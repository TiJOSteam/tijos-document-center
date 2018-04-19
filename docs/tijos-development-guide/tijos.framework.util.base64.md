# base64 - BASE64编码

BASE64是网络上最常见的使用字符串传输8位字节码的编码方式，具体请参考<https://baike.baidu.com/item/base64> 或 <https://en.wikipedia.org/wiki/Base64>。

TiJOS Framework提供了base64工具类用于BASE64的编码和解码，并支持指定长度换行。

## Java包
tijos.framework.util

例程:

```java
byte [] input = new byte[] {1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};

//base64 encoding 
String base64 = Base64.encode(input);

//base64 decoding
byte [] temp = Base64.decode(base64);
```
