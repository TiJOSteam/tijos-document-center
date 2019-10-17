import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import tijos.framework.networkcenter.dns.TiDNS;
import tijos.framework.platform.wlan.TiWiFi;

public class TcpClient {

    public static void main(String[] args) {

        try {
            // 启动连接WLAN, 10秒超时
            // WLAN的设置可通过TiDevManager配置
            TiWiFi.getInstance().startup(10);

            // 启动DNS
            TiDNS.getInstance().startup();
        } catch (IOException ex) {
            // 网络连接失败
            ex.printStackTrace();
            return;
        }

        // TCP服务器IP及PORT
        String host = "192.168.1.55";
        int port = 8080;
        Socket client = null;

        try {
            // Connect to the server with TCP
            client = new Socket(host, port);
            System.out.print("Connect : " + client.getRemoteSocketAddress());

            OutputStream output = client.getOutputStream();

            // Send data to the TCP server
            output.write("Hello, this is client".getBytes());
            output.flush();

            // Get remote data from the server
            InputStream input = client.getInputStream();

            byte[] buffer = new byte[1024];
            while (true) {
                int len = -1;
                len = input.read(buffer);

                if (len > 0) {
                    System.out.println("message form server:" + new String(buffer, 0, len));

                    // echo to the server
                    output.write(buffer, 0, len);
                    output.flush();
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                /* ignore */
            }
        }

    }
}
