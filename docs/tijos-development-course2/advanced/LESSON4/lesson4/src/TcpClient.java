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

		// TCP服务器IP及端口号
		String host = "192.168.0.113";
		int port = 8080;
		Socket client = null;

		try {
			// 用TCP连接到服务器
			client = new Socket(host, port);
			System.out.print("Connect : " + client.getRemoteSocketAddress());

			OutputStream output = client.getOutputStream();

			// 发送数据到TCP服务器
			output.write("Hello, this is client".getBytes());
			output.write("\r\n".getBytes());
			output.flush();

			// 从服务器获取远程数据
			InputStream input = client.getInputStream();

			byte[] buffer = new byte[1024];
			while (true) {
				int len = -1;
				len = input.read(buffer);

				if (len > 0) {
					
					//打印输出接收到的内容
					System.out.println("Message form server:" + new String(buffer, 0, len));
					
					// 发送数据到TCP服务器
					output.write("Message form client:".getBytes());
					//发送接收的数据给服务器
					output.write(buffer, 0, len);
					//发送换行符给服务器
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
				/* ignore */}
		}

	}
}
