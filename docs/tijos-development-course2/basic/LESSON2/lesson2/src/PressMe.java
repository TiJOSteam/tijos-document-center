import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.platform.peripheral.ITiKeyboardListener;
import tijos.framework.platform.peripheral.TiKeyboard;
import tijos.framework.transducer.oled.TiOLED_UG2864;

/**
 * 监听者
 * 
 * @author tijos
 *
 */
class KEYListener implements ITiKeyboardListener {


	TiOLED_UG2864 _oled;

	public KEYListener(TiOLED_UG2864 oled) {
		// TODO Auto-generated constructor stub
		this._oled = oled;
	}

	@Override
	public void onPressed(int arg0, long arg1) {
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
	public void onReleased(int arg0, long arg1) {
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
 * 按我一下，控制按钮KEY
 * 
 * @author tijos
 *
 */
public class PressMe {

	public static void main(String[] args) {

		try {
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);	
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			// 显示并打印标题
			oled.print(0, 0, "Press me.");
			System.out.println("Press me");
			// 创建监听对象
			KEYListener lc = new KEYListener(oled);
			// 获取键盘实例
			TiKeyboard kb = TiKeyboard.getInstance();
			// 设置键盘事件监听对象
			kb.setEventListener(lc);

			while (true) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
