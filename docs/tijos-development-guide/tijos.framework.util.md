# 钛极OS(TiJOS)常用工具类使用说明

为了方便用户使用， TiJOS 提供了一些常用的工具类方便用户在应用中使用， 如日志，BASE64，JSON等。

## Java包
tijos.framework.util

| 包/类                  | 说明        |
| ------------------ | --------- |
| tijos.framework.util.base64  | BASE64编码类 |
| tijos.framework.util.json    | JSON类     |
| tijos.framework.util.logging | 日志类       |
| tijos.framework.util.crc     | CRC校验码    |
| BigBitConverter | Byte与Int,Long相互转换，大尾模式 |
| LittleBitConverter | Byte与Int, Long相互转换，小尾模式 |
| Delay | 延时，支持毫秒，微妙延时 |
| Formatter | 支持Double/float/byte[] 转字符串, 方便显示 |

## BASE64

BASE64是网络上最常见的使用字符串传输8位字节码的编码方式，具体请参考tijos.util.base64相关说明。

## JSON

JSON是一种常用的数据交换方式，也是物联网云服务使用最多数据格式， TiJOS 提供JSON类可以很方便将Key-Value数据组装为JSON格式发送到其它应用进行数据交换， 具体请参考tijos.util.json相关说明。

## Logging 

日志类用于控制日志的输出, 可通过设置动态调整输出日志级别， 具体请参考tijos.util.logging。

## CRC

CRC是一种数据通讯过程中常用的校验码运算方式，TiJOS提供了支持CRC8和CRC16, 具体请参考tijos.util.crc相关说明。

## BigBitConverter/LittleBitConverter

由于Java不支持指针，无法象C语言一样方便地进行不同类型之间的内存转换， BigBitConverter/LittleBitConverter提供了方便用户使用的不同类型的数据转换为Byte数组的方法， 常用于一些通讯协议的数据传输过程中。

## Delay

在硬件应用中经常用到延时功能，Delay类提供了毫秒和微妙级的延时功能， 比Java自带的Thread.Sleep更方便易用，建议用户在使用延时功能时使用该类提供的API.

## Formatter

生成数据格式化字符串，Formatter提供了常用的字符串转换函数，包括double/float 指定小数位长度， byte数组生成16进制字符串等等。

Java自带的DecimalFormat需要占用更大的代码和内存空间，性能不是太好， 建议能满足需求的情况下使用Formatter提供的函数。