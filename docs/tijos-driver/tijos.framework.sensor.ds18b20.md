# DS18B20 温度传感器类

tijos.framework.sensor.ds18b20中包含DS18B20数字温度传感器类，可支持测量范围-55~+125℃、精度为±0.5℃的环境实时温度监测。

包含类如下:

| 类名称       | 说明              |
| --------- | --------------- |
| TiDS18B20 | DS18B20数字温度传感器类 |



## TiDS18B20

TiJOS提供使用DS18B20数字温度传感器的类。

TiJOS DS18B20包括如下类：

| 类名           | 说明                     |
| ------------ | ---------------------- |
| TiDS18B20    | DS18B20数字温度传感器类        |
| TiDS18B20ROM | DS18B20数字温度传感器ROM信息解析类 |



## TiDS18B20的创建、绑定与使用

TiDS18B20实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiOWMaster；设备总线的使用请参考：tijos.framework.devicecenter。



构造器如下：

| 构造器                                | 说明   |
| ---------------------------------- | ---- |
| TiDS18B20(TiOWMaster ow, int ioID) | 创建实例 |
| TiDS18B20ROM(long rom)             | 创建实例 |



主要方法如下：

| 方法                            | 说明                               |
| ----------------------------- | -------------------------------- |
| void selectSingle()           | 设置为单个传感器模式（仅在一根总线上只有一个传感器时调用）    |
| TiDS18B20ROM[] enumeration()  | 枚举总线上的传感器（仅在一根总线上挂有多个传感器时调用）     |
| void select(TiDS18B20ROM rom) | 选择指定ROM信息的传感器（仅在一根总线上挂有多个传感器时调用） |
| int getFamilyCode()           | 获取当前传感器子系列码（通过ROM对象获取）           |
| byte[] getSerialNumber()      | 获取当前传感器的唯一ID（通过ROM对象获取）          |
| int setResolution(int bits)   | 设置当前传感器的分辨率（默认为12位）              |
| int getResolution()           | 获取当前传感器当前的分辨率                    |
| void measure()                | 测量当前环境温度                         |
| double getTemperature()       | 获取当前温度数据（单位：摄氏度）                 |

当一根总线上只挂有一个DS18B20传感器时，使用方法如下：

```java
// 创建TiDS18B20对象ds18b20并将owIo0和owIoID0与其绑定
TiDS18B20 ds18b20 = new TiDS18B20(owIo0, owIoID0);
//选择只有单传感器
ds18b20.selectSingle();
//调用measure方法测量温度
ds18b20.measure();
//获取最近一次测量的结果
double temperature = ds18b20.getTemperature();

```

当一根总线上挂有多个DS18B20传感器时，使用方法如下：

```JAVA
// 创建TiDS18B20对象ds18b20并将owIo0和owIoID0与其绑定
TiDS18B20 ds18b20 = new TiDS18B20(owIo0, owIoID0);
//枚举单根总线上的所有传感器并返回传感器的ROM信息
TiDS18B20ROM[] romlist = ds18b20.enumeration();
//选择指定ROM信息的传感器，当前选择第一个
ds18b20.select(romlist[0]); 

//使用指定ROM信息的传感器，调用measure方法测量温度
ds18b20.measure();
//使用指定ROM信息的传感器，获取最近一次测量的结果
double temperature = ds18b20.getTemperature();

```

TiDS18B2类中他方法的使用请参考TiDS18B20数字温度传感器功能例程。