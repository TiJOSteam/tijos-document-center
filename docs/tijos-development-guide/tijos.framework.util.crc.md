# crc - CRC校验码 

循环冗余校验(Cyclic Redundancy Check, CRC)是一种根据网络数据包或电脑文件等数据产生简短固定位数校验码的一种散列函数，主要用来检测或校验数据传输或者保存后可能出现的错误, 具体请参考<https://baike.baidu.com/item/CRC/1453359> 或  <https://en.wikipedia.org/wiki/Cyclic_redundancy_check>

TiJOS Framework中提供了CRC8, CRC16校验码运算。

## Java包
tijos.framework.util

| 类                | 说明                                             |
| ----------------- | ------------------------------------------------ |
| CRC16_IBM         | IBM标准， 基于多项式X^16 + X^15 + X^2 + 1        |
| CRC8              | 单总线标准， 基于多项工 X^8 + X^5 + X^4 + 1      |
| CRC16_CCITT_FALSE | CCITT FALSE标准，基于多项式X^16 + X^15 + X^2 + 1 |

例程：

```java
byte [] input = new byte[]{1,2,3,4,5,6,7,8};
int crc8 = CRC8.compute(input, 0);
int crc16= CRC16_IBM.compute(input, 0);
```

