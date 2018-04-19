# mqtt - MQTT 客户端

MQTT是一个物联网传输协议，它被设计用于轻量级的发布/订阅式消息传输，旨在为低带宽和不稳定的网络环境中的物联网设备提供可靠的网络服务， 请参考 <https://en.wikipedia.org/wiki/MQTT> 或 mqtt.org 了解更多信息。

目前主流的物联云厂商均支持MQTT， 如Amazon, 百度云，阿里云等等， TiJOS 提供符合MQTT 3.1.1标准协议的MQTT Client, 可与所有支持MQTT 协议的服务器直接连接， 如百度物联云 <https://cloud.baidu.com/product/iot.html>  及 阿里物联云 <https://www.aliyun.com/product/iot>.

## Java包
tijos.framework.networkcenter

TiJOS MQTT Client 包括如下类：

| 类名                 | 说明         |
| ------------------ | ---------- |
| MqttClient         | Mqtt客户端    |
| MqttConnectOptions | Mqtt连接项设置  |
| MqttClientListener | Mqtt消息监听接口 |
| MqttException      | Mqtt异常     |

TiJOS MQTT Client 支持TCP和SSL两种通讯方式， 符合MQTT3.1.1标准规范，支持QOS0,1,2, 支持高性能异步API,   在处理MQTT协议过程中通过事件回调返回操作结果， 可支持突发模式"burst-mode"客户端请求， 实现快速发布无等待， 避免客户端资源等待。

## MQTT 连接配置

MQTT 连接配置通过MqttConnectOptions 类进行，可进行如下配置：

| 配置项                | MqttConnectOptions                    | 说明                                       |
| ------------------ | ------------------------------------- | ---------------------------------------- |
| CleanSession       | setCleanSession(boolean)              | 设置 Client断开连接后Server是否应该保存Client的订阅信息    |
| UserName           | setUserName(String)                   | 用户名                                      |
| Password           | setPassword(String)                   | 密码                                       |
| LWT                | setWill(String, byte[], int, boolean) | "Last Will and Testament" (LWT), 具体请参考MQTT协议 |
| KeepAliveInterval  | setKeepAliveInterval(int)             | 设置客户端与服务器之间最大空闲时间，以秒为单位，默认60秒            |
| ConnectionTimeout  | setConnectionTimeout(int)             | 客户连接服务器超时设置，秒为单位，默认10秒                   |
| AutomaticReconnect | setAutomaticReconnect(boolean)        | 设置是否自动重新连接                               |

MQTT服务器地址及客户端ClientID通过MqttClient初始化时进行设置。

## MQTT 连接建立步骤

1. 通过MqttConnectOptions设置相应的连接参数:

```java
 MqttConnectOptions connOpts = new MqttConnectOptions();
 connOpts.setUserName(username);
 connOpts.setPassword(password);
 connOpts.setAutomaticReconnect(true);
```
2. 设置MQTT服务器地址及ClientID, 以百度物联云服务器为例:

```java
final String broker      = "tcp://tijos.mqtt.iot.gz.baidubce.com:1883";
final String clientId     = "mqtt_test_java_client1";

MqttClient mqttClient = new MqttClient(broker, clientId);
```

3. 设置事件监听并连接服务器

```
mqttClient.SetMqttClientListener(new MqttEventLister());
mqttClient.connect(connOpts, mqttClient);
```

4. 连接成功或失败时会通过MqttClientListener中onMqttConnectSuccess或onMqttConnectFailure返回, 用户也可以不使用突发模式直接进行topic的发布和订阅以提高性能。

   ​

MQTT 客户端通过异步事件的方式处理MQTT连接和断开响应, 消息调用过程如下：

   ![MQTT](.\img\MQTT.png)

   1. 事件connectComplete和connectionLost在网络TCP连接成功或断开时调用，如果设置了自动连接，当网络断开时MQTT Client会自动重新连接MQTT Server

   2. onMqttConnectSuccess和onMqttConnectFailure在MQTT 连接成功或失败时调用，

      ​

## MQTT 事件监听

MQTT的通过IMqttClientListener事件回调来处理事件，事件类型包括：

| 事件                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| connectComplete(Object, boolean)         | 当网络连接成功或重新连接成功时调用                        |
| connectionLost(Object)                   | 当网络断开或连接失败时调用                            |
| onMqttConnectSuccess(Object )            | 当MQTT CONNECT消息返回成功时调用,意味着MQTT SERVER接受该连接 |
| onMqttConnectFailure(Object, int )       | 当MQTT CONNECT消息返回失败时调用,意味着MQTT SERVER不接受该连接， 具体原因可通过第二个参数获得 |
| messageArrived(Object, String, byte[])   | 当服务器端发送topic更新时调用                        |
| publishCompleted(Object, int, String, int) | 当客户端PUBLISH成功后调用                         |
| subscribeCompleted(Object, int, String, int) | 当客户端SUBSCRIBE成功后调用                       |
| unsubscribeCompleted(Object, int, String, int) | 当客户端UNSUBSCRIBE成功时                       |

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

具体可参考MQTT相关例程。