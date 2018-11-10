import java.io.IOException;

import tijos.framework.devicecenter.TiADC;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * 咳一声灯亮了，延时声控开关
 * 
 * @author tijos
 *
 */
public class VoiceLight {

	public static void main(String[] args) {

		try {
			// GPIO资源分配，GPIO0的PIN2和PIN4脚
			TiGPIO gpio0 = TiGPIO.open(0, 2, 4);
			// GPIO资源分配，GPIO0的PIN4脚
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);	
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
		    oled.print(0, 0, "VoiceLight");

			// ADC资源分配，ADC0的CH0通道
			TiADC adc0 = TiADC.open(0, 0);
			// GPIO总线资源与继电器对象绑定
			TiRelay1CH relay = new TiRelay1CH(gpio0, 2);
			// 设置ADC参考电压1.0V，外部2倍分压
			adc0.setRefVoltageValue(1.0, 2);
			// 定义采集电压比较阈值:目前为15.0毫伏，需要根据实际情况调整。
			double threshold = 15.0;
			// 循环检测
			while (true) {
				// 检测到按钮按下
				if (adc0.getVoltageValue(0) * 1000 > threshold) {
					// 打开继电器
					relay.turnOn();
					// 延时10秒
					oled.print(2, 0, "Open switch");
					Delay.msDelay(10 * 1000);
				} else {
					// 关闭继电器
					relay.turnOff();
					oled.print(2, 0, "Close switch");
				}
				// 检测间隔100ms
				Delay.msDelay(100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
