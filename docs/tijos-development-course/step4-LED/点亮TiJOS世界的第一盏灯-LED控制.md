# 点亮TiJOS世界的第一盏灯-LED控制

例程说明：通过编程实现控制LED的亮、灭。

## 第一步： 环境搭建

### 1. 软件环境

请参考《钛极OS(TiJOS)应用开发环境搭建》以及《HelloWorld 第一个钛极OS(TiJOS)应用》搭建环境并创建例程：ControlLEDSapmle。

### 2. 硬件环境

例程所需（以TiKit-T600-ESP8266A开发套件为例）：

1. TiKit-T600-ESP8266A开发板；

2. TiLED模块；

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

- 3.3V<------>3.3V

- PIN0<------>R(红色灯)


![TikitConectLED.jpg](.\img\TikitConectLED.jpg)

### 2. 例程编写

在之前创建的工程ControlLEDSapmle中新建ControlLED.java文件，并引入以下包：

```java
import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.transducer.led.TiLED;
import tijos.util.Delay;
```

首先在主类ControlLED中定义main方法，并定义所需要使用的TiGPIO port以及所需要使用的pin引脚（按照硬件设计所连接的pin编号）。然后创建TiGPIO对象gpio0并打开需要使用的pin编号。最后创建红色灯的TiLED对象，并与对应的pin绑定。

```java
public class ControlLED {
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
           * 定义使用的TiGPIO Pin
           */
          int gpioPin0 = 0;
          /*
           * 资源分配，
           * 将gpioPort与gpioPin0分配给TiGPIO对象gpio0
           */			
          TiGPIO gpio0 = TiGPIO.open(gpioPort0, gpioPin0);
          /*
           * 资源绑定，
           * 创建TiLED对象red并将gpio0和gpioPin0与其绑定
           */	
          TiLED red = new TiLED(gpio0, gpioPin0);
```

按照上述步骤将资源分配完成后，就可以使用gpio0对象中的turnOn方法和turnOff方法，控制对应灯的亮和灭，在循环中配合延时方法控制灯的闪烁频率，实现灯按照一定频率闪烁的功能，同时使用打印端口将灯的状态打印到日志中输出。

```java
          /*
           * 资源使用，
           * 控制灯的亮与灭
           */				
          while(true) {
                  red.turnOn();
                  System.out.println("redled is turned on");

                  Delay.msDelay(1000);

                  red.turnOff();
                  System.out.println("redled is turned off");

                  Delay.msDelay(1000);
        	}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
```

## 第三步：结果演示

将上述例程按照《HelloWorld 第一个钛极OS(TiJOS)应用》中的方法编译并运行，即可在硬件上实现控制灯的功能演示。

![logcat.png](.\img\logcat.png)

## 源码

请参考[ControlLED.java](./src/ControlLED.java)