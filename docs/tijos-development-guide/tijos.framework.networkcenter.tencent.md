# 腾讯云物联网平台客户端

腾讯云物联网开发平台（IoT Explorer）为客户提供便捷的开发工具与服务，助力客户更高效的完成设备接入，并为客户提供应用开发及场景服务能力，帮助客户高效、低成本构建物联网应用。开发平台为客户提供了基于 SDK、通讯模组的设备接入方式，并通过灵活的协议支持及便捷的工具，降低客户在设备端的开发门槛。同时，开发平台通过 APP 开发服务、设备告警、数据分析等服务，可提升客户在智能制造、智能家居、安防监控、智慧农业等行业应用的构建效率。   详情请参考 [物联网开发平台_物联网开发工具_物联网应用开发_物联网一站式开发 - 腾讯云 (tencent.com)](https://cloud.tencent.com/product/iotexplorer)  



## Java包
tijos.framework.networkcenter.tencent

## 腾讯云物联网平台客户端  - IotExploreMqttClient类

IotExploreMqttClient类中包含了腾讯云物联网客户端相关的操作，支持腾讯云提供的各种设备能力， 包括物模型、子设备管理、动态注册等等。
腾讯云通过使用属性、事件、行为构成的数据模板描述设备，在设备接入前需要先在腾讯云物联网开发平台中建立相应的产品、数据模型，以及对应的设备等等信息， 并获得产品和设备的名称及认证信息， 具体请参考相关的帮助文档。


### 基础方法

| 方法                                                         | 说明                         |
| ------------------------------------------------------------ | ---------------------------- |
| static IotExploreMqttClient getInstance(String productId, String deviceName, String deviceSecret) | 获得IotExploreMqttClient实例 |
| void connect(IotExploreEventListener listener)               | 启动并连接物联网平台         |
| void disconnect()                                            | 断开连接并释放               |



### 物模型相关方法
| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| String propertyReport(JSONObject property)                   | 设备属性上报， params为属性和值的JSON格式数据                |
| String eventsReport(JSONArray events)                        | 设备事件上报 eventId -事件ID  params对应的JSON格式属性值     |
| void propertyControlReply(String clientToken, int code, String status) | 对于云端属性控制onPropertyControlArrived指令回复，code=0成功 |
| void actionResultReply(String clientToken, int code, String status, JSONObject response) | 对于云端异步服务调用onActionArrived指令回复，code=0成功      |

## IotExploreEventListener物模型事件监听

IotExploreEventListener提供了数据模型相关事件处理接口， 不应在事件中进行长时间的处理从而阻塞其它事件
| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| void onPropertyReportReply(String clientToken, int code, String status); | 属性上报云端回复 |
| void onEventReportReply(String clientToken, int code, String status); | 事件上报云端回复 |
| void onPropertyControlArrived(String clientToken, JSONObject params); | 收到云端属性控制指令 |
| void onActionArrived(String clientToken, String actionId, JSONObject params); |  收到云端行为调用事件  |



### 使用说明

在开发之间请先在腾讯云物联网开发平台上建立相应的产品及数据模型


### 调用流程如下


```java
...
// 设备密钥 由平台生成
String productId = "68NRL44HNK";
String deviceName = "device1";
String devicePSK = "PQ7Ja8O0/j9Bbm7WdW+TuQ==";
   
// 启动并连接云平台
IotExploreMqttClient txTempl = new IotExploreMqttClient(productId, deviceName, devicePSK);
txTempl.connect(new IotEventListener(txTempl));


// 物模型中的属性上报
JSONObject properties = new JSONObject();
properties.put("switch", 1);
properties.put("color", 0);
properties.put("brightness", i);
txTempl.propertyReport(jobj);

// 物模型中的事件上报
JSONObject jalarm = new JSONObject();
jalarm.put("alarm", 1);

String eventId = "alarm";
txTempl.eventReport(eventId,IotExploreMqttClient.EVENT_TYPE_INFO , jalarm);

...
```

事件回调，物模型相关事件通过IotExploreEventListener事件监听器回调进行处理

```java

class IotEventListener implements IotExploreEventListener {

	IotExploreMqttClient client = null;

	public IotEventListener(IotExploreMqttClient mqttClient) {
		this.client = mqttClient;
	}

	@Override
	public void onPropertyReportReply(String clientToken, int code, String status) {

		System.out.println("onPropertyReportReply " + clientToken + " code " + code + " status " + status);
	}

	@Override
	public void onPropertyControlArrived(String clientToken, JSONObject msg) {

		System.out.println("onPropertyControlArrived " + clientToken + " " + msg);

		int code = 0;
		String status = "OK";
		try {
			client.propertyControlReply(clientToken, code, status);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void onActionArrived(String clientToken, String actionId, JSONObject params) {
		System.out.println("onActionArrived " + actionId + " " + params);

		// check reply
		JSONObject response = new JSONObject();

		int code = 0;
		String status = "OK";
		try {
			response.put("result", 1);

			client.actionResultReply(clientToken, code, status, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void onEventReportReply(String clientToken, int code, String status) {
		System.out.println("onEventReportReply " + clientToken + " code " + code + " status " + status);
	}

	@Override
	public void onMqttConnected() {
		System.out.println("onMqttConnected");
	}

	@Override
	public void onMqttDisconnected(int error) {
		System.out.println("onMqttDisconnected");
	}

}
```

