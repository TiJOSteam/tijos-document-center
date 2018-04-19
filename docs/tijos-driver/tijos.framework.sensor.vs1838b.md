# VS1838BNEC 红外线传感器类

tijos.framework.sensor.vs1838b中包含了与VS1838B红外接收传感器相关的类，可通过接收事件监听满足不同的红外应用场景。

包含类如下:

| 类名称          | 说明               |
| ------------ | ---------------- |
| TiVS1838BNEC | VS1838B类，NEC编码模式 |

## TiVS1838BNEC

TiJOS提供的TiVS1838BNEC类支持接收事件监听。

TiJOS VS1838B 包括如下类：

| 类名                        | 说明            |
| ------------------------- | ------------- |
| TiVS1838BNEC              | 红外接收类，NEC编码模式 |
| TiVS1838BNECEventListener | 红外接收事件监听接口    |

## TiVS1838BNEC的创建、绑定与使用

TiVS1838BNEC实例在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本实例绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。

构造器如下：

| 构造器                                      | 说明          |
| ---------------------------------------- | ----------- |
| TiVS1838BNEC(TiGPIO gpio, int dataPinID) | 创建实例        |
| TiVS1838BNECEventListener()              | 创建实例，接收事件监听 |

主要方法如下：

| 方法                 | 说明         |
| ------------------ | ---------- |
| int getAddress()   | 获取接收到的地址   |
| int getCommand()   | 获取接收到的命令   |
| int getDataPinID() | 获取数据pin ID |

## TiVS1838BNEC事件监听

TiVS1838BNEC的事件监听主要通过TiVS1838BNECEventListener事件回调来处理事件，事件类型包括

| 方法                                       | 说明   |
| ---------------------------------------- | ---- |
| void setEventListener(TiVS1838BNECEventListener lc) | 设置监听 |
| void cmdReceived(TiVS1838BNEC vs1838b)   | 接收事件 |
| void cmdRepeat(TiVS1838BNEC vs1838b)     | 重复事件 |

创建TiVS1838BNEC事件监听对象：

```java
		
/*
 * 资源使用，
 * 创建事件监听对象并设置事件监听
 * 在事件监听中处理接收事件逻辑
 */			
TiVS1838BNECEventListener lc = new TiVS1838BNECEventListener();
vs1838bNec.setEventListener(lc);
```

事件处理：

```java
class VS1838BNECEventListener implements TiVS1838BNECEventListener {
	/**
	 * 接收事件处理
	 */
	public void cmdReceived(TiVS1838BNEC arg0) {
		System.out.println("Received:"+arg0.getAddress()+","+arg0.getCommand());
	}

	/**
	 * 接收重复事件处理
	 */
	public void cmdRepeat(TiVS1838BNEC arg0) {
		System.out.println("Repeat:"+arg0.getAddress()+","+arg0.getCommand());
	}	
}
```

TiVS1838BNEC类中他方法的使用请参考TiVS1838BNEC红外遥控接收功能例程。