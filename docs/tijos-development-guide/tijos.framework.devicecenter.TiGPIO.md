# TiGPIO - 通用输入/输出

General Purpose Input Output （通用输入/输出）简称为GPIO，或总线扩展器，能够提供额外的控制和监视功能，具体可参考https://baike.baidu.com/item/gpio或https://en.wikipedia.org/wiki/General-purpose_input/output。

TiJOS Framework提供TiGPIO来支持GPIO功能，其将GPIO按照port分组，共支持256组，组号范围0-255，即，port0-port255，其中每组内按照pin划分16条线，线号范围0-15，即，pin0-pin15，每条线都支持指定模式的单独设置。

## Java包
tijos.framework.devicecenter

## TiGPIO工作模式定义

| 工作模式           | 说明           |
| -------------- | ------------ |
| INPUT_FLOATING | 浮空输入模式，默认模式  |
| INPUT_PULLUP   | 上拉输入模式       |
| INPUT_PULLDOWN | 下拉输入模式       |
| OUTPUT_OD      | 开漏输出模式       |
| OUTPUT_PP      | 推挽输出模式       |


## TiGPIO事件模式定义

事件模式只有具备输入模式的pin能支持。

| 事件模式              | 说明       |
| ----------------- | -------- |
| EVENT_NONE        | 无事件，默认模式 |
| EVENT_RISINGEDGE  | 上升沿事件模式  |
| EVENT_FALLINGEDGE | 下降沿事件模式  |
| EVENT_BOTHEDGE    | 双沿事件模式   |



## TiGPIO方法定义

TiGPIO类中主要的方法：

| 说明                                     | 方法                                       |
| -------------------------------------- | ---------------------------------------- |
| 静态方法，通过指定port和pin集合打开指定GPIO，返回TiGPIO对象 | TiGPIO open(int portID, int... pinIDs)   |
| 关闭当前TiGPIO对象                           | void close()                             |
| 设置当前TiGPIO对象指定pin的工作模式                 | void setWorkMode(int pinID, int mode)    |
| 设置当前TiGPIO对象指定pin的事件参数，捕获周期单位：us       | void setEventParameters(int pinID, int evt, int captureFilter) |
| 读当前TiGPIO对象port数据，16位数据                | readPort()                               |
| 写当前TiGPIO对象port数据，16位数据                | writePort(int value)                     |
| 读当前TiGPIO对象指定pin电平                     | readPin(int pinID)                       |
| 写当前TiGPIO对象指定pin电平                     | writePin(int pinID, int levelValue)      |

TiGPIO类中他方法的技术说明请参考TiJOS JDK framework说明文档。



## TiGPIO对象创建与销毁

TiGPIO对象的创建需要调用open静态方法，传入指定port和pin集合参数，返回创建的接口对象。

TiGPIO对象的销毁需要调用close方法。

```java
...
int gpioPort0 = 0; //定义接口，port0
int gpioPin0 = 0;  //定义pin集合
int gpioPin1 = 1;
TiGPIO gpio0 = TiGPIO.open(gpioPort0, gpioPin0, gpioPin1); //打开port0，返回TiGPIO接口对象
...
...
...
gpio0.close(); //销毁当前对象
```



## TiGPIO模式设置

TiGPIO模式通过setWorkMode方法设置，设置参数为推挽输出模式和上拉输入模式。

```java
...
gpio0.setWorkMode(gpioPin0, TiGPIO.OUTPUT_PP); //推挽输出
gpio0.setWorkMode(gpioPin1, TiGPIO.INPUT_PULLUP); //上拉输入
...
```

注意：输入输出以及上下拉模式受平台特性限制，设置时须了解硬件平台特性。



## TiGPIO事件设置

TiGPIO事件通过setEventParameters方法设置，设置参数为事件类型和最小捕获周期。

最小捕获周期：指定周期内只能捕获一次事件，如：捕获周期为10us，在这个时间内发生了5次事件，则只能捕获第一次，其他事件自动丢弃。

事件的分发：TiJOS事件通过事件中心eventcenter统一管理和分发，TiGPIO设置了事件之后需要在事件中心注册GPIO类型事件监听回调。

```java
...
gpio0.setEventParameters(gpioPin1, TiGPIO.EVENT_FALLINGEDGE, 0);
...
TiEventCenter.getEventCenter().addListener(new TiGPIOEvent()); //向事件中心添加GPIO事件监听
...
```

```java
public class TiGPIOEvent implements ITiEventListener {
	@Override
	public TiEventType getType() {
		return TiEventType.GPIO;
	}

	@Override
	public void onEvent(ITiEvent evt) {
		TiGPIOEvent event =  (TiGPIOEvent)evt;
      	 System.out.println("pin:"event.getPin()+" event:"+event.getEvent()); //打印发生事件的																		   //pin和event
		}
	}
	...
	...
}
```

事件中心的详细使用方法请参考tijos.framework.eventcenter说明。

注意：事件类型受平台特性限制，设置时须了解硬件平台特性。



## TiGPIO输入输出

TiGPIO通过readPort、writePort和readPin、writePin完成输入输出操作。

```java
...
int value = gpio0.readPort(); //读接口数值，每个接口为16位
int level = gpio0.readPin(gpioPin1); //读取pin1电平，1：高电平   0：低电平
...
gpio0.writePort(0x01); //向接口写数值0x01
gpio0.writePort(0x00); //向接口写数值0x00
...
gpio0.writePin(gpioPin0, 1); //向pin0写高电平
gpio0.writePin(gpioPin0, 0); //向pin0写低电平
...
```



具体可参考TiJOS Framework说明文档。