import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.util.Delay;

/**
 * TiJOS的故事，屏幕文本滚动显示
 * 
 * @author tijos
 *
 */
public class TiJOSStory {
	// 第1屏字符串内容
	static String screen1 = "Hi, i am TiJOS. " + "I'm an operating" + "system that can " + "be programmed in";
	// 第2屏字符串内容
	static String screen2 = "java language.  " + "I'm trying to   " + "become a Android" + "in the Internet ";
	// 第3屏字符串内容
	static String screen3 = "of things.";

	public static void main(String[] args) {

		try {
			// I2C主机总线资源分配，I2C PORT0
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);	
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			// 通过屏幕循环打印
			while (true) {
				// 显示第1屏内容，等待2秒后清屏
				oled.output(screen1);
				Delay.msDelay(2000);
				oled.clear();
				// 显示第2屏内容，等待2秒后清屏
				oled.output(screen2);
				Delay.msDelay(2000);
				oled.clear();
				// 显示第3屏内容，等待2秒后清屏
				oled.output(screen3);
				Delay.msDelay(2000);
				oled.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
