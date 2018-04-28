# TiOWMaster - 单总线，主机模式

1-Wire总线是一个简单的信号传输电路，可通过一根共用的数据线实现主控制器与一个或一个以上从器件之间的半双工双向通信。具体可参考https://baike.baidu.com/item/1-Wire或https://en.wikipedia.org/wiki/1-Wire。

TiJOS Framework提供TiOWMaster来支持1-Wire主机功能，其将OW Master按照port分组，共支持256组，组号范围0-255，即：port0-port255；其中每组内按照io划分16条输入输出，输入输出号范围0-15，即：io0-io15；每条输入输出都支持指定模式的单独设置。

## Java包
tijos.framework.devicecenter

## TiOWMaster工作模式定义

| 项目   | 参数                           | 说明        |
| ---- | ---------------------------- | --------- |
| 工作模式 | MODE_STANDARD/MODE_OVERDRIVE | 标准模式/高速模式 |

## TiOWMaster方法定义

TiOWMaster类中主要的方法：

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| TiOWMaster open(int portID, int... ioIDs) | 静态方法，通过指定port和io集合打开OW Master，返回TiOWMaster对象 |
| void close()                             | 关闭当前对象                                   |
| setWorkMode(int ioID, int mode)          | 设置工作模式                                   |
| void reset(int ioID)                     | 复位I/O                                    |
| int readBits(int ioID, int bits)         | 读I/O位数据                                  |
| void writeBits(int ioID, int value, int bits) | 写I/O位数据                                  |

TiOWMaster类中他方法的技术说明请参考TiJOS Framework说明文档。

## TiOWMaster对象创建与销毁

TiOWMaster对象的创建需要调用open静态方法，传入指定port和io集合参数，返回创建的接口对象。

TiOWMaster对象的销毁需要调用close方法。

```java
...
int owPort0 = 0;
int owIo0 = 0; //定义io集合
int owIo1 = 1;
TiOWMaster ow0 = TiOWMaster.open(owPort0, owIo0, owIo1);
...
...
...
ow0.close();
```

## TiOWMaster模式设置

TiOWMaster模式设置通过setIoMode方法设置，设置参数为ioID和工作模式。

```java
...
ow0.setIoMode(owIo0, TiOWMaster.MODE_STANDARD); //io0工作在标准模式
ow0.setIoMode(owIo1, TiOWMaster.MODE_OVERDRIVE); //io1工作在高速模式
...
```

注意：模式支持受平台特性限制，设置时须了解平台特性。

## TiOWMaster输入输出

TiOWMaster的输入输出操作通过reset、readBits和writeBits方法完成。

```java
...
ow0.reset(owIo0);
ow0.writeBits(owIo0, 0x80, 8);
int value = ow0.readBits(owIo0, 8);
...
ow0.reset(owIo1);
ow0.writeBits(owIo1, 0x01, 1);
int value = ow0.readBits(owIo1, 2);
...
```

具体可参考TiJOS Framework说明文档。