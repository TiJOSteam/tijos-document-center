# TiI2CMaster - 双向二线制同步串行总线，主机模式

双向二线制同步串行总线（ Inter-Integrated Circuit）简称为I2C，是由[Philips](https://baike.baidu.com/item/Philips)公司开发的一种简单、双向二线制同步串行总线。它只需要两根线即可在连接于总线上的器件之间传送信息，具体可参考https://baike.baidu.com/item/I2C%E6%80%BB%E7%BA%BF/918424?fr=aladdin&fromid=1727975&fromtitle=I2C或https://en.wikipedia.org/wiki/I%C2%B2C。

TiJOS Framework提供TiI2CMaster来支持I2C主机功能，其将I2C Master按照port分组，共支持4组，组号范围0-3，即：port0-port3；每组都支持指定模式的单独设置。

## Java包
tijos.framework.devicecenter

## TiI2CMaster工作模式定义

| 项目   | 参数                      | 说明               |
| ---- | ----------------------- | ---------------- |
| 波特率  | 100Kbps、400Kbps、3.4Mbps | 支持的通讯波特率，受硬件平台限制 |
| 从机地址 | 7                       | 地址位数，0 - 127     |



## TiI2CMaster方法定义

TiI2CMaster类中主要的方法：

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| TiI2CMaster(int portID)                  | 静态方法，通过指定port打开I2C Master，返回TiI2CMaster对象，**若需要再次打开或打开为其他设备时需要先调用close关闭当前对象** |
| void close()                             | 关闭当前对象                                   |
| void setBaudRate(int baud)               | 设置通讯波特率                                  |
| oid read(int address, byte[] data, int offset, int len) | 从从机读数据。从机地址范围：0-127                      |
| void write(int address, byte[] data, int offset, int len) | 向从机写数据。从机地址范围：0-127                      |
| void read(int address, byte[] data, int offset, int len, boolean repeated) | 从从机读数据并使能重复，即，不发送stop。从机地址范围：0-127       |
| void write(int address, byte[] data, int offset, int len, boolean repeated) | 向从机写数据并使能重复，即，不发送stop。从机地址范围：0-127       |

TiI2CMaster类中他方法的技术说明请参考TiJOS Framework说明文档。

## TiI2CMaster对象创建与销毁

TiI2CMaster对象的创建需要调用open静态方法，传入指定port参数，返回创建的接口对象。

TiI2CMaster对象的销毁需要调用close方法。

```java
...
int i2cPort0 = 0;
TiI2CMaster i2c0 = TiI2CMaster.open(i2cPort0);
...
...
...
i2c0.close();
```



## TiI2CMaster模式设置

TiI2CMaster模式设置通过setBaudRate方法设置，设置参数为波特率。

```java
...
i2c0.setBaudRate(100); //波特率100Kbps
...
```

注意：模式支持受平台特性限制，设置时须了解硬件平台特性。



## TiI2CMaster输入输出

TiI2CMaster的输入输出操作通过read和write方法完成。

```java
...
byte[] cmdBuffer = {0x40, 0x00};
byte[] dataBuffer = {0x00, 0x00, 0x00, 0x00};
i2c0.write(0x3C, dataBuffer, 0, 2, true);
i2c0.read(0x3C, dataBuffer, 0, 4);
...
```



具体可参考TiJOS Framework说明文档。