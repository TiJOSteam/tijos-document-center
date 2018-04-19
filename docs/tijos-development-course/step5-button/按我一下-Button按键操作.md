# 按我一下-Button按键操作

例程说明：通过日志查看按键被按下和释放的过程。

## 第一步： 环境搭建

### 1. 软件环境

请参考《钛极OS(TiJOS)应用开发环境搭建》以及《HelloWorld 第一个钛极OS(TiJOS)应用》搭建环境并创建例程：ButtonSample。

### **2**. 硬件环境

例程所需（以TiKit-T600-ESP8266A开发套件为例）：

1. TiKit-T600-ESP8266A开发板；
2. TiButton模块；
3. 杜邦线2根；
4. USB线2根；

![Tikit.jpg](.\img\Tikit.jpg)

使用USB线分别将 TiKit-T600-ESP8266A开发板的两个USB口连接到电脑的USB端口，并使用TiDevManager查看主板的连接是否正常。当开发板和电脑连接成功后，按下开发板上的Reset键，日志窗口会将当前开发板的BOOT信息打印出来，如下图所示。

![TikitConect.png](.\img\TikitConect.png)

注意：为确保TiDevManager正常识别开发板，请将PC端的串口操作软件关闭，否则可能造成TiDevManager不能正常识别开发板。

## 第二步：应用开发

### 1. 硬件连接

所需硬件资源：TiGPIO port0的pin0。

将TiLED用杜邦线连接到TiKit-T600-ESP8266A开发板：

- GND<------>GND
- PIN0<------>B1

![TikitConectButton.jpg](.\img\TikitConectButton.jpg)

### 2. 例程编写

在之前创建的工程ButtonSample中新建OneButton.java文件，并引入以下包：

```java
import java.io.IOException;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.sensor.button.TiButton;
import tijos.util.Delay;
import tijos.framework.sensor.button.ITiButtonEventListener;
```

定义OneButtonEventListener事件监听类，并定义按键的按下事件和释放事件方法；

```java
class OneButtonEventListener implements ITiButtonEventListener {

	/**
	 * 按键按下事件处理
	 */
	public void onPressed(TiButton arg0) {
		System.out.println("onPressed"+"  time(us):"+arg0.getEventTime());		
	}

	/**
	 * 按键释放事件处理
	 */
	public void onReleased(TiButton arg0) {
		System.out.println("onReleased"+"  time(us):"+arg0.getEventTime());		
	}	
}
```

在主类OneButton中定义main方法，并定义所需要使用的TiGPIO port以及所需要使用的pin引脚(按照硬件设计所连接的pin编号)。然后创建TiGPIO对象gpio0并打开需要使用的pin编号。最后创建的buttonS1对象，并与对应的pin绑定。

```java
public class OneButton {
	/**
	 * 程序入口，由TiJOS调用
	 * @param args 入口参数， TiJOS中一直等于null
	 */
	public static void main(String[] args) {
      try {
          /*
           * 定义使用的TiGPIO port
           */
          int gpioPort0 = 0;
          /*
           * 定义使用的TiGPIO pin
           */
          int gpioPin0 = 0;

          /*
           * 资源分配，
           * 将gpioPort0与gpioPin0分配给TiGPIO对象gpio0
           */			
          TiGPIO gpio0 = TiGPIO.open(gpioPort0, gpioPin0);
          /*
           * 资源绑定，
           * 创建TiButton对象buttonS1并将gpio0和gpioPin0与其绑定
           */	
          TiButton buttonS1 = new TiButton(gpio0, gpioPin0);
```

按照上述步骤将资源分配完成后，创建事件监听对象并设置事件监听，在事件监听中处理按键逻辑。

```java
		OneButtonEventListener lc = new OneButtonEventListener();
		buttonS1.setEventListener(lc);		
```

## 第三步：结果演示

将上述例程按照《HelloWorld 第一个钛极OS(TiJOS)应用》中的方法编译并运行，然后对S1按键进行按下、释放操作，日志窗口会显示对应的按下和释放信息。

![logcat.png](.\img\logcat.png)

## 源码

源码请参考[OneButton.java](./src/OneButton.java)