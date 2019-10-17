import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.bh1750.TiBH1750;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.util.Delay;

/**
 * ���նȲɼ�����Ļ��ʾ
 *
 * @author tijos
 */

public class Illuminance {

    public static void main(String[] args) {

        try {
            // I2C����������Դ���䣬I2C PORT0
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // I2C����������Դ���նȼ�BH1750����󶨣�Ĭ�ϵ�ַ��0x23
            TiBH1750 bh1750 = new TiBH1750(i2cm0);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            // ��ʾ����
            oled.print(0, 0, "Illuminamce test");
            // ѭ���ɼ��նȲ���ʾ�ʹ�ӡ
            while (true) {
                //��ȡ���ն�ֵ
                int lux = bh1750.readLightLevel();
                //��־��ӡ��� 
                System.out.println("Light : " + lux + " lux");
                //Һ�������
                oled.print(2, 0, "Light : " + lux + " lux");
                Delay.msDelay(1000);
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

}
