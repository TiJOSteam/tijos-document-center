# CoAP - 域名解析系统协议客户端

CoAP（Constrained Application Protocol）是专用于低资源硬件的物联网协议, 它是一种基于UDP的应用层协议, 支持URL方式访问,就像HTTP请求一样,支持GET,POST,PUT,DELETE等操作。

## Java包
tijos.framework.networkcenter.coap

## CoAP客户端  - CoAPClient类

CoAPClient 类中包含了所有CoAP客户端相关的操作,
CoAP协议一般包含4种请求, 包括POST,GET,PUT,DELETE 通过向指定URI来发送相关请求与服务器端进行交互

### 消息类型
CoAP采用与HTTP协议相同的请求响应工作模式。CoAP协议共有4中不同的消息类型。
CON——需要被确认的请求，如果CON请求被发送，那么对方必须做出响应。
NON——不需要被确认的请求，如果NON请求被发送，那么对方不必做出回应。
ACK——应答消息，接受到CON消息的响应。
RST——复位消息，当接收者接受到的消息包含一个错误，接受者解析消息或者不再关心发送者发送的内容，那么复位消息将会被发送。

在实际使用中CON最为常用

### URI 资源
一个CoAP资源可以被一个URI所描述, URI中不包含服务器及端口, 例如 /sensors/temperature

### ContentType 内容类型
用于标识CoAP请求的内容类型, 包括 APPLICATION_TEXT_PLAIN/APPLICATION_XML/APPLICATION_JSON等等, 与CoAP服务器提供的数据格式有关。 


主要方法如下：

| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| CoAPClient getInstance()                 | 获得CoAPClient实例  |
| void connect(String serverUrl)                           | 连接到CoAP服务器 serverUrl 格式为coap://192.168.0.10:5683 或 coaps://192.168.0.10:5684 |
| void void disconnect()                           | 断开连接 |
| void void setMessageType(int messageType) | 设置消息请示类型, 包括 0-CON， 1-NON, 2-ACK, 3-RST |
| int post(String uri, int contentType, byte[] content)                    |通过CoAP POST请求将数据发向指定uri, contentType 内容类型, 如APPLICATION_TEXT_PLAIN, APPLICATION_JSON 等等, 返回消息ID |
| int get(String uri, int contentType)                    | 向指定URI发送GET请求，返回消息ID|
| int put(String uri, int contentType, byte[] content)                    | 通过CoAP PUT请求将数据发向指定uri，返回消息ID |
| int delete(String uri)                    | 向指定URI发送DELETE请求，返回消息ID |
| boolean isBusy()                     | CoAP 是否仍有请求未完成, 用于在退出应用或进入待机状态时确保COAP请求已完成 |




## ICoAPMessageListener CoAP 事件监听

ICoAPMessageListener 提供了CoAP相关事件处理接口
| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| void getResponseArrived(String uri, int msgid,  boolean result, int msgCode, byte[] payload)                | GET消息返回事件  |
| void postResponseArrived(String uri, int msgid, boolean result, int msgCode)                | POST消息返回事件  |
| void putResponseArrived(String uri, int msgid, boolean result, int msgCode)               |  PUT消息返回事件  |
| void deleteResponseArrived(String uri, int msgid, boolean result, int msgCode)                |  DELETE消息返回事件  |

事件中的参数含义如下

uri - 事件有关的URI

msgid - 对应的请求消息ID

result -  true:请求成功 false: 请求失败

msgCode - CoAP返回代码


### 使用说明

在实际使用中一般使用POST请求将数据发送到指定URI， 并通过GET请求获取指定URI的数据


### 调用流程如下


```java
...

//CoAP 服务器url
String url = "coap://coap.tijcloud.com:5683";
String test_uri = "/test";

//获取CoAP客户端实例
CoAPClient coapClient = CoAPClient.getInstance();

//连接到服务器
coapClient.connect(url);

//设置事件监听
coapClient.setMessageListener(new CoAPMessageListener());

//发送POST请求到URI
String content = "test";
coapClient.post(temperature_uri, CoAPClient.APPLICATION_TEXT_PLAIN, content.getBytes());

//GET请求获取指定URI值, 结果通过getResponseArrived事件返回
coapClient.get(temperature_uri, CoAPClient.APPLICATION_TEXT_PLAIN);

//等待所有处理完成
while(coapClient.isBusy()) {
	Delay.msDelay(1000);
	System.out.println("Busy ..");
}

//断开连接
coapClient.disconnect();
```
