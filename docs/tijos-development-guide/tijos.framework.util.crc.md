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



## CRC8 

CRC8 校验码算法， 主要方法如下：

| 方法                                                        | 说明                                                    |
| ----------------------------------------------------------- | ------------------------------------------------------- |
| int compute (byte dataToCrc [], int seed)                   | 对数组进行CRC计算，seed为初始值                         |
| int compute (byte dataToCrc [], int off, int len, int seed) | 对数组从off偏移开始len长度数据进行CRC计算，seed为初始值 |
|                                                             |                                                         |

例程：

```java
byte [] input = new byte[]{1,2,3,4,5,6,7,8};
int crc8 = CRC8.compute(input, 0);
```



## CRC16校验码

根据CRC16 多项式的不同， CRC16支持多种标准， 

相关类如下

|                   |                 |
| ----------------- | --------------- |
| CRC16 _IBM        | BM标准          |
| CRC16_CCITT_FALSE | CCITT FALSE标准 |
|                   |                 |

CRC16支持分段数据计算， 可通过update将数据进行连续处理，并通过getValue获取CRC值 

主要方法如下：

| 方法                                    | 说明                                  |
| --------------------------------------- | ------------------------------------- |
| void update(byte[] b)                   | 对数组进行CRC16运算                   |
| void update(byte[] b, int off, int len) | 对数组off开始len长度数据进行CRC16运算 |
| int getValue()                          | 获取CRC16结果                         |

例程：

```java
byte [] input = new byte[]{1,2,3,4,5,6,7,8};
CRC16_IBM crc16 = new CRC16_IBM();
crc16.update(input);
int crc = crc16.getValue(); //获取CRC16结果 
```

