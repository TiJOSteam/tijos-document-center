# 4G LTE - 4G蜂窝网络

第四代移动通信技术

## Java包
tijos.framework.platform.lte

## LTE网络设置 - TiLTE

TiLTE中包含了所有与LTE网络相关的操作和设置 
TiLTE为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                                            | 说明                                               |
| ----------------------------------------------- | -------------------------------------------------- |
| TiLTE getInstance()                             | 获取LTE实例                                        |
| void startup(int timeout)                       | 启动LTE并连接基站, timeout以秒为单位               |
| void startup(int timeout, ILTEEventListener lc) | 启动LTE并连接基站， 当基站连接或断开时通过事件通知 |
| void shutdown()                                 | 关闭LTE                                            |
| String getIMEI()                                | 获取模组IEMI字符串                                 |
| String getIMSI()                                | 获取SIM卡IMSI字符串                                |
| void getICCID()                                 | 获取SIM卡CCID字符串                                |
| String getPDPIP()                               | 获取当前IP地址                                     |
| int getRSSI()                                   | 获取当前信号强度                                   |
| TiLTECell getCellInfo()                         | 获取基站信息                                       |

TiLTE类中他方法的技术说明请参考TiJOS Framework说明文档。

### TiLTE例程

一般流程如下，程序开始启动LTE网络，之后即可进行网络通讯处理，

```java
...

//启动LTE网络 30秒超时
TiLTE.getInstance().startup(30);

//通过MQTT进行数据发送等处理

```





