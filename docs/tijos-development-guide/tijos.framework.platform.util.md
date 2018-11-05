# platform util - 平台相关工具类

tijos.framework.platform.util中提供了一些与平台工具特性相关的类，如：KV存储, 共享缓存区。

## Java包
tijos.framework.platform.util

包含类如下:

| 类名称        | 说明         |
| ---------- | ---------- |
| KeyValueStorage | KV存储 |
| SharedBuffer    | 共享缓存区       |

## KeyValueStorage - Key/Value键值对存储

KeyValueStorage提供了一种通过键值对进行数据存储的方式，方便用户存储一些自定义设置

### 基本概念
group : 分组
key : 键
value : 值


主要方法如下：

| 方法                               | 说明          |
| -------------------------------- | ----------- |
| void writeValue(String group, String key, byte[] value     |  将值写入到指定组中的键    |
|byte[] readValue(String group, String key) | 读取组中指定键的值     |
| void deleteGroup(String group)              | 删除指定组 |
| void deleteKey(String group, String key)   | 删除组中的key |

调用过程举例：

```java
...

KeyValueStorage kv = KeyValueStorage.getInstance();

String group = "settings";
String key = "ip";
String value = "192.168.0.1";

//写入指定值
kv.writeValue(group, key, value.getBytes());

//获取指定值
String ip = new String(kv.readValue(group, key));

```

## SharedBuffer - 共享缓存区

在内存中开辟了一块专用的区域用于不同应用之间的数据共享， 同时对于NB-IoT等模组该区域也是一块休眠数据不丢失区域，可用于存储临时数据。


主要方法如下：

| 方法                           | 说明                                       |
| ---------------------------- | ---------------------------------------- |
| int getSize()                | 获取共享缓存区大小。 |
| int read(byte[] dest, int destOffset, int srcOffset, int length)    | 从共享缓存区读取数据 |
| int write(byte[] src, int srcOffset, int destOffset, int length)| 将数据写入到共享缓存区 |


调用过程举例：

```java
...

//写入数据到共享缓存区
byte [] data = "test".getbytes();
SharedBuffer.getInstance().write(data, 0, 0, data.length);

//从共享缓存区读取数据 
SharedBuffer.getInstance().read(data, 0, 0, 4);

...
```