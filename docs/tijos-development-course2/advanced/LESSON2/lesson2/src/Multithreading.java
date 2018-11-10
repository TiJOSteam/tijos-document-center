import java.io.IOException;

import tijos.framework.devicecenter.TiADC;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.sensor.dht.TiDHT;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * 声控灯线程
 * 
 * @author tijos
 *
 */
class LightThread extends Thread {

	TiGPIO _gpio;
	int _ctlPin;
	TiADC _adc;
	int _inChl;

	/**
	 * 构造
	 * 
	 * @param gpio
	 * @param ctlPin
	 * @param adc
	 * @param inChl
	 */
	public LightThread(TiGPIO gpio, int ctlPin, TiADC adc, int inChl) {
		this._gpio = gpio;
		this._ctlPin = ctlPin;
		this._adc = adc;
		this._inChl = inChl;
	}

	public void run() {

		System.out.println("LightThread running...");

		try {
			// GPIO总线资源与继电器对象绑定
			TiRelay1CH relay = new TiRelay1CH(this._gpio, this._ctlPin);
			// 设置ADC参考电压1.0V，外部2倍分压
			this._adc.setRefVoltageValue(1.0, 2);
			// 定义采集电压比较阈值为：14.0毫伏
			double threshold = 14.0;
			// 循环检测
			while (true) {
				// 检测到按钮按下
				if (this._adc.getVoltageValue(this._inChl) * 1000 > threshold) {
					// 打开继电器
					relay.turnOn();
					// 延时10秒
					Delay.msDelay(10 * 1000);
				} else {
					// 关闭继电器
					relay.turnOff();
				}
				// 检测间隔100ms
				Delay.msDelay(100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 温湿度采集线程
 * 
 * @author tijos
 *
 */
class HumitureThread extends Thread {

	TiGPIO _gpio;
	int _dataPin;

	/**
	 * 构造
	 * 
	 * @param gpio
	 * @param dataPin
	 */
	public HumitureThread(TiGPIO gpio, int dataPin) {
		this._gpio = gpio;
		this._dataPin = dataPin;
	}

	public void run() {

		System.out.println("HumitureThread running...");

		try {
			// 创建温湿度传感器对象
			TiDHT dht11 = new TiDHT(this._gpio, this._dataPin);
			while (true) {
				// 启动测量
				dht11.measure();
				// 结果打印
				System.out.println("TEMP: " + dht11.getTemperature() + "  C");
				System.out.println("HUMI: " + dht11.getHumidity() + "  %");
				// 延时2秒
				Delay.msDelay(2000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 认识多线程，声控灯与温湿度采集
 * 
 * @author tijos
 *
 */
public class Multithreading {

	public static void main(String[] args) {

		TiGPIO gpio0 = null;
		TiADC adc0 = null;
		try {
			// GPIO资源分配，GPIO0的PIN2和PIN3脚
			gpio0 = TiGPIO.open(0, 2, 3);
			// ADC资源分配，ADC0的CH0通道
			adc0 = TiADC.open(0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建声控灯线程对象
		LightThread thread1 = new LightThread(gpio0, 2, adc0, 0);
		// 创建温湿度采集线程对象
		HumitureThread thread2 = new HumitureThread(gpio0, 3);
		// 启动两个线程
		thread1.start();
		thread2.start();
		// 当前线程退出
		System.out.println("MainThread exit.");
	}
}
