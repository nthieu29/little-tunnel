# little-tunnel
Simple and easy to use local proxy which help you visit websites blocked by your ISP.
## How does it work
Little Tunnel work as a local proxy in your computer, it processes the traffic of your browser to make your ISP can not understand or catch that traffic --> you could access the website you want.
## Why use Little Tunnel
- **Faster**: Little Tunnel built with [LittleProxy](https://github.com/adamfisk/LittleProxy) - a high performance HTTP proxy, it run in your local computer and processes only the traffic to your websites which you config. Your traffic go directly from your computer to the website you visit, not through any third-party nodes like VPN/Proxy.
- **Stable, safe and free**: Free VPN/Proxy usually is not stable and fast. For stable and fast VPN/Proxy, you have to pay the money. Little Tunnel is free and open source. It run in your local computer so it's stable and fast.
- **Easy to use**: We made the application not only efficient but also simple to use. We also provide comprehensive documentation for the application.
## Setup and config
1. Install JAVA: Please download [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. We need a browser with build-in proxy feature. We recommend Firefox, you can download [here](https://www.mozilla.org/en-US/firefox/new/).
3. Download Little Tunnel and double-click to run it.
4. Choose the tab **Sites Config**. Input the website you want to visit with hostname only, ignore www or http or https. For example: abc.com, example.com.

![Alt text](images/addSite.png?raw=true "Add Site")

5. Choose **Server tab**. Click **Start Server**.
6. Configure Firefox to use Little Tunnel:
- Click the menu button and select **Preferences**.
- In the **General** panel, scroll down to the **Network Settings** section.
- Click **Settings…**. The Connection Settings dialog will open.
- Choose **Manual proxy configuration** and configure like following:

![Alt text](images/firefoxProxy.png?raw=true "Configure Proxy in Firefox")

## Tips
1. **Use HTTPS whenever possible** because HTTPS will make harder for your ISP to block you. Input _https://_ as a prefix in address URL.
2. Little Tunnel automatically convert your site to lowercase and keep it distinct to maximize the process performance.
3. In the same directory of Little Tunnel, there is a config file called sites.txt. Little Tunnel read data from this file so you can add/remove site you want with your favorite text editor. Remember to restart Little Tunnel if you modify that file manually.
## It does not work?
1. Try use https by adding _https://_ as a prefix in your address bar.
2. Maybe your ISP block the website you want to visit by IP address or other techniques which Little Tunnel could not bypass at the moment.
