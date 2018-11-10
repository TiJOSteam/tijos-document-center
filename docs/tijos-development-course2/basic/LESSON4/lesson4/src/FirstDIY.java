import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.dht.TiDHT;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.util.Delay;


/**
 * DIY温湿度计，温湿度采集
 * 
 * @author andy
 *
 */
public class FirstDIY {

	public static void main(String[] args) {
		
		try {
			// GPIO资源分配，GPIO0的PIN3脚
			TiGPIO gpio0 = TiGPIO.open(0, 3);
			// I2C主机总线资源分配，I2C PORT0
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// GPIO总线资源与温湿度传感器DHT11绑定
			TiDHT dht11 = new TiDHT(gpio0, 3);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			// 显示标题
			oled.print(0, 0, "My first DIY");
			// 通过屏幕循环打印
			while (true) {
				// 开启测量
				dht11.measure();
				// 显示温湿度
				oled.print(2, 0, "TEMP: " + dht11.getTemperature() + "  C");
				oled.print(3, 0, "HUMI: " + dht11.getHumidity() + "  %");
				// 等待2秒
				Delay.msDelay(2000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
