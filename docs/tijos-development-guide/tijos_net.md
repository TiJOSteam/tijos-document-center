# 钛极OS(TiJOS)网络应用开发指南

TiJOS 除支持标准JAVA的TCP, UDP,DNS网络接口外， 还提供了网络时间协议NTP, MQTT客户端等常用应用接口，标准网络应用通过java.net来支持，NTP和MQTT分别通过tijos.runtime.networkcenter.ntp及tijos.runtime.networkcenter.mqtt支持。

## 标准网络应用开发说明

TiJOS 支持了大部分java.net中的类，主要包括: 

| 类              | 说明                           | 网络功能       |
| -------------- | ---------------------------- | ---------- |
| Socket         | TCP 客户端套接字                   | TCP Client |
| ServerSocket   | TCP 服务端套接字                   | TCP Server |
| DatagramSocket | UDP套接字, 常与DatagramPacket一起使用 | UDP        |
| InetAddress    | Internet地址                   | DNS        |
| URI            | 统一资源标识符                      |            |
|                |                              |            |

TiJOS标准网络应用开发与标准JAVA一致， 具体可参考相关资料， 下面介绍一些典型应用的开发流程。

### TCP 客户端

典型的TCP Client开发流程如代码所示：

```java
//TCP服务器IP及PORT
String host = "192.168.1.55";
int port = 8080;

//连接服务器
Socket client = new Socket(host, port);

//发送数据到服务端
OutputStream  output = client.getOutputStream();
output.write("Hello, this is client".getBytes());
output.flush();

//从服务端获取数据
InputStream input = client.getInputStream();
byte[] buffer = new byte[1024];
while (true) {
	int len = -1;
	len = input.read(buffer);
  	if(len > 0 ) //有数据收到
    {
        ...
    }
}
//关闭客户端 
client.close();
```



### TCP 服务端

典型TCP Server 开发流程如下所示：

```java
//Server Socket
ServerSocket listener = new ServerSocket(8080);
System.out.println("local ip = " + listener.getLocalSocketAddress());

//Connected client socket
Socket socket = null;
			
//服务端等待客户端连接，默认超时时间: 60 seconds
socket = listener.accept();
System.out.println("a client is connected: " + socket.getRemoteSocketAddress());

//发送数据到客户端
OutputStream out = socket.getOutputStream();
out.write("Hello, This is the server.".getBytes());
out.flush();

//读取客户端数据
InputStream input = socket.getInputStream();
byte[] buff = new byte[1024];
while(true)
{
    int len = 0;
    len = input.read(buff);
    if(len > 0)
    {
      System.out.println("message from client:" + new String(buff, 0, len));
    }
}
```

### UDP 

典型的UDP应用如下所示：

```java
DatagramSocket udpSocket = new DatagramSocket();
String remote = "192.168.1.55";
int port = 8080;

//UDP 发送数据
byte [] msg = ("Hello Server").getBytes();
DatagramPacket dp = new DatagramPacket(msg, msg.length, InetAddress.getByName(remote), port);
udpSocket.send(dp);
	        
//UDP 接收数据
byte [] buffer = new byte[1024];
while(true)
{
	dp.setData(buffer);
	dp.setAddress(null);
  	//接收数据  默认超时时间10秒 
	udpSocket.receive(dp);
	            
	String info = new String(dp.getData(), 0, dp.getLength());
	System.out.println("Received: " + info);
	System.out.println("Remote IP:" + dp.getAddress());
}
```

## NTP网络时间客户端

通过NTP客户端， 可在应用中获得准确的网络时间来校准本地时间。

具体使用方式请参考tijos.framework.networkcenter.ntp

## MQTT 客户端

MQTT协议目前广泛应用于IoT物联网, 支持MQTT3.1.1协议，具体使用方式请参考tijos.framework.networkcenter.mqtt