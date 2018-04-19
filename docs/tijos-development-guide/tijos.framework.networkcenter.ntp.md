# ntp - 网络时间协议客户端

NTP是网络时间协议(Network Time Protocol)，它是用来同步网络中各个计算机的时间的协议, 具体可参考<https://baike.baidu.com/item/NTP> 或 <https://en.wikipedia.org/wiki/Network_Time_Protocol>。

TiJOS Framework提供NTPUDPClient来支持NTP协议， 方便运行TiJOS的设备可从网络中获得当前的准确时间， 此NTP实现来自 **org.apache.commons.net.ntp.NTPUDPClient**， 可参考相关资料获得更多信息。

## Java包
tijos.framework.networkcenter

调用过程如下所示：

```java
NTPUDPClient ntpcli = new NTPUDPClient();
long interval = 0;
InetAddress host = InetAddress.getByName("58.220.207.226"); //NTP Server IP
TimeInfo tm = ntpcli.getTime(host);
interval = tm.getOffset();

//Display the network time
Calendar cal = Calendar.getInstance();
cal.setTimeInMillis(interval + System.currentTimeMillis());
System.out.println(cal.getTime().toString());
```

tm.getOffset()返回当前主机时间与网络时间差距，单位毫秒.