import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.vs1838b.ITiVS1838BNECEventListener;
import tijos.framework.sensor.vs1838b.TiVS1838BNEC;
import tijos.framework.transducer.oled.TiOLED_UG2864;

/**
 * ������
 *
 * @author tijos
 */
class IRDecodeListener implements ITiVS1838BNECEventListener {
    TiOLED_UG2864 _oled;

    // ����
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
 * ��ʶ����ң��������������
 *
 * @author tijos
 */
public class IRDecode {

    public static void main(String[] args) {

        try {
            // GPIO��Դ���䣬GPIO0��PIN5��
            TiGPIO gpio0 = TiGPIO.open(0, 5);
            // I2C����������Դ���䣬I2C PORT0
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // GPIO������Դ������������
            TiVS1838BNEC vs1838b = new TiVS1838BNEC(gpio0, 5);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            // ��ʾ����
            oled.print(0, 0, "IRDecode.");
            // ���������������ߣ���������Ļʵ��
            IRDecodeListener lc = new IRDecodeListener(oled);
            // ���ú�������¼�������
            vs1838b.setEventListener(lc);

            while (true) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
