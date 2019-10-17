import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.util.Delay;

/**
 * TiJOS�Ĺ��£���Ļ�ı�������ʾ
 *
 * @author tijos
 */
public class TiJOSStory {
    // ��1���ַ�������
    static String screen1 = "Hi, i am TiJOS. " + "I'm an operating" + "system that can " + "be programmed in";
    // ��2���ַ�������
    static String screen2 = "java language.  " + "I'm trying to   " + "become a Android" + "in the Internet ";
    // ��3���ַ�������
    static String screen3 = "of things.";

    public static void main(String[] args) {

        try {
            // I2C����������Դ���䣬I2C PORT0
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            // ͨ����Ļѭ����ӡ
            while (true) {
                // ��ʾ��1�����ݣ��ȴ�2�������
                oled.output(screen1);
                Delay.msDelay(2000);
                oled.clear();
                // ��ʾ��2�����ݣ��ȴ�2�������
                oled.output(screen2);
                Delay.msDelay(2000);
                oled.clear();
                // ��ʾ��3�����ݣ��ȴ�2�������
                oled.output(screen3);
                Delay.msDelay(2000);
                oled.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
