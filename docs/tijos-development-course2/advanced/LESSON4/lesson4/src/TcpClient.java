import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import tijos.framework.networkcenter.dns.TiDNS;
import tijos.framework.platform.wlan.TiWiFi;

public class TcpClient {

    public static void main(String[] args) {


        try {
            // ��������WLAN, 10�볬ʱ
            // WLAN�����ÿ�ͨ��TiDevManager����
            TiWiFi.getInstance().startup(10);

            // ����DNS
            TiDNS.getInstance().startup();
        } catch (IOException ex) {
            // ��������ʧ��
            ex.printStackTrace();
            return;
        }

        // TCP������IP���˿ں�
        String host = "192.168.0.113";
        int port = 8080;
        Socket client = null;

        try {
            // ��TCP���ӵ�������
            client = new Socket(host, port);
            System.out.print("Connect : " + client.getRemoteSocketAddress());

            OutputStream output = client.getOutputStream();

            // �������ݵ�TCP������
            output.write("Hello, this is client".getBytes());
            output.write("\r\n".getBytes());
            output.flush();

            // �ӷ�������ȡԶ������
            InputStream input = client.getInputStream();

            byte[] buffer = new byte[1024];
            while (true) {
                int len = -1;
                len = input.read(buffer);

                if (len > 0) {

                    //��ӡ������յ�������
                    System.out.println("Message form server:" + new String(buffer, 0, len));

                    // �������ݵ�TCP������
                    output.write("Message form client:".getBytes());
                    //���ͽ��յ����ݸ�������
                    output.write(buffer, 0, len);
                    //���ͻ��з���������
                    output.write("\r\n".getBytes());
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
