# logging - 日志输出

TiJOS Framework提供了日志输出类方便用户控制将日志输出到串口， 日志支持多个等级:INFO, WARNING, SEVERE,OFF, 在实际应用中可根据情况控制日志输出层级， 日志等级可通过TiDeviceManager进行设置。

日志输出包括两个参数:

​	componentName: 模块名称

​	message: 日志

日志可通过**Logger.info, Logger.warning, Logger.severe** 输出不同等级的日志。

## Java包
tijos.framework.util

主要方法如下

| 方法                                          | 说明           |
| --------------------------------------------- | -------------- |
| severe(String componentName, String message)  | 严重等级的日志 |
| warning(String componentName, String message) | 警告等级的日志 |
| info(String componentName, String message)    | 信息等级的日志 |

 例程:

```java
//Output current IP address 
Logger.info("MQTTClientDemo", "Current IP address : " + InetAddress.getLocalHost());
```

