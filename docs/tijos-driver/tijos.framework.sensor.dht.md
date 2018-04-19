# DHT 系列温湿度传感类

tijos.framework.sensor.dht中包含了DHT系列温度、湿度检监测的传感器类。温湿度监测支持DHT11/DHT22数字温湿度传感器，支持测量范围为0~50℃、精度为±2℃的环境实时温度监测，以及范围为20~90%、精度为±5%的湿度监测。温度监测使用DS18B20数字温度传感器，可支持测量范围-55~+125℃、精度为±0.5℃的环境实时温度监测。

包含类如下:

| 类名称       | 说明                     |
| --------- | ---------------------- |
| TiDHT     | DHT11/DHT22系列数字温湿度传感器类 |



## TiDHT

TiJOS提供使用DHT11和DHT22数字温湿度传感器的类。

TiJOS DHT包括如下类：

| 类名    | 说明                     |
| ----- | ---------------------- |
| TiDHT | DHT11/DHT22系列数字温湿度传感器类 |



## TiDHT的创建、绑定与使用

TiDHT实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。



构造器如下：

| 构造器                                      | 说明               |
| ---------------------------------------- | ---------------- |
| TiDHT(TiGPIO gpio, int dataPinID)        | 创建实例，默认DHT11     |
| TiDHT(TiGPIO gpio, int dataPinID, boolean model22) | 创建实例，DHT11或DHT22 |



主要方法如下：

| 方法                      | 说明                    |
| ----------------------- | --------------------- |
| void measure()          | 测量当前温度和湿度             |
| double getTemperature() | 获取当前环境温度（单位：摄氏度）      |
| double getHumidity()    | 获取当前环境湿度（单位：与空气相对百分比） |

TiDHT中方法的使用如下：

```java
TiDHT dht = new TiDHT(gpio0, gpioPin0);
// 设置传感器模式，本例程默认为DHT11

//调用测量方法
dht.measure();

//获取最近一次测量的温度、湿度数据
double temperature = dht.getTemperature();
double humidity = dht.getHumidity();

```

TiDH类中他方法的使用请参考TiDHT11 数字温湿度传感器功能例程。



