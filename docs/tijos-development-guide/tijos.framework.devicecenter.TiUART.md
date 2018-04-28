# TiUART - 通用异步收发传输器

通用异步收发传输器（Universal Asynchronous Receiver/Transmitter)，通常称作UART，是一种异步收发传输器，是电脑硬件的一部分。具体可参考https://baike.baidu.com/item/UART/4429746?fr=aladdin或https://en.wikipedia.org/wiki/Universal_asynchronous_receiver-transmitter。

TiJOS Framework提供TiUART来支持UART功能，其将UART按照port分组，共支持256组，组号范围0-255，即：port0-port255；每组都支持指定模式的单独设置。

## Java包
tijos.framework.devicecenter

## TiUART工作模式定义

| 项目   | 参数                                       | 说明                     |
| ---- | ---------------------------------------- | ---------------------- |
| 波特率  | ...、1200bps 、2400bps 、 ...               | 支持的通讯波特率，受硬件平台限制       |
| 数据位  | 5 / 6 / 7 / 8 / 9                        | 数据位数                   |
| 停止位  | STOPBIT_1 / STOPBIT_2 / STOPBIT_0_5 / STOPBIT_1_5 | 停止位数:1 / 2 / 0.5 / 1.5 |
| 奇偶校验 | MODE_PARITY_NONE / MODE_PARITY_ODD / MODE_PARITY_EVEN | 无校验/奇校验/偶校验            |
| 缓冲类型 | BUFF_READ/ BUFF_WRITE/ BUFF_WR           | 读缓冲/写缓冲/读写缓冲           |



## TiUART方法定义

TiUART类中主要的方法：

| 方法                                       | 说明                          |
| ---------------------------------------- | --------------------------- |
| TiUART open(int portID)                  | 通过指定port打开指定UART，返回TiUART对象 |
| void close()                             | 关闭已打开的TiUART实例              |
| void setWorkParameters(int dataBits, int stopBits, int parity,int baudRate) | 设置工作模式，数据位、停止位，奇偶校验位、波特率    |
| int available()                          | 检查有效接收数据长度，单位字节             |
| void clear(int buffer)                   | 根据不同缓冲类型清除缓冲区               |
| int read(byte[] data, int offset, int len) | 读取数据，返回实际读取长度               |
| int write(byte[] data, int offset, int len) | 写入数据，返回实际写入长度               |

TiUART类中他方法的技术说明请参考TiJOS Framework说明文档。

## TiUART对象创建与销毁

TiUART对象的创建需要调用open静态方法，传入指定port参数，返回创建的接口对象。

TiUART对象的销毁需要调用close方法。

```java
...
int uartPort0 = 0;
TiUART uart0 = TiUART.open(uartPort0);
...
...
...
uart0.close();
```

## TiUART模式设置

TiUART模式设置通过setWorkParameters方法设置，设置参数为数据位、结束位、奇偶校验位和波特率。

```java
...
uart0.setWorkParameters(8, TiUART.STOPBIT_1, TiUART.PARITY_NONE, 9600); //8数据位, 1停止位, 无校验位，波特率9600bps 
...
```

注意：模式支持受平台特性限制，设置时须了解硬件平台特性。

## TiUART输入输出

TiUART的输入输出操作通过available、clear、read和write方法完成。

```java
...
uart0.clear(TiUART.BUFF_WR); //清除读写缓冲
while(true) {
    int length = uart0.available();//获取当前有效数据长度
    if(length <= 0)
        continue; //无数据继续等待
    byte[] buffer = new byte[length];
    length = uart0.read(buffer, 0, length); //接收数据
    uart0.write(buffer, 0, length); //将接收到的数据发送
}
```

具体可参考TiJOS Framework说明文档。