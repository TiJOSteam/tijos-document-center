import java.io.IOException;

import tijos.framework.devicecenter.TiADC;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * ��һ�������ˣ���ʱ���ؿ���
 *
 * @author tijos
 */
public class VoiceLight {

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
            oled.print(0, 0, "VoiceLight");

            // ADC��Դ���䣬ADC0��CH0ͨ��
            TiADC adc0 = TiADC.open(0, 0);
            // GPIO������Դ��̵��������
            TiRelay1CH relay = new TiRelay1CH(gpio0, 2);
            // ����ADC�ο���ѹ1.0V���ⲿ2����ѹ
            adc0.setRefVoltageValue(1.0, 2);
            // ����ɼ���ѹ�Ƚ���ֵ:ĿǰΪ15.0��������Ҫ����ʵ�����������
            double threshold = 15.0;
            // ѭ�����
            while (true) {
                // ��⵽��ť����
                if (adc0.getVoltageValue(0) * 1000 > threshold) {
                    // �򿪼̵���
                    relay.turnOn();
                    // ��ʱ10��
                    oled.print(2, 0, "Open switch");
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
