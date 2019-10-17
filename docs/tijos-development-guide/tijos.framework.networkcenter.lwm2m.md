# LwM2M - 物联网协议客户端

LwM2M(OMA Lightweight M2M)是适用于资源有限的终端设备管理的轻量级物联网协议, OMA Lightweight M2M 主要动机是定义一组轻量级的协议适用于各种物联网设备，因为M2M设备通常是资源非常有限的嵌入式终端，无UI, 计算能力和网络通信能力都有限。同时也因为物联网终端的巨大数量，节约网络资源变得很重要, 目前主流的NB-IoT运营商物联网平台多是基于LwM2M协议来支持设备接入.

## Java包
tijos.framework.networkcenter.lwm2m

## LwM2M客户端  - LwM2MClient类

LwM2MClient 类中包含了所有LwM2M客户端相关的操作,


主要方法如下：

| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| LwM2MClient getInstance()                 | 获得LwM2MClient实例  |
| void connect(String serverUrl, String endpoint, int bootstrap, int lifetime, ILwM2MMessageListener lc) | 连接到LwM2M服务器 serverUrl 格式为coap://192.168.0.10:5683或 coaps://192.168.0.10:5684 , endpoint 终端名称，一般为IMEI， bootstrap 服务器模式，一般为0， lifetime 注册生命周期, lc 消息回调接口|
| void start()                           | 启动 |
| void disconnect()                           | 断开连接 |
| void deregister()                           | 通知服务器注销 |
| int addResource(String uri, int cache)()    | 添加资源 uri 资源URI, cache 该资源数据缓存区个数, 可设置为1, 该缓存区中的数据会被异步依次发出 |
| int getRegisterStatus()                   |获取注册状态 |
| int setResourceValue(String uri, Object value)                    | 设置指定URI资源的值, 值将被发送到服务器|
| boolean isResourceObserved(String uri) | 指定URI资源是否被服务器订阅，只有被订阅的资源才能发送数据|
| boolean isBusy()                     | LwM2M 是否仍有请求未完成, 用于在退出应用或进入待机状态时检查请求是否完成 |



## ILwM2MMessageListener LwM2M 事件监听

ILwM2MMessageListener 提供了LwM2M相关事件处理接口
| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| int writeRequestArrived(String uri, boolean instance, ResourceValue[] rvs)     | 服务器端写资源操作事件，服务器端修改指定资源时触发该事件  |
| int executeRequestArrived(String uri, boolean instance, ResourceValue[] rvs)   | 服务器端执行资源操作事件  |
| ResourceValue[] readRequestArrived(String uri, boolean instance)               | 服务器端读资源操作事件, 在事件处理时返回所请求的值  |
| int rebootRequestArrived()                |  服务端重启动终端请求事件  |
| void transactionEventArrived(String uri, boolean result)                |  setResourceValue后收到服务器端确认事件 result 执行结果 true为成功 |
| void registerEventArrived(boolean registered)                |  服务器返回注册结果事件  registered 注册结果  true为成功  |
| void observeEventArrived(String uri, boolean observed)                |  服务器端订阅指定资源事件 observed 订阅结果  true 为成功  |

事件中的参数含义如下

uri - 事件有关的URI

instance - URI是否为实例, 即操作目标为该实例下所有资源

rvs -  资源ID及对应的值数组，支持一次操作多个资源

### 使用说明

在实际使用中需要对LWM2M服务器端对资源的支持情况进行了解

客户端使用流程一般为:

- 连接服务器 connect
- 添加设备支持的资源URI addResource
- 启动客户端 start
- 当有资源URI进行数据更新时 setResourceValue
- 当服务器端有请求下发时可在ILwM2MMessageListener事件中进行处理

### 调用流程如下

```java
...

//LWM2M 连接到服务器
LwM2MClient.getInstance().connect("coap://180.101.147.115:5683", TiNBIoT.getInstance().getIMEI(), 0, 600, new LWM2MMessageListener());

//添加支持资源URI
LwM2MClient.getInstance().addResource(URI_REPORT, 8);

//启动
LwM2MClient.getInstance().start();

//设置资源值 - 当该资源被服务器订阅时数据会被发送到服务器
LwM2MClient.getInstance().setResourceValue("/3300/0/5750", "12345678");

//断开连接
LwM2MClient.getInstance().disconnect();

```

## 更多说明

目前NB-IoT应用中, 运营商多使用LWM2M协议，如中国移动OneNet, 中国电信/华为OC平台等,各个平台在接入时都有不同, 如有需要请与我们联系。
