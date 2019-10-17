import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.button.ITiButtonEventListener;
import tijos.framework.sensor.button.TiButton;
import tijos.framework.transducer.oled.TiOLED_UG2864;

/**
 * ������
 *
 * @author tijos
 */
class TouchListener implements ITiButtonEventListener {
    TiOLED_UG2864 _oled;

    // ����
    public TouchListener(TiOLED_UG2864 oled) {
        this._oled = oled;
    }

    @Override
    public void onPressed(TiButton arg0) {
        try {
            // ��Ļʵ��
            this._oled.print(2, 0, "onPressed. ");
            // �ն˴�ӡ
            System.out.println("onPressed. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReleased(TiButton arg0) {
        try {
            // ��Ļʵ��
            this._oled.print(2, 0, "onReleased.");
            // �ն˴�ӡ
            System.out.println("onReleased.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * ����һ�£�Touch������ť
 *
 * @author tijos
 */
public class TouchMe {

    public static void main(String[] args) {

        try {
            // GPIO��Դ���䣬GPIO0��PIN4��
            TiGPIO gpio0 = TiGPIO.open(0, 4);
            // I2C����������Դ���䣬I2C PORT0
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // GPIO������Դ�봥����ť����󶨣�������ƽ���ߵ�ƽ
            TiButton touch = new TiButton(gpio0, 4, true);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            // ��ʾ����ӡ����
            oled.print(0, 0, "Touch me.");
            System.out.println("Touch me.");
            // ���������ߣ���������Ļʵ��
            TouchListener lc = new TouchListener(oled);
            // ���ô��������¼�������
            touch.setEventListener(lc);

            while (true) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
