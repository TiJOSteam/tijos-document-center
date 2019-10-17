import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.button.ITiButtonEventListener;
import tijos.framework.sensor.button.TiButton;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * ������
 *
 * @author tijos
 */
class TouchListener implements ITiButtonEventListener {
    boolean _isOnPressed = false;

    @Override
    public void onPressed(TiButton arg0) {
        synchronized (this) {
            // �ð��±�־
            this._isOnPressed = true;
        }
    }

    @Override
    public void onReleased(TiButton arg0) {
        synchronized (this) {
            // ���ͷű�־
            this._isOnPressed = false;
        }
    }

    public boolean isPressed() {
        boolean pressed = false;
        synchronized (this) {
            // �����ð��±�־
            pressed = this._isOnPressed;
        }
        return pressed;
    }
}

/**
 * �����ĵĿ��ƣ���ʱ��������
 *
 * @author tijos
 */
public class TouchLight {

    public static void main(String[] args) {

        try {
            // GPIO��Դ���䣬GPIO0��PIN2��PIN4��
            TiGPIO gpio0 = TiGPIO.open(0, 2, 4);
            // GPIO��Դ���䣬GPIO0��PIN4��
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            oled.print(0, 0, "TouchLight");
            // GPIO������Դ��̵��������
            TiRelay1CH relay = new TiRelay1CH(gpio0, 2);
            // GPIO������Դ�봥����ť����󶨣�������ƽ���ߵ�ƽ
            TiButton touch = new TiButton(gpio0, 4, true);
            // ����������ť�¼�������
            TouchListener lc = new TouchListener();
            // ���ô�����ť�¼�������
            touch.setEventListener(lc);
            // ѭ�����
            while (true) {
                // ��⵽��ť����
                if (lc.isPressed()) {
                    // �򿪼̵���
                    relay.turnOn();
                    oled.print(2, 0, "Open switch");
                    // ��ʱ10��
                    Delay.msDelay(10 * 1000);
                } else {
                    // �رռ̵���
                    relay.turnOff();
                    oled.print(2, 0, "Close switch");
                }
                // �����100ms
                Delay.msDelay(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
