# 蜂鸣器类

tijos.framework.transducer.buzzer中包含了与蜂鸣器相关的类，通过使用不同延时控制蜂鸣器的导通、关闭，来实现不同频率的提示声，满足声音提示、报警提示以及按键提示等多种提示音。

包含类如下:

| 类名称      | 说明         |
| -------- | ---------- |
| TiBuzzer | buzzer蜂鸣器类 |

## TiBuzzer

TiJOS提供控制蜂鸣器的类。

TiJOS Buzzer包括如下类：

| 类名       | 说明         |
| -------- | ---------- |
| TiBuzzer | buzzer蜂鸣器类 |

## TiBuzzer的创建、绑定与使用

TiBuzzer实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。

构造器如下：

| 构造器                                      | 说明            |
| ---------------------------------------- | ------------- |
| TiBuzzer(TiGPIO gpio, int signalPinID)   | 创建实例,默认为低电平激活 |
| TiBuzzer(TiGPIO gpio, int signalPinID, boolean highLevel) | 创建实例，激活电平可配置  |

主要方法如下：

| 方法                   | 说明             |
| -------------------- | -------------- |
| void turnOn()        | 打开蜂鸣器          |
| void turnOff()       | 关闭蜂鸣器          |
| boolean isTurnedOn() | 获取当前蜂鸣器状态（开/关） |

TIBuzzer中的主要方法使用如下：

```java
//创建蜂鸣器对象
TiBuzzer buzzer = new TiBuzzer(gpio0, gpioPin0,false);
//打开蜂鸣器
buzzer.turnOn();
//关闭蜂鸣器
buzzer.turnOff();
```

TiBuzze类中他方法的使用请参考TiBuzzer蜂鸣器功能例程。