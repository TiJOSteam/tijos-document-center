import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.transducer.led.TiLED;
import tijos.framework.util.Delay;

/**
 * 此类实现TiLED灯控制功能演示<br>
 * TiLED控制分为三步：<br>
 * 1.“资源分配”：使用tijos.runtime.deviceaccess.TiGPIO包中TiGPIO类的<code>open</code>方法分配GPIO对象（注：全局只能分配一次）。<br>
 * 2.“资源绑定”：新创建TiLED对象，将其与1.中分配的GPIO对象以及指定pin绑定。<br>
 * 3.“资源使用”：使用tijos.runtime.transducer.transducer.led.TiLED类中的<code>turnOn<code>以及<code>turnOff<code>控制灯的开关<br>
 * <p>
 *
 * @author Jason
 */
public class ControlLED {
    /**
     * 程序入口，由TiJOS调用
     *
     * @param args 入口参数， TiJOS中一直等于null
     */
    public static void main(String[] args) {
        try {
            /*
             * 定义使用的TiGPIO port
             */
            int gpioPort0 = 0;
            /*
             * 定义使用的TiGPIO Pin
             */
            int gpioPin0 = 0;
            /*
             * 资源分配， 将gpioPort与gpioPin0分配给TiGPIO对象gpio0
             */
            TiGPIO gpio0 = TiGPIO.open(gpioPort0, gpioPin0);
            /*
             * 资源绑定， 创建TiLED对象red并将gpio0和gpioPin0与其绑定
             */
            TiLED red = new TiLED(gpio0, gpioPin0);
            /*
             * 资源使用， 控制灯的亮与灭
             */
            while (true) {

                red.turnOn();
                System.out.println("redled is turned on");

                Delay.msDelay(1000);

                red.turnOff();
                System.out.println("redled is turned off");

                Delay.msDelay(1000);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
