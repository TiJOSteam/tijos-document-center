# networkcenter - 网络管理中心

TiJOS网络管理中心主要用于设置与网络相关的功能，如WLAN， DNS等等。

## Java包
tijos.framework.networkcenter

## WLAN设置 - TiWLAN

TiWLAN 包含了所有与WLAN相关的操作和设置, WLAN支持Station和Station + softAP两种模式， 当打开softAP模式时，允许其它WLAN设备连接到此AP， 一般用于设备设置等功能。

TiWLAN为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| TiWLAN getInstance()                                         | 获取WLAN实例                                                 |
| void startup(int timeout)                                    | 启动WLAN并连接AP, timeout以秒为单位                          |
| void shutdown()                                              | 关闭WLAN                                                     |
| void startupSoftAP(boolean hidden)                           | 启动WLAN SoftAP功能，WLAN必须已经开启, hidden为true时， SSID将不广播 |
| void shutdownSoftAP()                                        | 关闭WLAN SoftAP                                              |
| void changeSSID(String SSID)                                 | 更改WLAN的SSID，更改后重新启动WLAN生效                       |
| void changeSoftAPSSID(String SSID)                           | 更新softAP的SSID，更改后重新启动SoftAP生效                   |
| String getSSID()                                             | 获取WLAN将连接的SSID名称                                     |
| String getSoftAPSSID()                                       | 获取SoftAP广播的SSID名称                                     |
| void changePassword(String password)                         | 更改WLAN的Password，更改后重启WLAN生效                       |
| void changeSoftAPPassword(String password)                   | 更改SoftAP密码，更改后重启SoftAP生效                         |
| void setStaticAddress(String localAddress, String gateWayAddress, String netMaskAddress) | 设置WLAN的静态地址，设置后重启生效, 如果任何一个参数为null将使用DHCP动态IP地址，默认为DHCP动态IP |
| void setSoftAPStaticAddress(String localAddress, String gateWayAddress, String netMaskAddress) | 设置SoftAP的静态地址, 如果任何一个参数为null将使用默认IP地址 |
| String getAddress()                                          | 获取当前IP地址                                               |
| String getSoftAPAddress()                                    | 获取SoftAP当前IP地址                                         |

TiWLAN类中他方法的技术说明请参考TiJOS Framework说明文档。



### WLAN设置例程

WLAN连接目标AP，IP地址动态分配（推荐使用方式）， WLAN相关设置也可从Ti-Dev Manager中设置，这样就无需在代码设置WLAN连接

```java
...
//更改AP的SSID和密码，设置一次即可，也可通过TiDeviceManager工具设置，本设置掉电不丢失
TiWLAN.getInstance().changeWLANSSID("TPLink-123");
TiWLAN.getInstance().changePassword("12345678");
...
//启动WLAN，连接目标AP，超时10秒
int status = TiWLAN.getInstance().startup(10);
if(status == -1) {
    System.out.println("connect timeout.");
    return ;
}
if(status == -2) {
    System.out.println("connect fail.");
    return ;    
}
System.out.println("connect success.");
...
```

WLAN连接目标AP，IP地址静态设置。

```java
...
//更改AP的SSID和密码，设置一次即可，也可通过TiDeviceManager工具设置，本设置掉电不丢失
...
TiWLAN.getInstance().changeWLANSSID("TPLink-123");
TiWLAN.getInstance().changePassword("12345678");

//设置静态IP，网关，子网掩码
TiWLAN.getInstance().setStaticAddress("192.168.1.100", "192.168.1.1", "255.255.255.0");
...
//启动WLAN，连接目标AP，超时10秒
try{
  TiWLAN.getInstance().startup(10);
}
catch(IOException ex) {
    ex.printStackTrace(); //connect fail 
}

System.out.println("connect success.");
...
```

WLAN连接目标AP，IP地址动态分配，同时启动SoftAP。

```java
...
//更改AP的SSID和密码，设置一次即可，也可通过TiDeviceManager工具设置，本设置掉电不丢失
...
TiWLAN.getInstance().changeWLANSSID("TPLink-123");
TiWLAN.getInstance().changePassword("12345678");

//更改SoftAP的SSID和密码，设置一次即可，本设置掉电不丢失
TiWLAN.getInstance().changeSoftAPSSID("TiJOS-SoftAP");
TiWLAN.getInstance().changeSoftAPPassword("12345678", true);
...
try{
  //启动WLAN，连接目标AP，超时10秒
  TiWLAN.getInstance().startup(10);
  //不论目标AP是否连接成功，都启动SoftAP, SSID不隐藏
  TiWLAN.getInstance().startupSoftAP(false);
}
catch(IOException ex){
  ex.printStackTrace(); //connect fail 
}  
System.out.println("connect success.");
```



## DNS设置  - TiDNS

TiDNS中包含了所有与DNS服务器相关的设置， 默认DNS服务器使用OpenDNS服务器，IP为208.67.222.222，208.67.220.220， 用户可通过TiDNS进行修改。

TiDNS为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| TiDNS getInstance()                      | 获得TiDNS实例  |
| void startup()                           | 启动DNS客户端服务 |
| void shutdown()                          | 关闭DNS客户端服务 |
| void changeServer(String primaryAddress, String secondaryAddress) | 更改DNS服务器地址 |
| String[] getServer()                     | 获取DNS服务器地址 |

### DNS功能调用

```java
...
TiDNS.getInstance().startupDNS();
...
TiDNS.getInstance().shutdown();
```





