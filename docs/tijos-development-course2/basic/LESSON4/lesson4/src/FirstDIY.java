import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.dht.TiDHT;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.util.Delay;


/**
 * DIY��ʪ�ȼƣ���ʪ�Ȳɼ�
 *
 * @author andy
 */
public class FirstDIY {

    public static void main(String[] args) {

        try {
            // GPIO��Դ���䣬GPIO0��PIN3��
            TiGPIO gpio0 = TiGPIO.open(0, 3);
            // I2C����������Դ���䣬I2C PORT0
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // GPIO������Դ����ʪ�ȴ�����DHT11��
            TiDHT dht11 = new TiDHT(gpio0, 3);
            // I2C����������Դ����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // ��Ļ����������
            oled.turnOn();
            oled.clear();
            // ��ʾ����
            oled.print(0, 0, "My first DIY");
            // ͨ����Ļѭ����ӡ
            while (true) {
                // ��������
                dht11.measure();
                // ��ʾ��ʪ��
                oled.print(2, 0, "TEMP: " + dht11.getTemperature() + "  C");
                oled.print(3, 0, "HUMI: " + dht11.getHumidity() + "  %");
                // �ȴ�2��
                Delay.msDelay(2000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
