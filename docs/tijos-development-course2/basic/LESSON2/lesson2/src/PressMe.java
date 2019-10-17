import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.platform.peripheral.ITiKeyboardListener;
import tijos.framework.platform.peripheral.TiKeyboard;
import tijos.framework.transducer.oled.TiOLED_UG2864;

/**
 * ������
 *
 * @author tijos
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
            // ��Ļʵ��
            this._oled.print(2, 0, "onPressed. ");
            // �ն˴�ӡ
            System.out.println("onPressed. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReleased(int arg0, long arg1) {
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
 * ����һ�£����ư�ťKEY
 *
 * @author tijos
 */
public class PressMe {

    public static void main(String[] args) {

        try {
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            // ��ʾ����ӡ����
            oled.print(0, 0, "Press me.");
            System.out.println("Press me");
            // ������������
            KEYListener lc = new KEYListener(oled);
            // ��ȡ����ʵ��
            TiKeyboard kb = TiKeyboard.getInstance();
            // ���ü����¼���������
            kb.setEventListener(lc);

            while (true) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
