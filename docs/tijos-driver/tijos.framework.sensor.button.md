# 按键类

tijos.framework.sensor.button中包含了与按键相关的类，如单按键，矩阵键盘等等，通过按键事件监听模式满足不同的按键应用场景。

包含类如下:

| 类名称      | 说明   |
| -------- | ---- |
| TiButton | 单按键类 |

## TiButton

TiJOS提供的单按键类支持按键事件监听；可支持激活电平配置为高电平激活和低电平激活两种，按键默认使用低电平激活。

TiJOS Button 包括如下类：

| 类名                    | 说明        |
| --------------------- | --------- |
| TiButton              | 单按键类      |
| TiButtonEventListener | 单按键事件监听接口 |

## TiButton的创建、绑定与使用

TiButton对象在创建时需要和具体总线设备绑定，具体绑定类型与其工作方式以及通讯协议有关，本对象绑定的设备总线类为 tijos.framework.devicecenter.TiGPIO；设备总线的使用请参考：tijos.framework.devicecenter。

构造器如下：

| 构造器                                      | 说明           |
| ---------------------------------------- | ------------ |
| TiButton(TiGPIO gpio, int signalPinID)   | 创建对象，默认低电平激活 |
| TiButton(TiGPIO gpio, int signalPinID, boolean highLevel) | 创建对象，激活电平可配置 |
| TiButtonEventListener()                  | 创建对象，按键事件监听  |

主要方法如下：

| 方法                   | 说明             |
| -------------------- | -------------- |
| long getEventTime()  | 获取按键事件时间，单位：us |
| int getSignalPinID() | 获取按键pin ID     |


## TiButton事件监听

TiButton的事件监听主要通过TiButtonEventListener事件回调来处理事件，事件类型包括

| 方法                                       | 说明     |
| ---------------------------------------- | ------ |
| void setEventListener(TiButtonEventListener lc) | 设置监听   |
| void onReleased(TiButton button)         | 按键按下事件 |
| void onPressed(TiButton button)          | 按键释放事件 |

创建TiButton事件监听对象：

```java
/*
 * 资源使用，
 * 创建事件监听对象并设置事件监听
 * 在事件监听中处理按键事件逻辑
 */			
OneButtonEventListener lc = new OneButtonEventListener();
buttonS1.setEventListener(lc);
```

事件处理：

```java
class OneButtonEventListener implements TiButtonEventListener {
	/**
	 * 按键按下事件处理
	 */
	public void onPressed(TiButton arg0) {
		System.out.println("onPressed"+"  time(us):"+arg0.getEventTime()+"  ButtonPinID:"+arg0.getPinID());
	}
	/**
	 * 按键释放事件处理
	 */
	public void onReleased(TiButton arg0) {
		System.out.println("onReleased"+"  time(us):"+arg0.getEventTime()+"  ButtonPinID:"+arg0.getPinID());
	}
}
```

TiButton类中他方法的使用请参考 TiButton 按键功能例程。