# OLED显示类

tijos.framework.transducer.oled中包含了OLED屏幕相关的类, OLED屏幕使用TiICMaster接口控制，可输出4*16个字符，适用于需要显示信息、带有人机交互界面的设备。

包含类如下:

| 类名称           | 说明           |
| ------------- | ------------ |
| TiOLED_UG2864 | OLED12864屏幕类 |

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