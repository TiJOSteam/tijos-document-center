# 华为云OceanConnect物联网平台客户端

华为云物联网平台（简称物联网平台）提供海量设备的接入和管理，配合华为云其他产品同时使用，帮助快速构筑物联网应用。

使用物联网平台构建一个完整的物联网解决方案主要包括3部分：物联网平台、业务应用和设备。

- 物联网平台作为连接业务应用和设备的中间层，屏蔽了各种复杂的设备接口，实现设备的快速接入；同时提供强大的开放能力，支撑行业用户快速构建各种物联网业务应用。
- 设备可以通过固网、2G/3G/4G/5G、NB-IoT、Wifi等多种网络接入物联网平台，并使用LWM2M/CoAP或MQTT协议将业务数据上报到平台，平台也可以将控制命令下发给设备。
- 业务应用通过调用物联网平台提供的API，实现设备管理、数据上报、命令下发等业务场景。

详情请参考 https://support.huaweicloud.com/iothub/index.html

OceanConnect 支持多种方式接入，包括MQTT, LWM2M等等， 钛极OS(TiJOS)对MQTT进行了封装方便用户使用， LWM2M用户基于标准LWM2M接口使用即可。 



## Java包
tijos.framework.networkcenter.huawei

## MQTT客户端  - OceanConnect类

OceanConnect类中包含了OceanConnect MQTT客户端相关的操作，支持OceanConnect提供的各种设备能力， OceanConnect通过服务和属性构成的产品模型描述设备，在设备接入前需要先在OceanConnect平台中建立相应的产品模型，以及对应的设备等等信息， 并获得产品和设备的名称及认证信息， 具体请参考相关的帮助文档。


### 基础方法

| 方法                                                         | 说明                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| void connect(String deviceId, String secret, OceanConnectEventHandler eventHandler) | 连接物联网平台， 参数分别为：设备ID，设备密钥及事件回调 |
| void disconnect()                                            | 断开连接并释放                                          |



### 设备影子相关方法
| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void reportProperties(ServiceProperty... properties)         | 设备属性上报,properties为指定服务的属性值, 支持一次上报多个服务属性 |
| void respondCommand(String requestId, CommandRsp commandRsp) | 云端命令响应回复, requestId: 对应的请求id, commandRsp命令回复结果及对应产品模型中的属性值。 |
|                                                              |                                                              |

## AbstractOceanConnectEventHandler 事件监听

AbstractOceanConnectEventHandler 提供了产品模型相关事件处理接口， 不应在事件中进行长时间的处理从而阻塞其它事件
| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| void onPropertiesSet(String requestId, PropsSet propsSet); | 收到云端属性控制指令 |
| void onCommand(String requestId, Command command); |  收到云端同步命令  |



### 使用说明

在开发之间请先在平台上建立相应的产品及模型


### 调用流程如下


```java
...
//设备ID 由平台生成
String deviceId = "5fd3232ccbfe2f02ce6d4d42_867435057486946";
//设备密钥 由用户设置或平台生成
String secret ="867435057486946";

// 启动并连接云平台
OceanConnect oc = new OceanConnect();
oc.connect(deviceId, secret, new MyEventHandler(oc));

//模型中的服务属性上报
//服务名称
ServiceProperty sp = new ServiceProperty("Connectivity"); 
//属性名称及对应的值
sp.setProperty("rssi", TiLTE.getInstance().getRSSI());
sp.setProperty("cellId", TiLTE.getInstance().getCellInfo().getCI());
//时间戳
sp.setEventTime(System.currentTimeMillis()); 

//上报服务属性
oc.reportProperties(sp);


...
```

事件回调，物模型相关事件通过AbstractOceanConnectEventHandler 事件监听器回调进行处理

```java
class MyEventHandler extends AbstractOceanConnectEventHandler  {
....
    //收到云端命令
@Override
	public void onCommand(String requestId, Command command) {
		System.out.println("onCommand " + requestId);	
		
       //回复命令执行结果 
		CommandRsp rsp = new CommandRsp(CommandRsp.SUCCESS);
		try {
			oc.respondCommand(requestId, rsp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

    //收到云端属性配置请求
	@Override
	public void onPropertiesSet(String requestId, PropsSet propsSet) {
		System.out.println("onPropertiesSet");	
		
        //回复发生配置执行结果 
		try {
			oc.respondPropsSet(requestId, IotResult.SUCCESS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    ....
}
```

