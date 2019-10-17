import java.io.IOException;

import tijos.framework.devicecenter.TiADC;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.platform.peripheral.ITiKeyboardListener;
import tijos.framework.platform.peripheral.TiKeyboard;
import tijos.framework.platform.wlan.TiWiFi;
import tijos.framework.sensor.bh1750.TiBH1750;
import tijos.framework.sensor.button.ITiButtonEventListener;
import tijos.framework.sensor.button.TiButton;
import tijos.framework.sensor.dht.TiDHT;
import tijos.framework.sensor.vs1838b.ITiVS1838BNECEventListener;
import tijos.framework.sensor.vs1838b.TiVS1838BNEC;
import tijos.framework.transducer.oled.TiOLED_UG2864;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * WiFi SmartConfig�߳���
 *
 * @author tijos
 */
class WiFiSCThread implements Runnable {
    TiOLED_UG2864 _oled;

    // ����
    public WiFiSCThread(TiOLED_UG2864 oled) {
        this._oled = oled;
    }

    @Override
    public void run() {

        try {
            // ��ʾSmartConfig������Ϣ
            this._oled.print(3, 0, "Wi-Fi SC startup ");
            // ����SmartConfig 30�볬ʱ
            TiWiFi.getInstance().smartConfig(30);
            // ��ʾ�ɹ���Ϣ
            this._oled.print(3, 0, "Wi-Fi SC success");
        } catch (IOException e) {
            try {
                // ��ʾʧ����Ϣ
                this._oled.print(3, 0, "Wi-Fi SC fail   ");
            } catch (IOException e1) {
            }
        }
    }

}

/**
 * MICģ���źŲɼ��߳���
 *
 * @author tijos
 */
class EMICThread implements Runnable {
    TiADC _adc;
    TiOLED_UG2864 _oled;

    // ����
    public EMICThread(TiADC adc, TiOLED_UG2864 oled) {
        this._adc = adc;
        this._oled = oled;
    }

    @Override
    public void run() {

        try {
            while (true) {
                // ��ȡ��ͷ(MIC)��ѹ
                int val = this._adc.getRawValue(0);
                // ��ʾ��ͷ(MIC)��ѹ
                this._oled.print(1, 0, "MIC: " + val + "    ");
                Delay.msDelay(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/**
 * ���̼�����
 *
 * @author tijos
 */
class KeyboardListener implements ITiKeyboardListener {
    TiOLED_UG2864 _oled;
    Thread _thread;

    // ����
    public KeyboardListener(TiOLED_UG2864 oled) {
        this._oled = oled;
    }

    @Override
    public void onPressed(int arg0, long arg1) {

    }

    @Override
    public void onReleased(int arg0, long arg1) {
        // ȷ��SmartConfig�̻߳�δ����
        if (this._thread != null && this._thread.isAlive()) {
            return;
        }
        // ����SmartConfig�̲߳�����
        this._thread = new Thread(new WiFiSCThread(this._oled));
        this._thread.start();
    }

}

/**
 * ������������
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
            // ��ʾ�������ֵ
            this._oled.print(3, 0, "IR: " + arg0.getAddress() + " - " + arg0.getCommand() + "      ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cmdRepeat(TiVS1838BNEC arg0) {
    }

}

/**
 * ����������
 *
 * @author tijos
 */
class TouchListener implements ITiButtonEventListener {
    TiRelay1CH _relay;

    // ����
    public TouchListener(TiRelay1CH relay) {
        this._relay = relay;
    }

    @Override
    public void onPressed(TiButton arg0) {
        try {
            // �̵�����
            this._relay.turnOn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReleased(TiButton arg0) {
        try {
            // �̵����ر�
            this._relay.turnOff();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * ������ȫ���ܲ���
 *
 * @author tijos
 */
public class TiKitTest {

    public static void main(String[] args) {

        try {
            /* ---��Դ����--- */
            // GPIO��Դ���䣬GPIO0��PIN2/3/4/5��
            TiGPIO gpio0 = TiGPIO.open(0, 2, 3, 4, 5);
            // I2C����������Դ���䣬I2CM0
            TiI2CMaster i2cm0 = TiI2CMaster.open(0);
            // ADC��Դ���䣬ADC0��CH0ͨ��
            TiADC adc0 = TiADC.open(0, 0);

            /* ---��Դ��--- */
            // I2C����������ԴPORT0����Ļ����󶨣���Ļ��ַ��0x3C
            TiOLED_UG2864 oled = new TiOLED_UG2864(i2cm0, 0x3c);
            // I2C����������ԴPORT0���նȼ�BH1750����󶨣�Ĭ�ϵ�ַ��0x23
            TiBH1750 bh1750 = new TiBH1750(i2cm0);
            // GPIO������ԴPIN2��̵��������
            TiRelay1CH relay = new TiRelay1CH(gpio0, 2);
            // GPIO������ԴPIN3����ʪ�ȴ�����DHT11��
            TiDHT dht11 = new TiDHT(gpio0, 3);
            // GPIO������ԴPIN4�봥����ť����󶨣�������ƽ���ߵ�ƽ
            TiButton touch = new TiButton(gpio0, 4, true);
            // GPIO������ԴPIN5������������
            TiVS1838BNEC vs1838b = new TiVS1838BNEC(gpio0, 5);
            // ��ȡ����ʵ�������ü����¼���������
            TiKeyboard keyboard = TiKeyboard.getInstance();
            keyboard.setEventListener(new KeyboardListener(oled));
            // ���ú�������¼���������
            vs1838b.setEventListener(new IRDecodeListener(oled));
            // ���ô�����ť�¼���������
            touch.setEventListener(new TouchListener(relay));
            // ����ADC�ο���ѹ����ѹ����
            adc0.setRefVoltageValue(1.0, 2);

            /* ---��Դʹ��--- */
            // ��Ļ����������
            oled.turnOn();
            oled.clear();

            // ����MICģ���ѹ�ɼ��̶߳��������߳�
            new Thread(new EMICThread(adc0, oled)).start();

            while (true) {
                // ������ʪ�Ȳ���
                dht11.measure();
                // ��ȡ��ʪ��ֵ
                double temp = dht11.getTemperature();
                double humi = dht11.getHumidity();
                // ��ȡ���ն�ֵ
                int lux = bh1750.readLightLevel();
                // ��ʾ��ʪ�ȼ����ն�
                oled.print(0, 0, "TH: " + temp + "C  " + humi + "%");
                oled.print(2, 0, "LUX: " + lux + "    ");
                // �ȴ�2��
                Delay.msDelay(2000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
