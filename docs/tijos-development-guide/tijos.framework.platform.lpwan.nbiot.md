# NB-IoT - 无线局域网

窄带物联网（Narrow Band Internet of Things, NB-IoT）成为万物互联网络的一个重要分支。NB-IoT构建于蜂窝网络，只消耗大约180KHz的带宽，可直接部署于GSM网络、UMTS网络或LTE网络，以降低部署成本、实现平滑升级。NB-IoT是IoT领域一个新兴的技术，支持低功耗设备在广域网的蜂窝数据连接，也被叫作低功耗广域网(LPWAN)。NB-IoT支持待机时间长、对网络连接要求较高设备的高效连接。据说NB-IoT设备电池寿命可以提高至少10年，同时还能提供非常全面的室内蜂窝数据连接覆盖。 具体请参考 <https://baike.baidu.com/link?url=l7bPL51_cUhF2ywlVyeU_qPjoXpuBrFJundP_zGp7DxXdow5sl9FQ-g9dnsBdNc24R3MPJQOoLlk-f_otIPuf_>

## Java包
tijos.framework.platform.lpwan

## NB-IoT设置 - TiNBIoT

TiNBIoT中包含了所有与NBIoT相关的操作和设置, 支持PSM, eDRX两种模式. 
TiNBIoT为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| TiNBIoT getInstance()                     | 获取NB-IoT实例                                 |
| void startup(int timeout)                | 启动NB-IoT并连接基站, timeout以秒为单位                |
| void shutdown()                          | 关闭NB-IoT                                  |
| String getIMEI()                         | 获取模组IEMI字符串                         |
| String getIMSI()                   | 获取SIM卡IMSI字符串                        |
| void getICCID()     | 获取SIM卡CCID字符串             |
| String getPDPIP()                      | 获取当前IP地址                                 |
| int getRSSI()                | 获取当前信号强度                           |
| int getBER()                | 获取误码率                           |
| String getUTCTime()                | 获取当前UTC时间                           |
|int getCI(int index)              | 获取CELL ID                          |
| void enablePSM(String active, String periodic)               | 启用PSM, active: PSM active timer字符串 T3324时间, periodic: PSM periodic time string(TAU) T3412 时间, 相关时间格式需参考相关资料               |
| void disablePSM()                | 禁用PSM                           |
| String[] getPSM()               | 获取PSM设置, 返回null为PSM禁用, String[0]: PSM active timer, String[1]: periodic - TAU                        |
| void enableeDRX(int accType, String value, String ptw)              | 启用eDRX                           |
| void disableeDRX()             | 禁用eDRX                           |
| String[] geteDRX()            | 获取eDRX设置  eDRX[0] eDRX value string, eDRX[1] PTW value string, eDRX[2] Type value string                        |

TiNBIoT类中他方法的技术说明请参考TiJOS Framework说明文档。

### TiNBIoT 例程

NB-IoT的使用方式与运营商有关, 一般使用PSM模式较多，如果有低功耗需求， 请在处理完相关请求后通过TiPower进入standby，在经过一个PSM周期或外部主动唤醒后会自动从头开始运行。

一般流程如下，启动NB-IoT之后进行网络通讯之后进入待机即可, 可与CoAPClient接合使用。

```java
...

//启动NB-IoT 60秒超时
TiNBIoT.getInstance().startup(60);

//通过COAP进行数据发送等处理

//进入待机低功耗
TiPower.getInstance().standby(0);
```





