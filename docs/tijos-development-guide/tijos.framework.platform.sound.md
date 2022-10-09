# 音频

音频操作相关的类，如TTS等

## Java包
tijos.framework.platform.sound

## 将文字转换为语音输出 Text to Speech - TiTTS

TiTTS中包含了所有与TTS文字转换成语音输出相关操作 
TiTTS为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                          | 说明                         |
| ----------------------------- | ---------------------------- |
| TiTTS getInstance()           | 获取TTS实例                  |
| void play(byte[] soundStream) | 播放文字流-UTF8格式          |
| void forceStop()              | 停止播放                     |
| void setSpeed(int percent)    | 设置播放速度，0-100， 默认50 |
| void setVolume(int percent)   | 设置音量，0-100， 默认50     |
| void setPitch(int percent)    | 设置音高， 0-100， 默认50    |
|                               |                              |

注意：TTS中播放的文字流必须是UTF-8格式， 中文固定字符串播放可通过TiDevManager中的UTF8转换功能转换为数组进行播放， 通过网络或其它方式获取的字符串可预先转换为UTF-8格式之后发送到网关进行播放。



### TiTTS例程

大多数情况下使用play即可满足需求

```java
...
//中文需转成UTF-8格式, 可通过TiDevManager中的utf-8转换功能
//微信收款 100 元 转换为UTF-8 byte 数组 
byte[] content = { (byte) 0xE5, (byte) 0xBE, (byte) 0xAE, (byte) 0xE4, (byte) 0xBF, (byte) 0xA1,
		(byte) 0xE6, (byte) 0x94, (byte) 0xB6, (byte) 0xE6, (byte) 0xAC, (byte) 0xBE, (byte) 0x20,
		(byte) 0x31, (byte) 0x30, (byte) 0x30, (byte) 0x20, (byte) 0xE5, (byte) 0x85, (byte) 0x83 };

//通过音频播放输出
TiTTS.getInstance().play(content);

```





