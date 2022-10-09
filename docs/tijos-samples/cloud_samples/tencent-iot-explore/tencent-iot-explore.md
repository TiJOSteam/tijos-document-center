# 基于钛极OS(TiJOS)的腾讯云IoT接入案例

腾讯云物联网开发平台（IoT Explorer）为各行业的设备制造商、方案商及应用开发商提供一站式设备智能化服务。平台提供海量设备连接与管理能力及小程序应用开发能力，并打通腾讯云基础产品及 AI 能力，提升传统行业设备智能化的效率，降低用户的开发运维成本，助力用户业务发展。

可通过如下链接访问腾讯物联网开发平台： 

[物联网开发平台 - 控制台 (tencent.com)](https://console.cloud.tencent.com/iotexplorer)

同时， 腾讯云物联网平台提供了“腾讯连连” 小程序， 用户可通过该小程序对设备进行控制。 

钛极OS(TiJOS)内置腾讯IoT Explore平台支持, 可快速实现该平台的设备接入，下面以钛极TiGW200(4G-Cat1)边缘计算网关为例说明如果如何快速接入腾讯云物联网平台。 

## 代码说明

源码请参考  <https://github.com/TiJOSApp/tijos-tencent-iot-explore>



## 准备工作 - 创建腾讯云产品模型

腾讯云通过数据模板定义了产品的属性，事件， 行为， 并通过标识符和数据类型进行数据交互， 在进行设备接入之前首先要在腾讯云物联网开发平台里创建并定义产品的数据模型。 

在腾讯云中通过如下流程进行模型开发：

### 创建项目

在开发平台中首先新建一个项目，如下所示：

![image-20201209111711658](.\img\image-20201209111711658.png)

### 新建产品

在项目中新建一个产品

![image-20201209111851305](
	.\img\image-20201209111851305.png)

设备产品属性，选择已有产品品类，平台会自动生成标准的数据模板， 为方便测试可选择智慧生活下的标准产品灯，

选择密钥认证、TiGW200为4G设备， 数据协议使用数据模板

![image-20201209112549815](.\img\image-20201209112549815.png)



### 产品数据模板

由于使用的是标准产品品类， 平台会自动生成相应的数据模板，用户也可添加自定义属性，事件，行为,如下所示：

![image-20201209115030450](.\img\image-20201209115030450.png)



### 腾讯连连小程序/APP配置

腾讯平台提供了标准小程序及APP选项， 方便用户通过手机直接访问和测试设备， 选择上方的交互开发即可进行设置。

![image-20201209133011425](.\img\image-20201209133011425.png)

### 新建设备

选择页面上方的设备调试， 即可新建设备并进行测试和调试

![image-20201209133300855](.\img\image-20201209133300855.png)

### 设备调试

在新建设备右边的操作选项中选择“调试“操作，即可进行设备调试和测试

![image-20201209133445497](.\img\image-20201209133445497.png)

## 通过钛极OS(TiJOS)接入腾讯云

钛极OS是钛云物联开发的物联网操作系统 ，可运行于单片机、物联网模组等低资源设备中， 支持用户通过Java语言进行进行硬件功能开发，并提供了各种云端接入组件包， 并内置支持腾讯云物联网平台接入。 

### 准备工作

1. 准备一台内置钛极OS(TiJOS)的设备， 建议使用支持4G的TiGW200边缘计算网关
2. 安装Eclipse及TiStudio开发环境， 具体请参考TiGW200开发指南文档或访问<钛极OS文档中心>[http://doc.tijos.net]
3.  将TiGW200进入开发模式并连接电脑USB口
4. 在腾讯开发平台中建立新设备并获取到设备的密钥认证信息， 包括产品ID， 设备名称，设备密钥

### 应用开发

在Ecclipse中新建TiJOS Application应用，腾讯云平台接入通过钛极OS(TiJOS)内置的IotExploreMqttClient类及IotExploreEventListener事件进行支持，用户可参考相关文档和例程接合实际应用进行开发，并通过编译下载到TiGW200设备中进行测试。 

具体可参考[腾讯云IotExplore平台客户端 - 文档中心 (tijos.net)](http://doc.tijos.net/docstore/tijos-development-guide/tijos.framework.networkcenter.tencent/)

### 代码编译下载

从GitHub下载已完成的代码，通过Eclipse导入到Workspace中, 在Eclipse中可以看到工程基于TiJOS Framework开发，所有源码和API都是Java代码，TiJOS Framework对各种外设传感器及网络做了抽象封装，通过API可方便快捷的操作外设硬件。

在完成代码修改后，通过选中工程右键弹出菜单点击Run as --> TiJOS Application实时下载至硬件中运行。 

### 代码解析

#### 启动4G网络

在程序启动时， 先启动4G网络

```java
启动4G网络 超时30秒
TiLTE.getInstance().startup(30);
```

#### 连接腾讯云IoT Explore平台

使用设备密钥信息连接云平台

```java
//设备密钥信息	
String productId = "68NRL44HNK";
String deviceName = "device1";
String devicePSK = "PQ7Ja8O0/j9Bbm7WdW+TuQ==";

//腾讯云平台客户端
IotExploreMqttClient txTempl = new IotExploreMqttClient(productId, deviceName, devicePSK);

//启动连接并设置事件监听
txTempl.connect(new IotEventListener(txTempl));
```

此时，可在设备的上下线日志看到设备连接成功

![image-20201209135407192](.\img\image-20201209135407192.png)

#### 上报设备属性

从传感器读取当前状态，并通过IotExploreMqttClient提供的属性上报接口上报相关属性， 属性必须与在平台定义的数据模型相一致

```java
//通过JSON对象进行设备属性上报
JSONObject jobj = new JSONObject();
jobj.put("switch", 1);
jobj.put("color", 0);
jobj.put("brightness", 10);

txTempl.propertyReport(jobj);
```

此时， 可在设备的在线调试页面看到相关的通讯数据

![image-20201209135714693](.\img\image-20201209135714693.png)

#### 下发指令

钛极OS(TiJOS)通过事件回调的方式处理平台下发指令，用户需要在应用中实现IotExploreEventListener接口, 当有属性控制指令下发时通过onPropertyControlArrived来进行回调处理，并可在处理完成后通过propertyControlReply回复平台， 当有行为调用下发时通过onActionArrived进行回调处理， 并可在处理完成后通过actionResultReply回复平台，如下所示：

```java
class IotEventListener implements IotExploreEventListener {

......
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
......
}
```

以上的属性控制指令及行为调用均可通过在线调试页面来进行操作，并可在TiStudio中看到相关日志。



## 总结

本案例实现了最基本的网络接入和收发数据，实际中也可进一步通过腾讯云的策略对数据进行转发，同时基于云服务对数据做处理分析，以及大数据运算和存储等。