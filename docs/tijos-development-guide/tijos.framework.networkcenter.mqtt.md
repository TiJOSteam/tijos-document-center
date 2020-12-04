# mqtt - MQTT 客户端

MQTT是一个物联网传输协议，它被设计用于轻量级的发布/订阅式消息传输，旨在为低带宽和不稳定的网络环境中的物联网设备提供可靠的网络服务， 请参考 <https://en.wikipedia.org/wiki/MQTT> 或 mqtt.org 了解更多信息。

目前主流的物联云厂商均支持MQTT， 如Amazon, 百度云，阿里云等等， TiJOS 提供符合MQTT 3.1.1标准协议的MQTT Client, 可与所有支持MQTT 协议的服务器直接连接， 如百度物联云 <https://cloud.baidu.com/product/iot.html>  及 阿里物联云 <https://www.aliyun.com/product/iot>.

## Java包
tijos.framework.networkcenter.mqtt

TiJOS MQTT Client 包括如下类：

| 类名                 | 说明         |
| ------------------ | ---------- |
| MqttClient         | Mqtt客户端    |
| MqttConnectOptions | Mqtt连接项设置  |
| IMqttMessageListener | Mqtt消息监听接口 |

TiJOS MQTT Client 支持TCP和SSL两种通讯方式， 符合MQTT3.1.1标准规范，支持QOS0,1,2, 支持高性能异步API,   在处理MQTT协议过程中通过事件回调返回操作结果， 可支持突发模式"burst-mode"客户端请求， 实现快速发布无等待， 避免客户端资源等待。

MQTT Client支持自动连接，默认断开连接后30秒自动重新建立连接。

## MQTT 连接配置

MQTT 连接配置通过MqttConnectOptions 类进行，可进行如下配置：

| 配置项            | MqttConnectOptions                    | 说明                                                      |
| ----------------- | ------------------------------------- | --------------------------------------------------------- |
| CleanSession      | setCleanSession(boolean)              | 设置 Client断开连接后Server是否应该保存Client的订阅信息   |
| UserName          | setUserName(String)                   | 用户名                                                    |
| Password          | setPassword(String)                   | 密码                                                      |
| LWT               | setWill(String, byte[], int, boolean) | "Last Will and Testament" (LWT), 具体请参考MQTT协议       |
| KeepAliveInterval | setKeepAliveInterval(int)             | 设置客户端与服务器之间最大空闲时间，以秒为单位，默认240秒 |

MQTT服务器地址及客户端ClientID通过MqttClient初始化时进行设置。

## MQTT 客户端 - MqttClient

| 接口                                                         | 说明                                                     |
| ------------------------------------------------------------ | -------------------------------------------------------- |
| void connect(String clientId, String serverUrl, int timeout, MqttConnectOptions options,IMqttMessageListener listener) | 建立连接                                                 |
| void disconnect()                                            | 断开连接                                                 |
| int getNetState()                                            | 获取网络状态 1 断开 2网络连接成功 3 正在连接  4 MQTT断开 |
| int subscribe(String topic, int qos)                         | TOPIC订阅                                                |
| int unsubscribe(String topic)                                | 取消订阅                                                 |
| int publish(String topic, byte[] payload, int qos, boolean retained) | 消息发布                                                 |
|                                                              |                                                          |



## MQTT 连接建立步骤

1. 通过MqttConnectOptions设置相应的连接参数:

```java
 MqttConnectOptions connOpts = new MqttConnectOptions();
 connOpts.setUserName(username);
 connOpts.setPassword(password);

```
2. 设置MQTT服务器地址及ClientID, 以百度物联云服务器为例:

```java
final String broker      = "tcp://tijos.mqtt.iot.gz.baidubce.com:1883";
final String clientId     = "mqtt_test_java_client1";
//mqtt客户端为单例
MqttClient mqttClient = MqttClient.getInstance();
```

3. 设置事件监听并连接服务器

```java
//连接超时10秒
int timeout = 10;
mqttClient.connect(clientId, broker, timeot, connOpts, new MqttEventLister());
```

4. 连接成功或失败时会通过IMqttMessageListener中onMqttConnected或onNetworkDisconnected返回

   

## MQTT 事件监听

MQTT的通过IMqttMessageListener事件回调来处理事件，事件类型包括：

| 事件                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void onNetworkConnected(boolean reconnect)                   | 当网络连接成功或重新连接成功时调用                           |
| void onNetworkDisconnected(int errcode)                      | 当网络断开或MQTT连接失败时调用                               |
| onMqttConnected()                                            | 当MQTT CONNECT消息返回成功时调用,意味着MQTT SERVER接受该连接 |
| publishMessageArrived(String topic, byte[] payload)          | 当服务器端发送topic更新时调用                                |
| void publishCompleted(int msgId, String topic, int result)   | 当客户端PUBLISH成功后调用                                    |
| void subscribeCompleted(int msgId, String topic, int result); | 当客户端SUBSCRIBE成功后调用                                  |
| void unsubscribeCompleted(int msgId, String topic, int result); | 当客户端UNSUBSCRIBE成功时                                    |

## MQTT Topic 发布和订阅

MQTT协议主要来进行topic的发布和订阅，即将数据发布到服务器和从服务器获得最数据更新，这主要通过MqttClient的publish和subscribe函数来完成：

```java
String topic        = "topic2";
String content      = "Message from MQTT IoT demo";
int Qos = 1;
boolean retain = false;

int msgId = mqttClient.subscribe(topic, Qos);
msgId = mqttClient.publish(topic, content.getBytes(), Qos, retain);
```

publish和subscribe 返回msgId 用于标识本次操作，当操作完成后的事件回调中可通过msgId或topic名称来获得当前事件的执行结果。

注意： 

- 有些物联云服务器仅支持QOS0和1 的发布和订阅， 不支持QOS2. 
- TiJOS MQTT发布订阅最大支持512字节数据， 超出部分将被抛弃
- 

具体可参考MQTT相关例程。