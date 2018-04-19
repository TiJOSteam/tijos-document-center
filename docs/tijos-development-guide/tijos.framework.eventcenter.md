#  eventcenter - 硬件事件中心

TiJOS硬件事件中心主要用于与硬件设备总线相关的事件监听处理， 如GPIO事件等等， 类似于硬件中的中断处理， 与事件相关的硬件相关驱动均通过事件中心进行处理，如TiButton等。

## Java包
tijos.framework.eventcenter

## 事件类型 - TiEventType

硬件事件类型有多种， 目前TiJOS支持GPIO事件和USB事件， 随着总线类型的增加，相应的事件类型也会增加。

| 事件枚举 | 说明         |
| -------- | ------------ |
| GIPO     | GIPO总线事件 |
| KEYBOARD | 键盘按键事件 |
| USB      | USB总线事件  |

## 事件监听器 - ITiEventListener

事件监听接口ITiEventListener用于支持事件回调，需要处理总线事件的传感器驱动均需要实现该接口。

| 事件接口                  |                                       |
| --------------------- | ------------------------------------- |
| onEvent(ITiEvent evt) | 当事件发生时，由事件中心进行调用, ITiEvent中包含了事件的具体信息 |
| getEventType()        | 返回事件类型，如TiEventType.GPIO              |



## 事件注册及事件处理

当需要处理硬件事件时，需要实现ITiEventListener接口并在事件中心中进行注册， 事件中心通过getEventCenter来获取，当事件发生时, 在OnEvent中进行相应事件的处理，一般过程如下：

```java
//GPIO事件监听器
public class TiGPIOEventHandler implements ITiEventListener
{
	//在事件中心中注册GPIO事件
	TiGPIOEventHandler()
	{
    	TiEventCenter.getEventCenter().addListener(this);
	}
	
 	@Override
	public TiEventType getEventType() {
		return TiEventType.GPIO;
	}
	
	//当事件发生时, onEvent被调用
	@Override
	public void onEvent(ITiEvent evt) 
	{
		//转换为指定的事件类进行处理
		TiGPIOEvent event =  (TiGPIOEvent)evt;
      	if(event.getPin() == Pin1) 
      	{
			switch(event.getIOEvent())
			{
				//xxx
			}
		}
	}	
}
```

注意

1. 当同类型总线事件发生时，每个事件都会进入OnEvent调用中，驱动中应对发生事件的PIN进行过滤，只处理与自己相关的事件。

2. 在OnEvent中应尽快完成事件处理，以免影响后续事件， 对于处理时间较长的事件可在一个新的线程中进行处理从而避免事件堆积

   ​

## GPIO事件 - TiGPIOEvent

TiGPIOEvent代表GPIO总线事件， 派生自ITiEvent， 一般在OnEvent中使用，包含了本次GPIO事件发生时的相关信息：

| 函数                    | 说明                                       |
| --------------------- | ---------------------------------------- |
| TiEventType getType() | 获取事件类型，总返回TiEventType.GPIO               |
| long getTime()        | 获取事件发生时间，单位为微妙                           |
| int getPort()         | 获取事件发生的GPIO端口                            |
| int getPin()          | 获取事件发生的GPIO所属端口的管脚                       |
| int getIOEvent()      | 获取GPIO事件类型，包括EVENT_NONE，EVENT_RISINGEDGE，EVENT_FALLINGEDGE，EVENT_BOTHEDGE，具体请参考TiGPIO类说明 |

具体可参考相关GPIO总线例程。

## 键盘按键事件 - TiKeyboardEvent

TiKeyboardEvent代表钛极OS(TiJOS) 按键事件， 派生自ITiEvent， 一般在OnEvent中使用，包含了本次按键事件发生时的相关信息。

| 函数                  | 说明                                     |
| --------------------- | ---------------------------------------- |
| TiEventType getType() | 获取事件类型，总返回TiEventType.KEYBOARD |
| long getTime()        | 获取事件发生时间，单位为微妙             |
| int getId()           | 获取事件发生的按键ID                     |
| int getEvent()        |                                          |

