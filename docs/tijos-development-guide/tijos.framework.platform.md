# platform - 平台基础特性

tijos.framework.platform中包含了与平台特性相关的类，如：设置主机名称、设置电源模式等。

## Java包
tijos.framework.platform

包含类如下:

| 类名称        | 说明         |
| ---------- | ---------- |
| TiSettings | 平台设置，如主机名称 |
| TiPower    | 电源管理       |

## TiSettings - 平台设置

主要方法如下：

| 方法                               | 说明          |
| -------------------------------- | ----------- |
| void renameHost(String name)     | 主机重命名       |
| void resetLoggerLevel(int level) | 设置全局日志等级    |
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
这些设置可通过TiDevManager工具进行设置。

## TiPower - 电源管理

主要方法如下：

| 方法                           | 说明                                       |
| ---------------------------- | ---------------------------------------- |
| void sleep()                 | 系统进入Sleep模式，通过外部事件(GPIO)自动唤醒。 唤醒后程序会**继续**运行。 |
| void stop(int timeWakeup)    | 系统进入Stop模式，通过外部事件(GPIO)或指定时间后自动唤醒，时间单位：秒。 唤醒后程序会**继续**运行。 |
| void standby(int timeWakeup) | 系统进入StandBy模式，通过唤醒引脚或在指定时间后自动唤醒，时间单位：秒。唤醒后程序会**重新**运行。 |

TiPower类中他方法的技术说明请参考TiJOS Framework说明文档。

注意：电源管理受平台特性限制，设置时须了解硬件平台特性。

调用过程举例：

```java
...
TiPower.getInstance().standby(60); //系统进入standby模式，并在60秒以后自动唤醒。
...
```

用户可在应用中根据实际情况来进行电源管理从而达到降低功耗的目的。 由于不同CPU的电耗管理能力不同， 因此在使用电源管理功能前请先确认该CPU是否支持相应的模式。