# wlan - 无线局域网

无线[局域网络](https://baike.baidu.com/item/%E5%B1%80%E5%9F%9F%E7%BD%91%E7%BB%9C/5593831)英文全名：Wireless Local Area Networks；简写为： [WLAN](https://baike.baidu.com/item/WLAN)。它是相当便利的[数据传输系统](https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E4%BC%A0%E8%BE%93%E7%B3%BB%E7%BB%9F/143597)，它利用[射频](https://baike.baidu.com/item/%E5%B0%84%E9%A2%91/775402)（Radio Frequency； RF）的技术，使用电磁波，取代旧式碍手碍脚的双绞铜线（Coaxial）所构成的局域网络，在空中进行通信连接，使得[无线局域网](https://baike.baidu.com/item/%E6%97%A0%E7%BA%BF%E5%B1%80%E5%9F%9F%E7%BD%91)络能利用简单的存取架构让用户透过它，达到“信息随身化、便利走天下”的理想境界。WLAN的实现协议有很多，其中最为著名也是应用最为广泛的当属无线保真技术--Wi-Fi，它实际上提供了一种能够将各种终端都使用无线进行互联的技术，为用户屏蔽了各种终端之间的差异性。具体可参考https://baike.baidu.com/item/%E6%97%A0%E7%BA%BF%E5%B1%80%E5%9F%9F%E7%BD%91?fromtitle=WLAN&fromid=612199或https://baike.sogou.com/v8603.htm?fromTitle=WLAN。

## Java包
tijos.framework.platform.wlan

## WiFi设置 - TiWiFi

TiWiFi中包含了所有与WIFI相关的操作和设置, 支持Station和Station + softAP两种模式， 当打开softAP模式时，允许其它WLAN设备连接到此AP， 一般用于设备设置等功能。

TiWiFi为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| TiWiFi getInstance()                     | 获取WiFi实例                                 |
| void startup(int timeout)                | 启动WiFi并连接AP, timeout以秒为单位                |
| void shutdown()                          | 关闭WiFi                                   |
| void startupSoftAP(boolean hidden)       | 启动WiFi SoftAP功能，WiFi必须已经开启, hidden为true时， SSID将不广播 |
| void shutdownSoftAP()                    | 关闭WiFi SoftAP                            |
| void changeSSID(String SSID)             | 更改WiFi的SSID，更改后重新启动WiFi生效                |
| void changeSoftAPSSID(String SSID)       | 更新softAP的SSID，更改后重新启动SoftAP生效            |
| String getSSID()                         | 获取WiFi将连接的SSID名称                         |
| String getSoftAPSSID()                   | 获取SoftAP广播的SSID名称                        |
| void changePassword(String password)     | 更改WiFi的Password，更改后重启WiFi生效              |
| void changeSoftAPPassword(String password) | 更改SoftAP密码，更改后重启SoftAP生效                 |
| void setStaticAddress(String localAddress, String gateWayAddress, int subnetPrefix) | 设置WiFi的静态地址，设置后重启生效, 如果任何一个参数为null将使用DHCP动态IP地址，默认为DHCP动态IP |
| void setSoftAPStaticAddress(String localAddress, String gateWayAddress, int subnetPrefix) | 设置SoftAP的静态地址, 如果任何一个参数为null将使用默认IP地址    |
| String getAddress()                      | 获取当前IP地址                                 |
| String getSoftAPAddress()                | 获取SoftAP当前IP地址                           |
| void smartConfig()                       | 启动WIFI Smart Config, 可通过手机来进行无线WIFI快速设置  |

TiWiFi类中他方法的技术说明请参考TiJOS Framework说明文档。

### WiFi设置例程

WIFI设置支持手动设置和自动设置, 手动设置可通过TiDevManager工具进行设置， 自动设置则需要通过SmartConfig的方式进行， SmartConfig一种WiFi快连技术，可通过手机APP对WIFI设备直接进行IP地址配置，具体细节请搜索SmartConfig相关资料

#### 自动配置
现在的智能硬件产品，以WiFi品类居多，这些WiFi硬件没有人机交互界面，但设备要上网肯定要配置SSID等相关信息，于是WiFi快连应运而生， SmartConfig就是目前使用最广泛的WIFI快连技术。 
在TiJOS中可通过TiWiFi类中的smartConfig接口来完成相应的配置， 在实际使用时，可接合外部触发来启动SmartConfig, 如按键，等等。 

在使用自动配置时，需要在手机端安装相应的APP， 请从以下路径安装相应的APP - <https://github.com/EspressifApp/EsptouchForAndroid>
自动配置成功后， 下次启动时会自动连接该WIFI路由， 因此只需设置一次即可。

```java
...
 //请从手机打开SmartConfig软件,
  //启动smartConfig, 超时30秒， 出错或超时会抛出相应的异常
  TiWiFi.getInstance().smartConfig(30);
  System.out.println(" SSID: " + TiWiFi.getInstance().getSSID());
  System.out.println(" Password: " + TiWiFi.getInstance().getPassword());

...
```

#### 手动配置
WiFi连接目标AP，WiFi相关设置也可从TiDevManager中设置，这样就无需在代码设置WiFi连接参数

```java
...
//更改AP的SSID和密码，设置一次即可，也可通过TiDeviceManager工具设置，本设置掉电不丢失
TiWiFi.getInstance().changeSSID("TPLink-123");
TiWiFi.getInstance().changePassword("12345678");
...
//启动WLAN，连接目标AP，超时10秒
int status = TiWiFi.getInstance().startup(10);
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

WiFi连接目标AP，IP地址静态设置。

```java
...
//更改AP的SSID和密码，设置一次即可，也可通过TiDeviceManager工具设置，本设置掉电不丢失
...
TiWiFi.getInstance().changeSSID("TPLink-123");
TiWiFi.getInstance().changePassword("12345678");

//设置静态IP，网关，子网掩码
TiWiFi.getInstance().setStaticAddress("192.168.1.100", "192.168.1.1", 24);
...
//启动WiFi，连接目标AP，超时10秒
try{
  TiWiFi.getInstance().startup(10);
}
catch(IOException ex) {
    ex.printStackTrace(); //connect fail 
}

System.out.println("connect success.");
...
```

WiFi连接目标AP，IP地址动态分配，同时启动SoftAP。

```java
...
//更改AP的SSID和密码，设置一次即可，也可通过TiDeviceManager工具设置，本设置掉电不丢失
...
TiWiFi.getInstance().changeSSID("TPLink-123");
TiWiFi.getInstance().changePassword("12345678");

//更改SoftAP的SSID和密码，设置一次即可，本设置掉电不丢失
TiWiFi.getInstance().changeSoftAPSSID("TiJOS-SoftAP");
TiWiFi.getInstance().changeSoftAPPassword("12345678", true);
...
try{
  //启动WLAN，连接目标AP，超时10秒
  TiWiFi.getInstance().startup(10);
  //不论目标AP是否连接成功，都启动SoftAP, SSID不隐藏
  TiWiFi.getInstance().startupSoftAP(false);
}
catch(IOException ex){
  ex.printStackTrace(); //connect fail 
}  
System.out.println("connect success.");
```





