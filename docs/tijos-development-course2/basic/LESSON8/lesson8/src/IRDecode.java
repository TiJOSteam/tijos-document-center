import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.vs1838b.ITiVS1838BNECEventListener;
import tijos.framework.sensor.vs1838b.TiVS1838BNEC;
import tijos.framework.transducer.oled.TiOLED_UG2864;

/**
 * 监听类
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
			this._oled.print(2, 0, "IR: COMMAND=" + arg0.getCommand());
			this._oled.print(3, 0, "IR: ADDRESS=" + arg0.getAddress());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	    
	public void cmdRepeat(TiVS1838BNEC arg0) {
	}

}

/**
 * 认识红外遥控器，红外编解码
 * 
 * @author tijos
 *
 */
public class IRDecode {

	public static void main(String[] args) {
		
		try {
			// GPIO资源分配，GPIO0的PIN5脚
			TiGPIO gpio0 = TiGPIO.open(0, 5);
			// I2C主机总线资源分配，I2C PORT0
			TiI2CMaster i2cm0 = TiI2CMaster.open(0);
			// I2C主机总线资源与屏幕对象绑定，屏幕地址：0x3C			
			TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
			// GPIO总线资源与红外解码对象绑定
			TiVS1838BNEC vs1838b = new TiVS1838BNEC(gpio0, 5);
			// 屏幕开启并清屏
			oled.turnOn();
			oled.clear();
			// 显示标题
			oled.print(0, 0, "IRDecode.");
			// 创建红外解码监听者，并传入屏幕实例
			IRDecodeListener lc = new IRDecodeListener(oled);
			// 设置红外解码事件监听者
			vs1838b.setEventListener(lc);

			while (true) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
