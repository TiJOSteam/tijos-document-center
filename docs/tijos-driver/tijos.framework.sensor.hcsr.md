# HC-SR系列测距传感器类

tijos.framework.sensor.hcsr中包含了HC-SR系列距离测量相关的传感器类，通过例如HC-SR04之类的超声波测距传感器，完成测距的功能，可满足室内、近场以及多种环境的使用。

包含类如下:

| 类名称      | 说明               |
| -------- | ---------------- |
| TiHCSR04 | HC-SR04超声波测距传感器类 |

## TiHCSR04

TiJOS提供使用HC-SR04超声波测距传感器测量距离的类。

TiJOS HCSR04 包括如下类：

| 类名       | 说明               |
| -------- | ---------------- |
| TiHCSR04 | HC-SR04超声波测距传感器类 |

## TiHCSR04的创建、绑定与使用

TiHCSR04实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。

构造器如下：

| 构造器                                      | 说明   |
| ---------------------------------------- | ---- |
| TiHCSR04(TiGPIO gpio, int trigPinID, int echoPinID) | 创建实例 |

主要方法如下：

| 方法                          | 说明                        |
| --------------------------- | ------------------------- |
| void setSpeed(double speed) | 设置声波的速度（单位：m/s，默认为340m/s） |
| void measure()              | 开始测量                      |
| double getDistance()        | 获取测量结果（单位：m）              |

TiHCSR04测距方法的使用：

```java
/* 创建TiHCSR04的对象hcsr04并将gpio0和gpioPin0和gpioPin1与其绑定
* trigPinID ----gpioPin0
* echoPinID ----gpioPin1
*/	
 TiHCSR04 hcsr04 = new TiHCSR04(gpio0, gpioPin0, gpioPin1);
//开始测量
hcsr04.measure();
//获取最近一次的测量结果
distance = hcsr04.getDistance();
```

TiHCSR04类中他方法的使用请参考TiHCSR04超声波测距功能例程。