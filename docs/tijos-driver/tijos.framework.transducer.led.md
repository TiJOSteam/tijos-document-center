# LED显示类

tijos.framework.transducer.led中包含了LED灯、RGBELD灯以及OLED屏幕相关的类。其中LED灯为普通单色发光二极管，套件中自带红色、黄色、蓝色、绿色四种颜色的发光二极管，可以通过不同的PIN口单独控制亮灭，适用于需要指示灯、报警灯等场景；RGB三基色LED灯包含红色、绿色和蓝色三种基础色，可通过TiPWM接口各自输出不同的亮度等级，最终混色成相应的颜色，适用于需要变色指示灯、呼吸灯等场景；OLED屏幕使用TiICMaster接口控制，可输出4*16个字符，适用于需要显示信息、带有人机交互界面的设备。

包含类如下:

| 类名称           | 说明       |
| ------------- | ------------ |
| TiLED         | LED灯类        |
| TIRGBLED      | RGBLED灯类     |
| TiOLED_UG2864 | OLED12864屏幕类 |

## TiLED

TiJOS提供控制LED灯的类。

TiJOS LED包括如下类：

| 类名    | 说明    |
| ----- | ----- |
| TiLED | LED灯类 |



## TiLED的创建、绑定与使用

TiLED实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。



构造器如下：

| 构造器                                      | 说明            |
| ---------------------------------------- | ------------- |
| TiLED(TiGPIO gpio, int signalPinID)      | 创建实例,默认为低电平点亮 |
| TiLED(TiGPIO gpio, int signalPinID, boolean highLevel) | 创建实例，点亮电平可配置  |



主要方法如下：

| 方法                   | 说明               |
| -------------------- | ---------------- |
| void turnOn()        | 点亮LED灯           |
| void turnOff()       | 关闭LED灯           |
| boolean isTurnedOn() | 获取当前LE灯D状态（开/关）  |
| void turnOver()      | 对当前LED灯开关状态取相反状态 |

TiLED中主要方法的使用如下：

```java
//创建TiLED对象
TiLED red = new TiLED(gpio0, gpioPin0,false);
//点亮LED灯
red.turnOn();
//关闭LED灯
red.turnOff();
```

TiLED类中他方法的使用请参考TiLED灯控制例程。



## TiRGBLED

TiJOS提供控制RGBLED三基色灯的类。

TiJOS RGBLED包括如下类：

| 类名       | 说明            |
| -------- | ------------- |
| TiRGBLED | TiRGBLED三基色灯类 |



## TiRGBLED的创建、绑定与使用

TiRGBLED实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiPWM；设备总线的使用请参考：tijos.framework.devicecenter。



构造器如下：

| 构造器                                      | 说明            |
| ---------------------------------------- | ------------- |
| TiRGBLED(TiPWM pwm, int redChannelID, int greenChannelID, int blueChannelID) | 创建实例,默认为低电平点亮 |
| TiRGBLED(TiPWM pwm, int redChannelID, int greenChannelID, int blueChannelID, boolean highLevel) | 创建实例，点亮电平可配置  |



主要方法如下：

| 方法                                 | 说明                          |
| ---------------------------------- | --------------------------- |
| void setPeriod(int period)         | 设置PWM频率（默认为1Khz）            |
| int getPeriod()                    | 获取PWM频率                     |
| void setRedBrightness(int level)   | 设置红色灯的亮度等级（支持等级：0~255）      |
| void setGreenBrightness(int level) | 设置绿色灯的亮度等级（支持等级：0~255）      |
| void setBlueBrightness(int level)  | 设置蓝色灯的亮度等级（支持等级：0~255）      |
| void updateBrightness()            | 将设置好的三基色灯的亮度登记更新到PWM并输出     |
| int getRedBrightness()             | 获取红色灯的当前亮度等级（其余绿色和蓝色均有相应方法） |

TiRGBLED类中主要方法的使用如下：

```java
//创建TiRGBLED对象,传入需要使用的pwm对象并绑定相应的通道
TiRGBLED rgbled = new TiRGBLED(pwm0, CH0,CH1,CH2,false);
//设置PWM周期为1000hz
rgbled.setPeriod(1000);
//随机选取三个亮度等级
r = 35;
g = 87;
b = 128;
//设置相应颜色的亮等级
rgbled.setRedBrightness(r);
rgbled.setGreenBrightness(g);
rgbled.setBlueBrightness(b);
//将设置好的亮度等级更新到硬件中
rgbled.updateBrightness();
```

TiRGBLE类中他方法的使用请参考TiRGBLED灯控制例程。

## TiOLED_UG2864

TiJOS提供操作TiOLED_UG2864屏幕的类。

TiJOS OLED_UG2864包括如下类：

| 类名            | 说明           |
| ------------- | ------------ |
| TiOLED_UG2864 | OLED12864屏幕类 |



## TiOLED_UG2864的创建、绑定与使用

TiOLED_UG2864实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiI2C；设备总线的使用请参考：tijos.framework.devicecenter。



构造器如下：

| 构造器                                      | 说明              |
| ---------------------------------------- | --------------- |
| TiOLED_UG2864(TiI2CMaster i2c, int address) | 创建实例,设备地址根据实际设定 |



主要方法如下：

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| void turnOn()                            | 点亮屏幕                                     |
| void turnOff()                           | 关闭屏幕                                     |
| void clear()                             | 清除当前屏幕显示的所有信息                            |
| void print(int lineId, int columnId, String text) | 在指定行列坐标处开始显示字符（可显示的最大数据量为一个满屏幕的数据量，即：4*16个字符，多余字符将无法显示） |
| void setPosition(int lineId, int columnId) | 设置指定的行坐标和列坐标（支持0~3行，0~15列）               |
| void output(String text)                 | 在指定坐标（由setPosition方法设置）处开始显示字符（当字符超出屏幕末端时，回滚从0行0列开始覆盖显示） |

TiOLED_UG2864类中主要方法的使用如下(单屏显示)：

```java
//创建对象并传入默认设备地址0x78
TiOLED_UG2864 oled = new TiOLED_UG2864(i2c0, 0x78);
String s = "Welcome to the TiKit world !";
/*给屏幕上电*/
oled.turnOn();
/*清屏*/
oled.clear();
/*从第0行0列开始打印字符串*/
oled.print(0, 0, s);
```

支持回滚的显示方法：

```java
//创建对象并传入默认设备地址0x78
TiOLED_UG2864 oled = new TiOLED_UG2864(i2c0, 0x78);
String s = "Welcome to the TiKit world !";
/*给屏幕上电*/
oled.turnOn();
/*清屏*/
oled.clear();
/*设置行起始坐标和列起始坐标，第1行第2列*/
oled.setPosition(1, 2);
/*在已经设置好的指定位置显示字符串（第1行第2列）*/
oled.output(s);
```

TiOLED_UG286类中他方法的使用请参TiOLED_UG2864屏幕控制例程。