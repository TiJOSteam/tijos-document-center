import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.button.ITiButtonEventListener;
import tijos.framework.sensor.button.TiButton;
import tijos.framework.transducer.oled.TiOLED_UG2864;

/**
 * 监听类
 * 
 * @author tijos
 *
 */
class TouchListener implements ITiButtonEventListener {
	TiOLED_UG2864 _oled;

	// 构造
	public TouchListener(TiOLED_UG2864 oled) {
		this._oled = oled;
	}

	@Override
	public void onPressed(TiButton arg0) {
		try {
			// 屏幕实现
			this._oled.print(2, 0, "onPressed. ");
			// 终端打印
			System.out.println("onPressed. ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReleased(TiButton arg0) {
		try {
			// 屏幕实现
			this._oled.print(2, 0, "onReleased.");
			// 终端打印
			System.out.println("onReleased.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 摸我一下，Touch触摸按钮 
 * 
 * @author tijos
 *
 */
public class TouchMe {

	public static void main(String[] args) {

		try {
			// GPIO资源分配，GPIO0的PIN4脚
			TiGPIO gpio0 = TiGPIO.open(0, 4);
			// I2C主机总线资源分配，I2C PORT0
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C			
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
			// GPIO总线资源与触摸按钮对象绑定，触发电平：高电平
			TiButton touch = new TiButton(gpio0, 4, true);	
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			// 显示并打印标题
			oled.print(0, 0, "Touch me.");
			System.out.println("Touch me.");
			// 创建监听者，并传入屏幕实例
			TouchListener lc = new TouchListener(oled);
			// 设置触摸按键事件监听者
			touch.setEventListener(lc);

			while (true) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
