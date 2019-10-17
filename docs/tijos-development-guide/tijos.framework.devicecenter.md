# devicecenter - 设备总线

设备中心(devicecenter)提供了用以控制硬件资源的通道，在TiJOS中传感器（sensor/transducer）类都通过设备中心提供的总线通道完成对外部硬件的控制，用户可以基于设备总线类编写标准库中未提供的传感器类。

## Java包
tijos.framework.devicecenter

TiJOS 设备总线包括如下类：

| 类名          | 说明                                       |
| ----------- | ---------------------------------------- |
| [TiGPIO](tijos.framework.devicecenter.TiGPIO.md)      | 通用输入/输出(General Purpose Input Output)  |
| [TiUART](tijos.framework.devicecenter.TiUART.md)      | 通用异步收发传输器(Universal Asynchronous Receiver/Transmitter)|
| [TiADC](tijos.framework.devicecenter.TiADC.md)       | 模数转换器(Analog-to-Digital Converter)     |
| [TiPWM](tijos.framework.devicecenter.TiPWM.md)       | 脉冲宽度调制发生器(Pulse Width Modulation)       |
| [TiI2CMaster](tijos.framework.devicecenter.TiI2CMaster.md) | 双向二线制同步串行总线(Inter-Integrated Circuit), 主机模式 |
| [TiSPIMaster](tijos.framework.devicecenter.TiSPIMaster.md) | 串行外设接口(Serial Peripheral Interface), 主机模式 |
| [TiOWMaster](tijos.framework.devicecenter.TiOWMaster.md)  | 单总线(one wire), 主机模式                       |

