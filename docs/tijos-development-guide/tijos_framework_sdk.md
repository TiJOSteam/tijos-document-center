# TiJOS Framework 说明

TiJOS Framework提供了用户在应用中对硬件资源及传感器类等进行操作的相关类, 其中包括对各种硬件接口和传感器类的操作， 同时也提供了一些可能用到的工具类, 如BASE64, JSON等等

详情请参考 [TiJOS Framework JavaDoc](http://dev.tijos.net/javadoc)

## TiJOS Framework 包

| 包名称                                 | 说明                                       |
| ----------------------------------- | ---------------------------------------- |
| tijos.framework.platform            | 系统设置相关类，主机名称等设置                          |
| tijos.framework.platform.peripheral | 平台最小系统外设, 键盘， 按键等等                       |
| tijos.framework.platform.wlan       | WLAN设置, WIFI等                            |
| tijos.framework.eventcenter         | 事件中心类，处理来自硬件外设总线的事件，如GPIO事件等             |
| tijos.framework.devicecenter        | 设备总线相关类，如GPIO, I2C, PWM等等，可用来开发在驱动库中需要支持的传感器 |
| tijos.framework.appcenter           | 应用中心类，进行应用管理，包括安装，升级，运行等等                |
| tijos.framework.networkcenter       | 网络中心类，网络协议相关, 如: DNS、NTP、MQTT等           |
| tijos.framework.util                | 常用工具类，如Delay, byte数组与int之间相互转换, 按格式生成字符串等等 |
| tijos.framework.util.crc            | CRC校验算法                                  |
| tijos.framework.util.base64         | BASE64转换类                                |
| tijos.framework.util.json           | JSON 处理类                                 |
| tijos.framework.util.logging        | 日志类，输出到打印口                               |
