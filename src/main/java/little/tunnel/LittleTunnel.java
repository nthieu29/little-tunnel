package little.tunnel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import little.tunnel.filter.ProxyFilter;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

public enum LittleTunnel {
    INSTANCE;
    private HttpProxyServer server;
    private boolean started;

    public void startServer(int port) {
        if (server == null || !started) {
            this.server = DefaultHttpProxyServer.bootstrap().withFiltersSource(new HttpFiltersSourceAdapter() {
                @Override
                public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                    return new ProxyFilter(originalRequest);
                }
            })
                    .withPort(port)
                    .withTransparent(true).start();
            started = true;
        }
    }

    public void stopServer() {
        if (server != null) {
            this.server.stop();
            started = false;
        }
    }
}
