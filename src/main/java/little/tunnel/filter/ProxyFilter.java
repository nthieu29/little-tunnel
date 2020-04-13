package little.tunnel.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import little.tunnel.Config;
import little.tunnel.site.Site;
import little.tunnel.util.HttpUtility;
import org.littleshoot.proxy.HttpFiltersAdapter;

public class ProxyFilter extends HttpFiltersAdapter {

    public ProxyFilter(HttpRequest originalRequest, ChannelHandlerContext ctx) {
        super(originalRequest, ctx);
    }

    public ProxyFilter(HttpRequest originalRequest) {
        super(originalRequest);
    }

    private static void bypassDPI(HttpRequest request) {
        String host = request.headers().get("Host");
        if (Config.MIX_HOST_CASE) {
            StringBuilder modified = new StringBuilder();
            for (int i = 0; i < host.length(); i++) {
                char c = host.toCharArray()[i];
                if (i % 2 == 0) {
                    c = Character.toUpperCase(c);
                }
                modified.append(c);
            }
            host = modified.toString();
        }
        request.headers().remove("Host");
        if (Config.PAYLOAD_LENGTH > 0) {
            for (int i = 0; i < Config.PAYLOAD_LENGTH; i++) {
                request.headers().add("X-Padding" + i, new String(new char[1000]).replace("\0", String.valueOf(i % 10)));
            }
        }
        request.headers().add("hOSt", host + ".");
    }

    @Override
    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
        if (httpObject instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) httpObject;
            if (Site.INSTANCE.isBlockedByISP(HttpUtility.formatHost(request.headers().get("Host")))) {
                bypassDPI(request);
            }
        }
        return null;
    }

    @Override
    public HttpResponse proxyToServerRequest(HttpObject httpObject) {
        if (httpObject instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) httpObject;
            if (request.headers().contains("Via")) {
                request.headers().remove("Via");
            }
        }
        return null;
    }

    @Override
    public HttpObject serverToProxyResponse(HttpObject httpObject) {
        if (httpObject instanceof DefaultHttpResponse) {
            DefaultHttpResponse response = (DefaultHttpResponse) httpObject;
            if (response.getStatus().code() == 302) {
                return HttpUtility.getStub("Thrown out ISP redirect to the stub");
            }
        }

        return httpObject;
    }
}
