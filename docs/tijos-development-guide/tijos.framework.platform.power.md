# TiPower - 电源管理

钛极OS电源管理功能通过tijos.framework.platform.TiPower支持，用户可在应用中根据实际情况来进行电源管理从而达到降低功耗的目的。 由于不同CPU的电耗管理能力不同， 因此在使用电源管理功能前请先确认该CPU是否支持相应的模式。

## Java包
tijos.framework.platform

包含类如下:

| 类名称     | 说明                 |
| ---------- | -------------------- |
| TiPower    | 电源管理             |

## TiPower

主要方法如下：

| 方法                         | 说明                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| void sleep()                 | 系统进入Sleep模式，通过外部事件(GPIO)自动唤醒。 **唤醒后程序会继续运行**。 |
| void stop(int timeWakeup)                  | 系统进入Stop模式，通过外部事件(GPIO)或指定时间后自动唤醒，时间单位：秒。 **唤醒后程序会继续运行**。 | void standby(int timeWakeup) | 系统进入StandBy模式，通过唤醒引脚或在指定时间后自动唤醒，时间单位：秒。**唤醒后程序会重新运行**。 |
                  |

TiPower类中他方法的技术说明请参考TiJOS Framework说明文档。

注意：电源管理受平台特性限制，设置时须了解硬件平台特性。

调用过程举例：

```java
...
TiPower.getInstance().standby(60); //系统进入standby模式，并在60秒以后自动唤醒。
...
```
