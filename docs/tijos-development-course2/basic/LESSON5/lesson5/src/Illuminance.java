import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.bh1750.TiBH1750;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.util.Delay;

    /**
	 * 光照度采集，屏幕显示
	 * 
	 * @author tijos
	 *
	 */

public class Illuminance {

	public static void main(String[] args) {

		try {
			// I2C主机总线资源分配，I2C PORT0
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
			// I2C主机总线资源与照度计BH1750对象绑定，默认地址：0x23			
			TiBH1750 bh1750 = new TiBH1750(i2cm0);
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			// 显示标题
			oled.print(0, 0, "Illuminamce test");
			// 循环采集照度并显示和打印
			while (true) {			
				//获取光照度值
				int lux = bh1750.readLightLevel();
                //日志打印输出 
				System.out.println("Light : " + lux + " lux");
				//液晶屏输出
				oled.print(2, 0, "Light : " + lux + " lux");
				Delay.msDelay(1000);
			}

		} catch (IOException ex) {
		
			ex.printStackTrace();
		}
	}

}
