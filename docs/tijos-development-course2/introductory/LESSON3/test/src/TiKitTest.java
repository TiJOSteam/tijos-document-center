import java.io.IOException;

import tijos.framework.devicecenter.TiADC;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.platform.peripheral.ITiKeyboardListener;
import tijos.framework.platform.peripheral.TiKeyboard;
import tijos.framework.platform.wlan.TiWiFi;
import tijos.framework.sensor.bh1750.TiBH1750;
import tijos.framework.sensor.button.ITiButtonEventListener;
import tijos.framework.sensor.button.TiButton;
import tijos.framework.sensor.dht.TiDHT;
import tijos.framework.sensor.vs1838b.ITiVS1838BNECEventListener;
import tijos.framework.sensor.vs1838b.TiVS1838BNEC;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * WiFi SmartConfig线程类
 * 
 * @author tijos
 *
 */
class WiFiSCThread implements Runnable {
	TiOLED_UG2864 _oled;

	// 构造
	public WiFiSCThread(TiOLED_UG2864 oled) {
		this._oled = oled;
	}

	@Override
	public void run() {

		try {
			// 显示SmartConfig启动信息
			this._oled.print(3, 0, "Wi-Fi SC startup ");
			// 启动SmartConfig 30秒超时
			TiWiFi.getInstance().smartConfig(30);
			// 显示成功信息
			this._oled.print(3, 0, "Wi-Fi SC success");
		} catch (IOException e) {
			try {
				// 显示失败信息
				this._oled.print(3, 0, "Wi-Fi SC fail   ");
			} catch (IOException e1) {
			}
		}
	}

}

/**
 * MIC模拟信号采集线程类
 * 
 * @author tijos
 *
 */
class EMICThread implements Runnable {
	TiADC _adc;
	TiOLED_UG2864 _oled;

	// 构造
	public EMICThread(TiADC adc, TiOLED_UG2864 oled) {
		this._adc = adc;
		this._oled = oled;
	}

	@Override
	public void run() {

		try {
			while (true) {
				// 获取咪头(MIC)电压
				int val = this._adc.getRawValue(0);
				// 显示咪头(MIC)电压
				this._oled.print(2, 0, "MIC: " + val + "    ");
				Delay.msDelay(50);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

/**
 * 键盘监听类
 * 
 * @author tijos
 *
 */
class KeyboardListener implements ITiKeyboardListener {
	TiOLED_UG2864 _oled;
	Thread _thread;

	// 构造
	public KeyboardListener(TiOLED_UG2864 oled) {
		this._oled = oled;
	}

	@Override
	public void onPressed(int arg0, long arg1) {

	}

	@Override
	public void onReleased(int arg0, long arg1) {
		// 确保SmartConfig线程还未创建
		if (this._thread != null && this._thread.isAlive()) {
			return;
		}
		// 创建SmartConfig线程并启动
		this._thread = new Thread(new WiFiSCThread(this._oled));
		this._thread.start();
	}

}

/**
 * 红外解码监听类
 * 
 * @author tijos
 *
 */
class IRDecodeListener implements ITiVS1838BNECEventListener {
	TiOLED_UG2864 _oled;

	// 构造
	public IRDecodeListener(TiOLED_UG2864 oled) {
		this._oled = oled;
	}

	@Override
	public void cmdReceived(TiVS1838BNEC arg0) {
		try {
			// 显示红外解码值
			this._oled.print(3, 0, "IR: " + arg0.getAddress() + " - " + arg0.getCommand() + "      ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cmdRepeat(TiVS1838BNEC arg0) {
	}

}

/**
 * 触摸监听类
 * 
 * @author tijos
 *
 */
class TouchListener implements ITiButtonEventListener {
	TiRelay1CH _relay;

	// 构造
	public TouchListener(TiRelay1CH relay) {
		this._relay = relay;
	}

	@Override
	public void onPressed(TiButton arg0) {
		try {
			// 继电器打开
			this._relay.turnOn();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReleased(TiButton arg0) {
		try {
			// 继电器关闭
			this._relay.turnOff();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 开发板全功能测试
 * 
 * @author tijos
 *
 */
public class TiKitTest {

	public static void main(String[] args) {

		try {
			/* ---资源分配--- */
			// GPIO资源分配，GPIO0的PIN2/3/4/5脚
			TiGPIO gpio0 = TiGPIO.open(0, 2, 3, 4, 5);
			// I2C主机总线资源分配，I2CM0
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// ADC资源分配，ADC0的CH0通道
			TiADC adc0 = TiADC.open(0, 0);

			/* ---资源绑定--- */
			// I2C主机总线资源PORT0与屏幕对象绑定，屏幕地址：0x3C
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
			// I2C主机总线资源PORT0与照度计BH1750对象绑定，默认地址：0x23
			TiBH1750 bh1750 = new TiBH1750(i2cm0);
			// GPIO总线资源PIN2与继电器对象绑定
			TiRelay1CH relay = new TiRelay1CH(gpio0, 2);
			// GPIO总线资源PIN3与温湿度传感器DHT11绑定
			TiDHT dht11 = new TiDHT(gpio0, 3);
			// GPIO总线资源PIN4与触摸按钮对象绑定，触发电平：高电平
			TiButton touch = new TiButton(gpio0, 4, true);
			// GPIO总线资源PIN5与红外解码对象绑定
			TiVS1838BNEC vs1838b = new TiVS1838BNEC(gpio0, 5);
			// 获取键盘实例并设置键盘事件监听对象
			TiKeyboard keyboard = TiKeyboard.getInstance();
			keyboard.setEventListener(new KeyboardListener(oled));
			// 设置红外解码事件监听对象
			vs1838b.setEventListener(new IRDecodeListener(oled));
			// 设置触摸按钮事件监听对象
			touch.setEventListener(new TouchListener(relay));
			// 设置ADC参考电压及分压倍数
			adc0.setRefVoltageValue(1.0, 2);

			/* ---资源使用--- */
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			oled.print(3, 0,"Welcomes YOU ! ");
			System.out.println("Congratulations! The test routine runs successfully in TiJOS. ");
			// 创建MIC模拟电压采集线程对象并启动线程
			Thread thread = new Thread(new EMICThread(adc0, oled));
			thread.setDaemon(true);
			thread.start();
		
			while (true) {
				// 启动温湿度测量
				dht11.measure();
				// 获取温湿度值
				double temp = dht11.getTemperature();
				double humi = dht11.getHumidity();
				// 获取光照度值
				int lux = bh1750.readLightLevel();
				// 显示温湿度及光照度
				oled.print(0, 0, "TH: " + temp + "C  " + humi + "%");
				oled.print(1, 0, "LUX: " + lux + "    ");
				
				// 等待2秒
				Delay.msDelay(2000);
			}

		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}

}
