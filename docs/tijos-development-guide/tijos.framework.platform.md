# platform - 平台设置

tijos.framework.platform.TiSettings中包含了与平台设置相关的类，主机名称等等， 这些设置可通过TiDevManager工具进行设置，也可通过该类来进行设置。

## Java包
tijos.framework.platform

包含类如下:

| 类名称     | 说明                 |
| ---------- | -------------------- |
| TiSettings | 系统设置，如主机名称 |

## TiSettings

主要方法如下：

| 方法                             | 说明                   |
| -------------------------------- | ---------------------- |
| void renameHost(String name)     | 主机重命名             |
| void resetLoggerLevel(int level) | 设置全局日志等级       |
| long getDateTime()               | 获取系统时间，单位为秒 |
| void setDateTime(long seconds)   | 设置系统时间，单位为秒 |

TiSettings类中他方法的技术说明请参考TiJOS Framework说明文档。

调用过程举例：

```java
...
TiSettings.renameHost("TiJOS-12345678"); //重新命名主机名
...
TiSettings.resetLoggerLevel(1000); //设置全局日志等级为1000
...
```