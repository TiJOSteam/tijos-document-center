# base64 - BASE64编码

BASE64是网络上最常见的使用字符串传输8位字节码的编码方式，具体请参考<https://baike.baidu.com/item/base64> 或 <https://en.wikipedia.org/wiki/Base64>。

TiJOS Framework提供了base64工具类用于BASE64的编码和解码，并支持指定长度换行。

## Java包
tijos.framework.util

## BASE64 编码  - Base64 

Base64 中包含了BASE64编解码相关的操作， 可将byte数组转为字符串，也可将BASE64编码的字符串还原为byte数组

主要方法如下：

| 方法                                                      | 说明                                                         |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| String encode(byte[] buf)                                 | 将byte数据进行BASE64编码                                     |
| String encode(byte[] buf, int tw)                         | 将byte数据进行BASE64编码，一行最多tw字符，超过自动加换行， tw=0时不加换行 |
| String encode(byte[] buf, int offset, int length, int tw) | 将byte数组从offset开始length长度进行BASE64编码，一行最多tw字符，超过自动加换行， tw=0时不加换行 |
| byte[] decode(String b64)                                 | BASE64解码，将BASE64编码字符串还原为byte数组                 |
|                                                           |                                                              |

例程:

```java
byte [] input = new byte[] {1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};

//base64 encoding 
String base64 = Base64.encode(input);

//base64 decoding
byte [] temp = Base64.decode(base64);
```
