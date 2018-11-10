import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.button.ITiButtonEventListener;
import tijos.framework.sensor.button.TiButton;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * 监听类
 * 
 * @author tijos
 *
 */
class TouchListener implements ITiButtonEventListener {
	boolean _isOnPressed = false;

	@Override
	public void onPressed(TiButton arg0) {
		synchronized (this) {
			// 置按下标志
			this._isOnPressed = true;
		}
	}

	@Override
	public void onReleased(TiButton arg0) {
		synchronized (this) {
			// 置释放标志
			this._isOnPressed = false;
		}
	}

	public boolean isPressed() {
		boolean pressed = false;
		synchronized (this) {
			// 返回置按下标志
			pressed = this._isOnPressed;
		}
		return pressed;
	}
}

/**
 * 静悄悄的开灯，延时触摸开关
 * 
 * @author tijos
 *
 */
public class TouchLight {

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
			oled.print(0, 0, "TouchLight");
			// GPIO总线资源与继电器对象绑定
			TiRelay1CH relay = new TiRelay1CH(gpio0, 2);
			// GPIO总线资源与触摸按钮对象绑定，触发电平：高电平
			TiButton touch = new TiButton(gpio0, 4, true);
			// 创建触摸按钮事件监听者
			TouchListener lc = new TouchListener();
			// 设置触摸按钮事件监听者
			touch.setEventListener(lc);
			// 循环检测
			while (true) {
				// 检测到按钮按下
				if (lc.isPressed()) {
					// 打开继电器
					relay.turnOn();
					oled.print(2, 0, "Open switch");
					// 延时10秒
					Delay.msDelay(10*1000);
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
