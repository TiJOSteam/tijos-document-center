# 继电器类

tijos.framework.transducer.relay 中包含了与继电器相关的类，继电器的主要功能是使用低电压控制高电压、大电流以及交流电等强电电路的开断，被广泛用于工业控制、家庭电器以及汽车电子等多个行业。

包含类如下:

| 类名称        | 说明      |
| ---------- | ------- |
| TiRelay1CH | 单通道继电器类 |

TiJOS提供控制单通道继电器的类。

TiJOS Relay1CH包括如下类：

| 类名         | 说明      |
| ---------- | ------- |
| TiRelay1CH | 单通道继电器类 |

## TiRelay1CH

TiRelay1CH实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。

构造器如下：

| 构造器                                      | 说明                 |
| ---------------------------------------- | ------------------ |
| TiRelay1CH(TiGPIO gpio, int signalPinID) | 创建实例,默认为高电平导通继电器   |
| TiRelay1CH(TiGPIO gpio, int signalPinID, boolean highLevel) | 创建实例，控制继电器导通的电平可配置 |

主要方法如下：

| 方法                   | 说明             |
| -------------------- | -------------- |
| void turnOn()        | 继电器导通          |
| void turnOff()       | 继电器断开          |
| boolean isTurnedOn() | 获取当前继电器状态（开/关） |

TiRelay中主要方法的使用如下：

```java
//创建TiRelay对象
TiRelay1CH relay = new TiRelay1CH(gpio0, gpioPin0);
//打开继电器
relay.turnOn();
//关闭继电器
relay.turnOff();
```

TiRelay1CH类中他方法的使用请参考TiRelay1CH继电器控制例程。