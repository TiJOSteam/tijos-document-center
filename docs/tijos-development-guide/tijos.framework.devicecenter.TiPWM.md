# TiPWM - 脉冲宽度调制发生器

脉冲宽度调制（Pulse-width modulation）简称PWM，是利用[微处理器](https://baike.baidu.com/item/%E5%BE%AE%E5%A4%84%E7%90%86%E5%99%A8)的数字输出来对[模拟电路](https://baike.baidu.com/item/%E6%A8%A1%E6%8B%9F%E7%94%B5%E8%B7%AF/5896)进行控制的一种非常有效的技术，广泛应用在从测量、通信到功率控制与变换的许多领域中。具体可参考https://baike.baidu.com/item/%E8%84%89%E5%86%B2%E5%AE%BD%E5%BA%A6%E8%B0%83%E5%88%B6?fromtitle=PWM&fromid=3034961或https://en.wikipedia.org/wiki/Pulse-width_modulation。

TiJOS Framework提供TiPWM来支持PWM功能，其将PWM按照port分组，共支持4组，组号范围0-3，即：port0-port3；其中每组内按照channel划分4通道，4个通道使用相同的脉冲频率，通道范围0-3，即：channel0-channel3；每组都支持指定模式的单独设置、每个通道都支持单独的占空比控制。

## Java包
tijos.framework.devicecenter

## TiPWM方法定义

TiPWM类中主要的方法：

| 方法                                       | 说明                                     |
| ---------------------------------------- | -------------------------------------- |
| TiPWM open(int portID, int... channelIDs) | 静态方法，通过指定port和channel集合打开PWM，返回TiPWM对象 |
| void close()                             | 关闭当前对象                                 |
| void changePeriod(int periodValue)       | 设置脉冲周期                                 |
| void changeChannelDuty(int channelID, int dutyValue) | 设置脉冲占空比                                |
| void updatePeriodAndDuty()               | 更新周期和占空比                               |

TiPWM类中他方法的技术说明请参考TiJOS Framework说明文档。

## TiPWM对象创建与销毁

TiPWM对象的创建需要调用open静态方法，传入指定port和channel集合参数，返回创建的接口对象。

TiPWM对象的销毁需要调用close方法。

```java
...
int pwmPort0 = 0;
int pwmChannel0 = 0; //定义channel集合
int pwmChannel1 = 1;
int pwmChannel2 = 2;
TiPWM pwm0 = TiPWM.open(pwmPort0, pwmChannel0, pwmChannel1, pwmChannel2);
...
...
...
pwm0.close();
```

## TiPWM输出控制

TiPWM的输出控制通过changePeriod、changeChannelDuty和updatePeriodAndDuty方法完成。

```java
...
Random random = new Random();//使用伪随机数引擎
pwm0.changePeriod(1000);//1Khz, 1000us
while(true) { 
	int ch0=0, ch1=0, ch2=0;
	int max=255;
	int min=0; 
	ch0 = random.nextInt(max)%(max-min+1) + min;
	ch1 = random.nextInt(max)%(max-min+1) + min;
	ch2 = random.nextInt(max)%(max-min+1) + min;
  	//改变占空比
	pwm0.changeChannelDuty(pwmChannel0, ch0);
	pwm0.changeChannelDuty(pwmChannel1, ch1);
	pwm0.changeChannelDuty(pwmChannel2, ch2);
  	//更新
	pwm0.updatePeriodAndDuty();
...
```

注意：脉冲频率和占空比的支持受平台特性限制，设置时须了解硬件平台特性。

具体可参考TiJOS Framework说明文档。