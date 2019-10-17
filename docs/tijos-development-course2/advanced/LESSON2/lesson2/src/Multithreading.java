import java.io.IOException;

import tijos.framework.devicecenter.TiADC;
import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.sensor.dht.TiDHT;
import tijos.framework.transducer.relay.TiRelay1CH;
import tijos.framework.util.Delay;

/**
 * ���ص��߳�
 *
 * @author tijos
 */
class LightThread extends Thread {

    TiGPIO _gpio;
    int _ctlPin;
    TiADC _adc;
    int _inChl;

    /**
     * ����
     *
     * @param gpio
     * @param ctlPin
     * @param adc
     * @param inChl
     */
    public LightThread(TiGPIO gpio, int ctlPin, TiADC adc, int inChl) {
        this._gpio = gpio;
        this._ctlPin = ctlPin;
        this._adc = adc;
        this._inChl = inChl;
    }

    public void run() {

        System.out.println("LightThread running...");

        try {
            // GPIO������Դ��̵��������
            TiRelay1CH relay = new TiRelay1CH(this._gpio, this._ctlPin);
            // ����ADC�ο���ѹ1.0V���ⲿ2����ѹ
            this._adc.setRefVoltageValue(1.0, 2);
            // ����ɼ���ѹ�Ƚ���ֵΪ��14.0����
            double threshold = 14.0;
            // ѭ�����
            while (true) {
                // ��⵽��ť����
                if (this._adc.getVoltageValue(this._inChl) * 1000 > threshold) {
                    // �򿪼̵���
                    relay.turnOn();
                    // ��ʱ10��
                    Delay.msDelay(10 * 1000);
                } else {
                    // �رռ̵���
                    relay.turnOff();
                }
                // �����100ms
                Delay.msDelay(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * ��ʪ�Ȳɼ��߳�
 *
 * @author tijos
 */
class HumitureThread extends Thread {

    TiGPIO _gpio;
    int _dataPin;

    /**
     * ����
     *
     * @param gpio
     * @param dataPin
     */
    public HumitureThread(TiGPIO gpio, int dataPin) {
        this._gpio = gpio;
        this._dataPin = dataPin;
    }

    public void run() {

        System.out.println("HumitureThread running...");

        try {
            // ������ʪ�ȴ���������
            TiDHT dht11 = new TiDHT(this._gpio, this._dataPin);
            while (true) {
                // ��������
                dht11.measure();
                // �����ӡ
                System.out.println("TEMP: " + dht11.getTemperature() + "  C");
                System.out.println("HUMI: " + dht11.getHumidity() + "  %");
                // ��ʱ2��
                Delay.msDelay(2000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * ��ʶ���̣߳����ص�����ʪ�Ȳɼ�
 *
 * @author tijos
 */
public class Multithreading {

    public static void main(String[] args) {

        TiGPIO gpio0 = null;
        TiADC adc0 = null;
        try {
            // GPIO��Դ���䣬GPIO0��PIN2��PIN3��
            gpio0 = TiGPIO.open(0, 2, 3);
            // ADC��Դ���䣬ADC0��CH0ͨ��
            adc0 = TiADC.open(0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // �������ص��̶߳���
        LightThread thread1 = new LightThread(gpio0, 2, adc0, 0);
        // ������ʪ�Ȳɼ��̶߳���
        HumitureThread thread2 = new HumitureThread(gpio0, 3);
        // ���������߳�
        thread1.start();
        thread2.start();
        // ��ǰ�߳��˳�
        System.out.println("MainThread exit.");
    }
}
