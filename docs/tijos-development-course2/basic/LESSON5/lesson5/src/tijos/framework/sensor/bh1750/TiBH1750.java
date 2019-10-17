package tijos.framework.sensor.bh1750;

import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.util.BigBitConverter;
import tijos.framework.util.Delay;

/**
 * BH1750 ambient light intensity sensor driver for TiJOS based on
 * https://github.com/claws/BH1750
 *
 * @author TiJOS
 */
public class TiBH1750 {

    private TiI2CMaster i2cMaster;

    /**
     * Mode
     */
    public final static int UNCONFIGURED = 0;

    // Measurement at 1lx resolution. Measurement time is approx 120ms.
    public final static int CONTINUOUS_HIGH_RES_MODE = 0x10;

    // Measurement at 0.5lx resolution. Measurement time is approx 120ms.
    public final static int CONTINUOUS_HIGH_RES_MODE_2 = 0x11;

    // Measurement at 4lx resolution. Measurement time is approx 16ms.
    public final static int CONTINUOUS_LOW_RES_MODE = 0x13;

    // Measurement at 1lx resolution. Measurement time is approx 120ms.
    public final static int ONE_TIME_HIGH_RES_MODE = 0x20;

    // Measurement at 0.5lx resolution. Measurement time is approx 120ms.
    public final static int ONE_TIME_HIGH_RES_MODE_2 = 0x21;

    // Measurement at 1lx resolution. Measurement time is approx 120ms.
    public final static int ONE_TIME_LOW_RES_MODE = 0x23;

    /**
     * Default Address
     */
    private int i2cAddress = 0x23;

    /**
     * Default mode
     */
    private int mode = CONTINUOUS_HIGH_RES_MODE;

    /**
     * initialize with i2c master, default address 0x23
     *
     * @param i2c i2c master
     */
    public TiBH1750(TiI2CMaster i2c) {
        this(i2c, 0x23);
    }

    /**
     * initialize with i2c master and address
     *
     * @param i2c
     * @param addr
     */
    public TiBH1750(TiI2CMaster i2c, int addr) {
        this.i2cMaster = i2c;
        this.i2cAddress = addr;
    }

    /**
     * Configure BH1750 with specified mode
     *
     * @param mode Measurement mode
     */
    public void configurMode(int mode) throws IOException {

        // default transmission result to a value out of normal range
        byte[] data = new byte[1];

        // Check measurement mode is valid
        switch (mode) {

            case CONTINUOUS_HIGH_RES_MODE:
            case CONTINUOUS_HIGH_RES_MODE_2:
            case CONTINUOUS_LOW_RES_MODE:
            case ONE_TIME_HIGH_RES_MODE:
            case ONE_TIME_HIGH_RES_MODE_2:
            case ONE_TIME_LOW_RES_MODE:
                // Send mode to sensor
                data[0] = (byte) mode;
                this.i2cMaster.write(this.i2cAddress, data, 0, 1);
                // Wait a few moments to wake up
                Delay.msDelay(10);

                break;

            default:
                // Invalid measurement mode
                throw new IOException("[BH1750] ERROR: Invalid mode");
        }

        this.mode = mode;

    }

    /**
     * Read light level from sensor
     *
     * @return Light level in lux (0 ~ 65535)
     */
    public int readLightLevel() throws IOException {
        return readLightLevel(false);
    }

    public int readLightLevel(boolean maxWait) throws IOException {
        if (this.mode == UNCONFIGURED) {
            throw new IOException("[BH1750] Device is not configured!");
        }

        // Measurement result will be stored here
        int level = 65535;

        // Send mode to sensor
        byte[] data = new byte[2];

        // Send mode to sensor
        data[0] = (byte) this.mode;
        this.i2cMaster.write(this.i2cAddress, data, 0, 1);

        // Wait for measurement to be taken.
        // Measurements have a maximum measurement time and a typical measurement
        // time. The maxWait argument determines which measurement wait time is
        // used when a one-time mode is being used. The typical (shorter)
        // measurement time is used by default and if maxWait is set to True then
        // the maximum measurement time will be used. See data sheet pages 2, 5
        // and 7 for more details.
        // A continuous mode measurement can be read immediately after re-sending
        // the mode command.

        switch (this.mode) {
            case ONE_TIME_LOW_RES_MODE:
            case ONE_TIME_HIGH_RES_MODE:
            case ONE_TIME_HIGH_RES_MODE_2:
                if (this.mode == ONE_TIME_LOW_RES_MODE) {
                    if (maxWait)
                        Delay.msDelay(24);
                    else
                        Delay.msDelay(16);
                } else {
                    if (maxWait)
                        Delay.msDelay(180);
                    else
                        Delay.msDelay(120);
                }
                break;

            default:
                break;
        }

        // Read two bytes from the sensor, which are low and high parts of the sensor
        // value
        this.i2cMaster.read(this.i2cAddress, data, 0, 2);
        level = BigBitConverter.ToUInt16(data, 0);

        // Convert raw value to lux
        level /= 1.2;

        return level;
    }

}
