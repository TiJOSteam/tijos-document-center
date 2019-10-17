# TiADC - 模数转换器

ADC，Analog-to-Digital Converter的缩写ADC，指模/数转换器或者模数转换器。是指将连续变化的模拟信号转换为离散的数字信号的器件，具体可参考<https://baike.baidu.com/item/ADC/6529867或https://en.wikipedia.org/wiki/Analog-to-digital_converter>。

TiJOS Framework提供TiADC来支持ADC功能，其将ADC按照port分组，共支持256组，组号范围0-255，即：port0-port255；其中每组内按照channel划分16通道，通道范围0-15，即：channel0-channel15，测试模拟量为电压值。

## Java包
tijos.framework.devicecenter

## TiADC方法定义

TiADC类中主要的方法：

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| TiADC open(int portID)                   | 静态方法，通过指定port打开ADC所有通道，返回TiADC对象，**若需要再次打开或打开为其他设备时需要先调用close关闭当前对象** |
| TiADC open(int portID, int... channelIDs) | 静态方法，通过指定port及通道号打开ADC，返回TiADC对象，**若需要再次打开或打开为其他设备时需要先调用close关闭当前对象** |
| void close()                             | 关闭当前对象                                   |
| int getRawValue(int channelID)           | 获取指定通道的原始采样值                             |
| double getVoltageValue()                 | 获取转换后的电压测量值，单位：V                         |
| void setRefVoltageValue(double refVoltage) | 设置参考电压值，单位V。默认值：3.3                      |
| int getResolutionValue(boolean power)    | 获取分辨率                                    |

TiADC类中他方法的技术说明请参考TiJOS JDK framework说明文档。


## TiADC对象创建与销毁

TiADC对象的创建需要调用open静态方法，传入指定port参数，返回创建的接口对象。

TiADC对象的销毁需要调用close方法。

```java
...
int adcPort0 = 0; //定义接口，port0
int adcChannel0 = 0; //使用通道0
TiADC adc0 = TiADC.open(adcPort0, adcChannel0); //打开port0，返回TiADC接口对象
...
...
...
adc0.close(); //销毁当前对象
```



## TiADC电压测量

```java
...
  
adc0.setRefVoltage(1.0);     //1.0V为平台内部参考电压
						   //以ESP8266A平台为例，这部分与平台相关，需要根据平台特性设置。
double vin = adc0.getVoltageValue(adcChannel0);  //获取电压数字量
double voltage = vin * 5; //分压比1:5，以ESP8266A平台为例，这部分与平台相关，需要根据平台特性设置。
...
```

注意：电压测量参数中的内部参考电压和分压比受平台特性的限制，设置时须了解硬件平台特性。



具体可参考TiJOS Framework说明文档。