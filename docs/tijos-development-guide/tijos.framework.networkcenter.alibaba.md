# 阿里云物联网平台客户端

阿里云物联网平台提供了一站式的设备接入、设备管理、监控运维、数据流转、数据存储、数据备份等服务，数据按照实例维度隔离，可根据业务规模灵活提升规格，具备高并发、高可用、低成本、易运维的特性，是企业设备上云的首选，请参考[企业物联网平台_设备接入_设备管理_监控运维_数据服务-阿里云 (aliyun.com)](https://www.aliyun.com/product/iot/iot_instc_public_cn?spm=a2c56.193971.9574222374.11.413925c8AihcNa&acm=lb-zebra-603469-8447333.1003.4.8003112&scm=1003.4.lb-zebra-603469-8447333.OTHER_15899164445591_8003112)

## Java包
tijos.framework.networkcenter.alibaba

## 阿里云客户端  - AliYunIoT类

AliYunIoT类中包含了阿里云客户端相关的操作，支持阿里云提供的各种设备能力， 包括物模型、子设备管理、引导服务、动态注册等等。
阿里云通过使用属性、事件、服务构成的物模型描述设备，在设备接入前需要先在阿里云物联网平台中建立相应的产品、物模型，以及对应的设备等等信息， 并获得产品和设备的名称及认证信息， 具体请参考相关的帮助文档。



### 基础方法

| 方法                                                         | 说明                       |
| ------------------------------------------------------------ | -------------------------- |
| static AliYunIoT getInstance()                               | 获得AliYunIot实例          |
| void start(String serverUrl, String productKey, String deviceName, String deviceSecret,IDataModelEventListener listener) | 启动并连接阿里云物联网平台 |
| void stop()                                                  | 断开连接并释放             |
| static String dynamicRegister(String productKey, String productSecret, String deviceName) | 一型一密设备注册           |
| static String getUrlFromBootstrap(String productKey, String deviceName) | 设备分发                   |



### 物模型相关方法
| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| int propertyPost(String params)                              | 设备属性上报， params为属性和值的JSON格式数据                |
| int eventPost(String serviceId, String params)               | 设备事件上报 serviceId -事件ID  params对应的JSON格式属性值   |
| void propertySetReply(long msgId, int code, byte[] data)     | 对于云端属性控制onPropertySetArrived指令回复，code=200 成功  |
| void asynServiceReply(long msgId, String serviceId, int code, byte[] data) | 对于云端异步服务调用onAsyncServiceInvokeArrived指令回复，code=200 成功 |
| void syncServiceReply(long msgId, String serviceId, String rrpcId, int code, byte[] data) | 对于云端同步服务调用onSyncServiceInvokeArrived指令回复，code=200 成功 |
|                                                              |                                                              |


## IDataModelEventListener 物模型事件监听

IDataModelEventListener 提供了物模型相关事件处理接口， 不应在事件中进行长时间的处理从而阻塞其它事件
| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| void onGenericReplyArrived(String product, String deviceName, long msgId, int code, String message, byte [] data) | 云端回复，如属性、事件上报等， code=200为成功 |
| void onPropertySetArrived(String product, String deviceName, long msgId, String params) | 收到云端控制指令 |
| void onAsyncServiceInvokeArrived(String product, String deviceName, long msgId, String serviceId, String params) |  收到云端异步服务调用事件  |
| void onSyncServiceInvokeArrived(String product, String deviceName, long msgId, String rrpdId, String serviceId, String params) |  收到云端同步服务调用事件  |



## 子设备管理相关方法

| 方法                                                | 说明                                            |
| --------------------------------------------------- | ----------------------------------------------- |
| void getSubDevices()                                | 获取子设备列表， 云端通过onTopoGetReply事件返回 |
| void addSubDevices(AliLinkSubDevice[] devices)      | 添加子设备，云端通过onTopoAddReply事件返回      |
| void deleteSubDevices(AliLinkSubDevice[] devices)   | 删除子设备，云端通过onTopoDeleteReply事件返回   |
| void loginSubDevices(AliLinkSubDevice[] devices)    | 子设备上线，云端通过onTopoDeleteReply事件返回   |
| logoffSubDevices(AliLinkSubDevice[] devices)        | 子设备离线，云端通过onTopoDeleteReply事件返回   |
| void registerSubDevices(AliLinkSubDevice[] devices) | 子设备注册，云端通过onRegisterReply事件返回     |


### 使用说明

在开发之间请先在阿里云物联网平台上建立相应的产品及物模型


### 调用流程如下


```java
...
// 设备密钥 由平台生成
String ProductKey = "a1QJjmusiPI";
String DeviceName = "gwdemo1";
String DeviceSecret = "71fab7d64714233e8757181ab6c9e28b";
    
// 启动并连接云平台
aliiot.start(serverUrl, ProductKey, DeviceName, DeviceSecret, new AliListener());

// 物模型中的属性上报
JSONObject properties = new JSONObject();
properties.put("LightStatus", 0);

int mid = aliiot.propertyPost(properties.toString());

System.out.println("mid " + mid);

// 物模型中的事件上报
JSONObject errorEvent = new JSONObject();
errorEvent.put("ErrorCode", 0);

String serviceId = "Error";
mid = aliiot.eventPost(serviceId, errorEvent.toString());
System.out.println("mid " + mid);

...
```

事件回调，物模型相关事件通过IDataModelEventListener事件监听器回调进行处理

```java

class AliListener implements IDataModelEventListener {
	@Override
	public void onGenericReplyArrived(String product, String deviceName, long msgId, int code, String message,
			byte[] data) {
		System.out.println("onGenericReplyArrived  " + product + " " + deviceName + " mid " + msgId + " code " + code
				+ " message " + message);
	}
	
    //收到云端属性控制命令
	@Override
	public void onPropertySetArrived(String product, String deviceName, long msgId, String params) {
		System.out.println("onPropertySetArrived ");

		// 控制指令解析
		JSONObject commands = new JSONObject(params);
		// 命令执行

		//

		// 控制指令回复
		JSONObject reply = new JSONObject();

		try {
			AliYunIoT.getInstance().propertySetReply(msgId, 200, reply.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    //收到云端异步服务调用命令
	@Override
	public void onAsyncServiceInvokeArrived(String product, String deviceName, long msgId, String serviceId,
			String params) {
		System.out.println("onAsyncServiceInvokeArrived ");

		// 解析服务参数
		JSONObject parameters = new JSONObject(params);

		// 执行服务

		// 返回结果
		int code = 200; // 200 - 成功
		JSONObject reply = new JSONObject();

		try {
			AliYunIoT.getInstance().asynServiceReply(msgId, serviceId, code, reply.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    //收到云端同步服务调用命令
	@Override
	public void onSyncServiceInvokeArrived(String product, String deviceName, long msgId, String rrpcId,
			String serviceId, String params) {
		System.out.println("onSyncServiceInvokeArrived ");

		// 解析服务参数
		JSONObject parameters = new JSONObject(params);

		// 执行服务

		// 返回结果
		int code = 200; // 200 - 成功
		JSONObject reply = new JSONObject();

		try {
			AliYunIoT.getInstance().syncServiceReply(msgId, serviceId, rrpcId, code, reply.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
```

