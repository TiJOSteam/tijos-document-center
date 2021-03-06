# TiSPIMaster - 串行外设接口，主机模式

SPI是串行外设接口（Serial Peripheral Interface）的缩写。SPI，是一种高速的，全双工，同步的通信总线，并且在芯片的管脚上只占用四根线，节约了芯片的管脚，同时为PCB的布局上节省空间，提供方便，正是出于这种简单易用的特性，如今越来越多的芯片集成了这种通信协议。具体可参考https://baike.baidu.com/item/SPI或https://en.wikipedia.org/wiki/Serial_Peripheral_Interface_Bus。

TiJOS Framework提供TiSPIMaster来支持SPI主机功能，其将SPI Master按照port分组，共支持256组，组号范围0-255，即：port0-port255；每组都支持指定模式的单独设置。

## Java包
tijos.framework.devicecenter

## TiSPIMaster工作模式定义

| 项目    | 参数                                  | 说明                   |
| ----- | ----------------------------------- | -------------------- |
| 工作模式  | MODE0/MODE1/MODE2/MODE3             | 模式0/模式1/模式2/模式3      |
| 通道类型  | TYPE_SIO/TYPE_DIO/TYPE_QIO/TYPE_PIO | 单通道/双通道/四通道/并行通道     |
| 位传输模式 | ORDER_MSB/ORDER_LSB                 | 高位先传输/低位先传输          |
| 波特率   | 1/2/3...                            | 1Mbps/2Mbps/3Mbps... |

## TiSPIMaster方法定义

TiSPIMaster类中主要的方法：

| 方法                                       | 说明                                     |
| ---------------------------------------- | -------------------------------------- |
| TiSPIMaster open(int portID)             | 通过指定port打开指定SPI Master，返回TiSPIMaster对象 |
| void close()                             | 关闭已打开的TiSPIMaster实例                    |
| void setWorkParameters(int mode, int type, int order, int baudRate) | 设置工作模式，类型、位传输模式、波特率                    |
| void selectSlave(boolean enable)         | 从机选择使能控制                               |
| int read(byte[] data, int offset, int len) | 读取数据，返回实际读取长度                          |
| int write(byte[] data, int offset, int len) | 写入数据，返回实际写入长度                          |

TiSPIMaster类中他方法的技术说明请参考TiJOS Framework说明文档。

## TiSPIMaster对象创建与销毁

TiSPIMaster对象的创建需要调用open静态方法，传入指定port参数，返回创建的接口对象。

TiSPIMaster对象的销毁需要调用close方法。

```java
...
int spiPort0 = 0;
TiSPIMaster spi0 = TiSPIMaster.open(spiPort0);
...
...
...
spi0.close();
```

## TiSPIMaster模式设置

TiSPIMaster模式设置通过setWorkParameters方法设置，设置参数为工作模式、通道类型、波特率、位传输模式，波特率。

```java
...
//模式3, 单通道类型, 高位先传, 8Mbps
spi0.setWorkParameters(TiSPIMaster.MODE3, TiSPIMaster.TYPE_SIO, TiSPIMaster.ORDER_MSB, 8); 
...
```

注意：模式支持受平台特性限制，设置时须了解硬件平台特性。

## TiSPIMaster输入输出

TiSPIMaster的输入输出操作通过selectSlave、read和write方法完成。

```java
...
spi0.selectSlave(true);
byte[] dataBuffer = {1, 2, 3, 4, 5};
int writeLength = spi0.write(dataBuffer, 0, dataBuffer.length);
int readLength = spi0.read(dataBuffer, 0, dataBuffer.length);
spi0.selectSlave(false);
...
```

具体可参考TiJOS Framework说明文档。