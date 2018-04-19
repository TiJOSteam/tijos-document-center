# devicecenter - 设备总线

设备中心(devicecenter)提供了用以控制硬件资源的通道，在TiJOS中传感器（sensor/transducer）类都通过设备中心提供的总线通道完成对外部硬件的控制，用户可以基于设备总线类编写标准库中未提供的传感器类。

## Java包
tijos.framework.devicecenter

TiJOS 设备总线包括如下类：

| 类名          | 说明                                       |
| ----------- | ---------------------------------------- |
| TiGPIO      | 通用输入/输出（General Purpose Input Output ）   |
| TiUART      | 通用异步收发传输器（Universal Asynchronous Receiver/Transmitter） |
| TiADC       | 模数转换器（ Analog-to-Digital Converter）      |
| TiPWM       | 脉冲宽度调制发生器（ Pulse Width Modulation）       |
| TiI2CMaster | 双向二线制同步串行总线（ Inter-Integrated Circuit），主机模式 |
| TiSPIMaster | 串行外设接口 （Serial Peripheral Interface），主机模式 |
| TiOWMaster  | 单总线（one wire），主机模式                       |



## TiGPIO

TiGPIO的详细技术资料请参考：tijos.framework.devicecenter.TiGPIO。



## TiUART

TiUART的详细技术资料请参考：tijos.framework.devicecenter.TiUART.md



## TiADC

TiADC的详细技术资料请参考：tijos.framework.devicecenter.TiADC



## TiPWM

TiPWM的详细技术资料请参考：tijos.framework.devicecenter.TiPWM



## TiI2CMaster

TiI2CMaster的详细技术资料请参考：tijos.framework.devicecenter.TiI2CMaster



## TiSPIMaster

TiSPIMaster的详细技术资料请参考：tijos.framework.devicecenter.TiSPIMaster



## TiOWMaster

TiOWMaster的详细技术资料请参考：tijos.framework.devicecenter.TiOWMaster