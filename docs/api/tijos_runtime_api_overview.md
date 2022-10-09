# 钛极OS运行时API手册 #

详情请参考 [TiJOS Framework JavaDoc](http://dev.tijos.net/javadoc)

TiStudio是由钛云物联基于Eclipse开发的一款插件工具，通过此工具开发者可在TiKit平台上简单快捷的开发TiJOS应用。

## 说明 ##
### 环境要求
- Windows 7 or later
- Eclipse 4.6 (Mars) or later （请选择Eclipse IDE for Java EE Developers 或 Eclipse IDE for Java Developers 版本)
- Java 6 or later            
### 简单介绍 ###

TiStudio里含TiJOS Framework，它提供了用户在应用中对硬件资源进行嵌入式应用开发操作的相关类及方法，其中包括对各种硬件接口的操作，同时也提供了一些可能用到的工具类及方法。

## 目录 TiJOS Framework 包

| 包名称                               | 说明                                       |
| --------------------------------- | ---------------------------------------- |
| tijos.framework.appcenter | 应用中心类，进行应用管理，包括安装，升级，运行等等 |
| tijos.framework.devicecenter | 设备总线相关类，如GPIO, I2C, PWM等等，可用来开发在驱动库中需要支持的传感器 |
| tijos.framework.eventcenter       | 事件中心类，处理来自硬件外设总线的事件，如GPIO事件等             |
| tijos.framework.networkcenter.coap | 标准的COAP协议，一种基于UDP的应用层协议, 支持URL方式访问 |
| tijos.framework.networkcenter.dns | 域名解析系统协议客户端 |
| tijos.framework.networkcenter.mqtt | MQTT 客户端，支持3.1.1标准 |
| tijos.framework.networkcenter.ntp           | 网络时间协议客户端  |
| tijos.framework.networkcenter.http | HTTP协议客户端 |
| tijos.framework.networkcenter.lwm2m | 标准LWM2M协议客户端 |
| tijos.framework.networkcenter.alibaba | 阿里云物联网平台客户端 |
| tijos.framework.networkcenter.tencent | 腾讯云物联网平台客户端 |
| tijos.framework.platform.lpwan | 低功耗广域网(LPWAN)即NB-IoT，是物联网领域一个新兴的技术，支持低功耗设备在广域网的蜂窝数据连接 |
| tijos.framework.platform.lpwan.lte | 4G LTE蜂窝网络 |
| tijos.framework.platform.peripheral | 平台最小系统外设, 键盘， 按键等等 |
| tijos.framework.platform.util | NB模组存储类， 如KeyValueStorage，SharedBuffer等。此类只适用NB模组硬件平台。 |
| tijos.framework.platform.wlan | WLAN设置, WIFI等 |
| tijos.framework.platform.lan | 以太网 |
| tijos.framework.util       | 常用工具类，如Delay, byte数组与int之间相互转换, 按格式生成字符串等等|
| tijos.framework.util.base64 | BASE64转换类 |
| tijos.framework.util.crc                 | CRC校验算法                                |
| tijos.framework.util.json                   | JSON 处理类                                 |
| tijos.framework.util.logging                | 日志类，输出到打印口                               |


## 应用中心 appcenter包

tijos.framework.appcenter

包含类如下:

| 类名           | 说明        |
| ------------ | --------- |
| TiAPP        | TiJOS 应用类 |
| TiAPPManager | 应用管理类     |

### TiAPP

#### TiAPP 方法定义

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| int getId()                              | 获取应用ID                                   |
| String getName()                         | 获取应用名称                                   |
| void execute(boolean immediate, String args) | 执行应用 immediate : true立即执行  false 退出当前应用后执行 args: 应用参数 |
| void delete()                            | 删除自身，在程序退出时会实际删除应用                       |
| void enableAutorun()                     | 将应用设置为上电自动运行                             |


### TiAPPManager

#### TiAPPManager 方法定义

| 方法                            | 说明                                       |
| ----------------------------- | ---------------------------------------- |
| TiAPPManager getInstance()    | 获取TiAppManager实例                         |
| OutputStream create(int size) | 创建新的APP并获取输出流操作实例                        |
| TiAPP activate(int type)      | 激活当前应用 type: TiAPP.APP_GENERIC    TiAPP.APP_SHELL |
| void format()                 | 格式化应用存储区，除Shell外所有的应用都将被删除               |
| TiAPP getRunningAPP()         | 获取当前正在运行的应用对象，也就是调用此API的应用               |
| int getTotalSize()            | 获取应用存储区总空间                               |
| int getFreeSize()             | 获取应用存储区剩余空间                              |

## 设备总线 devicecenter包

tijos.framework.devicecenter

包含类如下：

| 类名          | 说明                                       |
| ----------- | ---------------------------------------- |
| TiGPIO      | 通用输入/输出（General Purpose Input Output ）   |
| TiUART      | 通用异步收发传输器（Universal Asynchronous Receiver/Transmitter） |
| TiADC       | 模数转换器（ Analog-to-Digital Converter）      |
| TiPWM       | 脉冲宽度调制发生器（ Pulse Width Modulation）       |
| TiI2CMaster | 双向二线制同步串行总线（ Inter-Integrated Circuit），主机模式 |
| TiSPIMaster | 串行外设接口 （Serial Peripheral Interface），主机模式 |
| TiOWMaster  | 单总线（one wire），主机模式                       |

### TiGPIO

#### TiGPIO 工作模式定义

| 工作模式       | 说明                      |
| -------------- | ------------------------- |
| INPUT_FLOATING | 浮空输入模式，默认模式    |
| INPUT_PULLUP   | 上拉输入模式              |
| INPUT_PULLDOWN | 下拉输入模式              |
| OUTPUT_OD      | 开漏输出模式&浮空输入模式 |
| OUTPUT_PP      | 推挽输出模式&浮空输入模式 |


#### TiGPIO 事件模式定义

事件模式只有具备输入模式的pin能支持。

| 事件模式              | 说明       |
| ----------------- | -------- |
| EVENT_NONE        | 无事件，默认模式 |
| EVENT_RISINGEDGE  | 上升沿事件模式  |
| EVENT_FALLINGEDGE | 下降沿事件模式  |
| EVENT_BOTHEDGE    | 双沿事件模式   |

#### TiGPIO 方法定义

| 方法                                     | 说明                                       |
| -------------------------------------- | ---------------------------------------- |
| TiGPIO open(int portID, int... pinIDs)|静态方法，通过指定port和pin集合打开指定GPIO，返回TiGPIO对象     |
| void close()                           | 关闭当前TiGPIO对象                             |
| void setWorkMode(int pinID, int mode)                 |设置当前TiGPIO对象指定pin的工作模式     |
| void setEventParameters(int pinID, int evt, int captureFilter)   |设置当前TiGPIO对象指定pin的事件模式，捕获周期单位：us      |
|  readPort()                 | 读当前TiGPIO对象port数据，16位数据                             |
| writePort(int value)                |写当前TiGPIO对象port数据，16位数据                      |
| readPin(int pinID)                    |读当前TiGPIO对象指定pin电平                         |
| writePin(int pinID, int levelValue)                     | 写当前TiGPIO对象指定pin电平      |

### TiUART

#### TiUART 工作模式定义

| 项目   | 参数                                       | 说明                     |
| ---- | ---------------------------------------- | ---------------------- |
| 波特率  | ...、1200bps 、2400bps 、 ...               | 支持的通讯波特率，受硬件平台限制       |
| 数据位  | 5 / 6 / 7 / 8 / 9                        | 数据位数                   |
| 停止位  | STOPBIT_1 / STOPBIT_2 / STOPBIT_0_5 / STOPBIT_1_5 | 停止位数:1 / 2 / 0.5 / 1.5 |
| 奇偶校验 | MODE_PARITY_NONE / MODE_PARITY_ODD / MODE_PARITY_EVEN | 无校验/奇校验/偶校验            |
| 缓冲类型 | BUFF_READ/ BUFF_WRITE/ BUFF_WR           | 读缓冲/写缓冲/读写缓冲           |

#### TiUART 方法定义

| 方法                                       | 说明                          |
| ---------------------------------------- | --------------------------- |
| TiUART open(int portID)                  | 通过指定port打开指定UART，返回TiUART对象 |
| void close()                             | 关闭已打开的TiUART实例              |
| void setWorkParameters(int dataBits, int stopBits, int parity,int baudRate) | 设置工作模式，数据位、停止位，奇偶校验位、波特率    |
| int available()                          | 检查有效接收数据长度，单位字节             |
| void clear(int buffer)                   | 根据不同缓冲类型清除缓冲区               |
| int read(byte[] data, int offset, int len) | 读取数据，返回实际读取长度               |
| int write(byte[] data, int offset, int len) | 写入数据，返回实际写入长度               |

### TiADC

#### TiADC 方法定义

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| TiADC open(int portID)                                       | 静态方法，通过指定port打开ADC所有通道，返回TiADC对象，**若需要再次打开或打开为其他设备时需要先调用close关闭当前对象** |
| TiADC open(int portID, int... channelIDs)                    | 静态方法，通过指定port及通道号打开ADC，返回TiADC对象，**若需要再次打开或打开为其他设备时需要先调用close关闭当前对象** |
| void close()                                                 | 关闭当前对象                                                 |
| int getRawValue(int channelID)                               | 获取指定通道的原始采样值                                     |
| double getVoltageValue(int channelID)                        | 获取转换后的电压测量值，单位：V                              |
| int getResolutionValue(boolean pow)                          | 获取分辨率                                                   |
| void setRefVoltageValue(double refVoltage, double volDivision) | 设置参考电压值和电压分压值。默认值：参考电压3.3，电压分压1.0 |

### TiPWM

#### TiPWM 方法定义

| 方法                                       | 说明                                     |
| ---------------------------------------- | -------------------------------------- |
| TiPWM open(int portID, int... channelIDs) | 静态方法，通过指定port和channel集合打开PWM，返回TiPWM对象 |
| void close()                             | 关闭当前对象                                 |
| void setFrequency(int freqValue)         | 设置脉冲频率                                 |
| setDutyCycle(int channelID, double duty) | 设置脉冲占空比，0 - 1: 0% - 100%               |
| void updateFreqAndDuty()                 | 更新周期和占空比                               |

### TiI2CMaster  

#### TiI2CMaster 工作模式定义

| 项目   | 参数                      | 说明               |
| ---- | ----------------------- | ---------------- |
| 波特率  | 100Kbps、400Kbps、3.4Mbps | 支持的通讯波特率，受硬件平台限制 |
| 从机地址 | 7、10                    | 地址位数             |

#### TiI2CMaster 方法定义

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| TiI2CMaster(int portID)                                      | 静态方法，通过指定port打开I2C Master，返回TiI2CMaster对象，**若需要再次打开或打开为其他设备时需要先调用close关闭当前对象** |
| void close()                                                 | 关闭当前对象                                                 |
| void setWorkBaudrate(int baudRate)                           | 设置通讯波特率                                               |
| void read(int address, byte[] data, int offset, int len)     | 从从机读数据，7、10位从机地址。                              |
| void write(int address, byte[] data, int offset, int len)    | 向从机写数据，7、10位从机地址。                              |
| void read(int address, byte[] data, int offset, int len, boolean repeated) | 从从机读数据并使能重复，即，不发送stop，从7、10位从机地址。  |
| void write(int address, byte[] data, int offset, int len, boolean repeated) | 向从机写数据并使能重复，即，不发送stop，7、10位从机地址。    |

### TiSPIMaster

#### TiSPIMaster 工作模式定义

| 项目    | 参数                                  | 说明                   |
| ----- | ----------------------------------- | -------------------- |
| 工作模式  | MODE0/MODE1/MODE2/MODE3             | 模式0/模式1/模式2/模式3      |
| 通道类型  | TYPE_SIO/TYPE_DIO/TYPE_QIO/TYPE_PIO | 单通道/双通道/四通道/并行通道     |
| 位传输模式 | ORDER_MSB/ORDER_LSB                 | 高位先传输/低位先传输          |
| 波特率   | 1/2/3...                            | 1Mbps/2Mbps/3Mbps... |

#### TiSPIMaster 方法定义

| 方法                                       | 说明                                     |
| ---------------------------------------- | -------------------------------------- |
| TiSPIMaster open(int portID)             | 通过指定port打开指定SPI Master，返回TiSPIMaster对象 |
| void close()                             | 关闭已打开的TiSPIMaster实例                    |
| void setWorkParameters(int mode, int type, int order, int baudRate) | 设置工作模式，类型、位传输模式、波特率                    |
| void selectSlave(boolean enable)         | 从机选择使能控制                               |
| int read(byte[] data, int offset, int len) | 读取数据，返回实际读取长度                          |
| int write(byte[] data, int offset, int len) | 写入数据，返回实际写入长度                          |

### TiOWMaster

#### TiOWMaster 工作模式定义

| 项目   | 参数                           | 说明        |
| ---- | ---------------------------- | --------- |
| 工作模式 | MODE_STANDARD/MODE_OVERDRIVE | 标准模式/高速模式 |

#### TiOWMaster 方法定义

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| TiOWMaster open(int portID, int... ioIDs) | 静态方法，通过指定port和io集合打开OW Master，返回TiOWMaster对象 |
| void close()                             | 关闭当前对象                                   |
| setWorkMode(int ioID, int mode)          | 设置工作模式                                   |
| void reset(int ioID)                     | 复位I/O                                    |
| int readBits(int ioID, int bits)         | 读I/O位数据                                  |
| void writeBits(int ioID, int value, int bits) | 写I/O位数据                                  |

## 硬件事件中心 eventcenter包

tijos.framework.eventcenter

### TiEventType

事件类型如下：

| 事件枚举 | 说明         |
| -------- | ------------ |
| GIPO     | GIPO总线事件 |
| KEYBOARD | 键盘按键事件 |
| USB      | USB总线事件  |

#### TiGPIOEvent

GPIO事件如下： 

| 函数                    | 说明                                       |
| --------------------- | ---------------------------------------- |
| TiEventType getType() | 获取事件类型，总返回TiEventType.GPIO               |
| long getTime()        | 获取事件发生时间，单位为微妙                           |
| int getPort()         | 获取事件发生的GPIO端口                            |
| int getPin()          | 获取事件发生的GPIO所属端口的管脚                       |
| int getIOEvent()      | 获取GPIO事件类型，包括EVENT_NONE，EVENT_RISINGEDGE，EVENT_FALLINGEDGE，EVENT_BOTHEDGE，具体请参考TiGPIO类说明 |

#### TiKeyboardEvent

 键盘按键事件如下： 

| 函数                  | 说明                                     |
| --------------------- | ----------------------- |
| TiEventType getType() | 获取事件类型，总返回TiEventType.KEYBOARD |
| long getTime()        | 获取事件发生时间，单位为微妙             |
| int getId()           | 获取事件发生的按键ID                     |
| int getEvent()        | 获取事件 |

### ITiEventListener

 事件监听器如下：

| 事件接口                  | 说明                                      |
| --------------------- | ------------------------------------- |
| onEvent(ITiEvent evt) | 当事件发生时，由事件中心进行调用, ITiEvent中包含了事件的具体信息 |
| getEventType()        | 返回事件类型，如TiEventType.GPIO              |

## COAP客户端 networkcenter.coap包 ##
tijos.framework.networkcenter.coap
### CoAP Client ###

包括如下类：

| 类名                 | 说明             |
| -------------------- | ---------------- |
| CoAPClient           | Coap客户端       |
| CoAPClientListener   | Coap消息监听     |
| ICoAPMessageListener | Coap消息监听接口 |

### coap 连接方法

|方法	| 说明|
|-------------|--------------|
|CoAPClient getInstance()|	获得CoAPClient实例|
|void connect(String serverUrl)|	连接到CoAP服务器 serverUrl 格式为coap://192.168.0.10:5683 或 coaps://192.168.0.10:5684|
|void void disconnect()	|断开连接|
|void setMessageType(int messageType)	|设置消息请示类型, 包括 0-CON， 1-NON, 2-ACK, 3-RST|
|int post(String uri, int contentType, byte[] content)|	通过CoAP POST请求将数据发向指定uri, contentType 内容类型, 如APPLICATION_TEXT_PLAIN, APPLICATION_JSON 等等, 返回消息ID|
|int get(String uri, int contentType)	|向指定URI发送GET请求，返回消息ID|
|int put(String uri, int contentType, byte[] content)	|通过CoAP PUT请求将数据发向指定uri，返回消息ID|
|int delete(String uri)|	向指定URI发送DELETE请求，返回消息ID|
|boolean isBusy()	|CoAP 是否仍有请求未完成, 用于在退出应用或进入待机状态时确保COAP请求已完成|

### CoAP 事件监听 ###
| 方法 | 说明 |
| -------------------------- | ---------- |
| void getResponseArrived(String uri, int msgid, boolean result, int msgCode, byte[] payload) | GET消息返回事件 |
| void postResponseArrived(String uri, int msgid, boolean result, int msgCode) | POST消息返回事件 |
| void putResponseArrived(String uri, int msgid, boolean result, int msgCode) | PUT消息返回事件 |
| void deleteResponseArrived(String uri, int msgid, boolean result, int msgCode) | DELETE消息返回事件 |


## MQTT客户端 networkcenter.mqtt包

tijos.framework.networkcenter.mqtt


## MQTT 连接配置

MQTT 连接配置通过MqttConnectOptions 类进行，可进行如下配置：

| 配置项            | MqttConnectOptions                    | 说明                                                      |
| ----------------- | ------------------------------------- | --------------------------------------------------------- |
| CleanSession      | setCleanSession(boolean)              | 设置 Client断开连接后Server是否应该保存Client的订阅信息   |
| UserName          | setUserName(String)                   | 用户名                                                    |
| Password          | setPassword(String)                   | 密码                                                      |
| LWT               | setWill(String, byte[], int, boolean) | "Last Will and Testament" (LWT), 具体请参考MQTT协议       |
| KeepAliveInterval | setKeepAliveInterval(int)             | 设置客户端与服务器之间最大空闲时间，以秒为单位，默认240秒 |

MQTT服务器地址及客户端ClientID通过MqttClient初始化时进行设置。

## MQTT 客户端 - MqttClient

| 接口                                                         | 说明                                                     |
| ------------------------------------------------------------ | -------------------------------------------------------- |
| void connect(String clientId, String serverUrl, int timeout, MqttConnectOptions options,IMqttMessageListener listener) | 建立连接                                                 |
| void disconnect()                                            | 断开连接                                                 |
| int getNetState()                                            | 获取网络状态 1 断开 2网络连接成功 3 正在连接  4 MQTT断开 |
| int subscribe(String topic, int qos)                         | TOPIC订阅                                                |
| int unsubscribe(String topic)                                | 取消订阅                                                 |
| int publish(String topic, byte[] payload, int qos, boolean retained) | 消息发布                                                 |
|                                                              |                                                          |


### MQTT 事件监听

| 事件                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void onNetworkConnected(boolean reconnect)                   | 当网络连接成功或重新连接成功时调用                           |
| void onNetworkDisconnected(int errcode)                      | 当网络断开或MQTT连接失败时调用                               |
| onMqttConnected()                                            | 当MQTT CONNECT消息返回成功时调用,意味着MQTT SERVER接受该连接 |
| publishMessageArrived(String topic, byte[] payload)          | 当服务器端发送topic更新时调用                                |
| void publishCompleted(int msgId, String topic, int result)   | 当客户端PUBLISH成功后调用                                    |
| void subscribeCompleted(int msgId, String topic, int result); | 当客户端SUBSCRIBE成功后调用                                  |
| void unsubscribeCompleted(int msgId, String topic, int result); | 当客户端UNSUBSCRIBE成功时                                    |



## 阿里云客户端 network.alibaba

tijos.framework.networkcenter.alibaba

## 阿里云物联网平台客户端

包括如下类：
| 类名                    | 说明                       |
| ----------------------- | -------------------------- |
| AliYunIoT               | 阿里云客户端               |
| AliLinkSubDevice        | 网关子设备管理             |
| IDataModelEventListener | 阿里云数据模型消息监听接口 |
| ISubDeviceEventListener | 阿里云子设备管理监听接口   |



## HTTP客户端network.http

tijos.framework.networkcenter.http

包括如下类：

| 类名                 | 说明                 |
| -------------------- | -------------------- |
| HttpClient           | HTTP Client客户端    |
| IHttpMessageListener | HTTP回复消息监听接口 |



## 平台基础特性 platform包

tijos.framework.platform

包含类如下:

| 类名称        | 说明         |
| ---------- | ---------- |
| TiSettings | 平台设置，如主机名称 |
| TiPower    | 电源管理       |

### TiSettings 平台设置

主要方法如下：

| 方法                               | 说明          |
| -------------------------------- | ----------- |
| void renameHost(String name)     | 主机重命名       |
| void resetLoggerLevel(int level) | 设置全局日志等级    |
| long getDateTime()               | 获取系统时间，单位为秒 |
| void setDateTime(long seconds)   | 设置系统时间，单位为秒 |

### TiPower 电源管理

主要方法如下：

| 方法                           | 说明                                       |
| ---------------------------- | ---------------------------------------- |
| void sleep()                 | 系统进入Sleep模式，通过外部事件(GPIO)自动唤醒。 唤醒后程序会**继续**运行。 |
| void stop(int timeWakeup)    | 系统进入Stop模式，通过外部事件(GPIO)或指定时间后自动唤醒，时间单位：秒。 唤醒后程序会**继续**运行。 |
| void standby(int timeWakeup) | 系统进入StandBy模式，通过唤醒引脚或在指定时间后自动唤醒，时间单位：秒。唤醒后程序会**重新**运行。 |
| void reboot(int time)|系统在指定时间后重启，时间单位：秒。重启后程序会重新运行|
| void getStartupMode()|获取系统启动模式，0 - 重新启动 1 - 从Standby手动唤醒运行 2 - 从Standby自动唤醒|

## 低功耗广域网NBIOT - platform.lpwan包

tijos.framework.platform.lpwan

包含类如下

|方法	|说明|
|-----------|------------------|
|TiNBIoT getInstance()	|获取NB-IoT实例|

### TiNBIoT

NBIoT设置方法如下：

|方法	|说明|
|-----------|------------------|
|TiNBIoT getInstance()	|获取NB-IoT实例|
|void startup(int timeout)|	启动NB-IoT并连接基站, timeout以秒为单位|
|void shutdown()|	关闭NB-IoT|
|String getIMEI()|	获取模组IEMI字符串|
|String getIMSI()|	获取SIM卡IMSI字符串|
|void getICCID()|	获取SIM卡CCID字符串|
|String getPDPIP()|	获取当前IP地址|
|int getRSSI()	|获取当前信号强度|
|int getBER()	|获取误码率|
|String getUTCTime()|	获取当前UTC时间|
|int getCI(int index)	|获取CELL ID|
|void enablePSM(String active, String periodic)|	启用PSM, active: PSM active timer字符串 T3324时间, periodic: PSM periodic time string(TAU) T3412 时间, 相关时间格式需参考相关资料，参数默认可传入null|
|void disablePSM()|	禁用PSM |
| String[] getPSM()	|获取PSM设置, 返回null为PSM禁用, String[0]: PSM active timer, String[1]: periodic - TAU|
|void enableeDRX(int accType, String value, String ptw)|	启用eDRX|
|void disableeDRX()	|禁用eDRX|
|String[] geteDRX()|	获取eDRX设置 eDRX[0] eDRX value string, eDRX[1] PTW value string, eDRX[2] Type value string|

## 4G LTE蜂窝网 - platform.lte包

tijos.framework.platform.lte

| 类名称            | 说明                                   |
| ----------------- | -------------------------------------- |
| TiLTE             | 4G LTE                                 |
| TiLTECell         | 基站小区ID相关信息，包括mcc,mnc,lac,ci |
| TiLTECSQ          | 信号 rssi,ber                          |
| ILTEEventListener | LTE连接断开事件回调                    |

### TiLTE 4G网络控制

主要方法如下：

| 方法                                            | 说明                                               |
| ----------------------------------------------- | -------------------------------------------------- |
| TiLTE getInstance()                             | 获取LTE实例                                        |
| void startup(int timeout)                       | 启动LTE并连接基站, timeout以秒为单位               |
| void startup(int timeout, ILTEEventListener lc) | 启动LTE并连接基站， 当基站连接或断开时通过事件通知 |
| void shutdown()                                 | 关闭LTE                                            |
| String getIMEI()                                | 获取模组IEMI字符串                                 |
| String getIMSI()                                | 获取SIM卡IMSI字符串                                |
| void getICCID()                                 | 获取SIM卡CCID字符串                                |
| String getPDPIP()                               | 获取当前IP地址                                     |
| int getRSSI()                                   | 获取当前信号强度                                   |
| TiLTECell getCellInfo()                         | 获取基站信息                                       |

## 平台外设 platform.peripheral包

tijos.framework.platform.peripheral
### TiLight 指示灯控制 

主要方法如下：

| 方法                    | 说明                   |
| --------------------- | -------------------- |
| TiLight getInstance() | 获取指示灯实例              |
| void turnOn(int id)   | 控制指定指示灯打开，id=0-255   |
| void turnOff(int id)  | 控制指定指示灯关闭，id=0-255   |
| void turnOver(int id) | 控制指定指示灯状态翻转，id=0-255 |

### TiKeyboard 键盘输入  

主要类如下：

| 类名                  | 说明    |
| ------------------- | ----- |
| TiKeyboard          | 键盘类   |
| ITiKeyboardListener | 键盘监听类 |

键盘类主要方法如下：

| 方法                                       | 说明     |
| ---------------------------------------- | ------ |
| TiKeyboard getInstance()                 | 获取键盘实例 |
| void setEventListener(ITiKeyboardListener lc) | 设置监听者  |

键盘监听类主要接口如下：

| 接口名称                | 说明     |
| ------------------- | ------ |
| ITiKeyboardListener | 键盘监听接口 |

## 平台工具 platform.util包

tijos.framework.platform.util　

| 类名            | 说明                               |
| --------------- | ---------------------------------- |
| KeyValueStorage | 键值存储，掉电数据不丢失           |
| SharedBuffer    | 缓冲区存储，临时存储，掉电数据丢失 |

### KeyValueStorage 键值对存储

主要方法如下：

| 方法                                                    | 说明             |
| ------------------------------------------------------- | ---------------- |
| KeyValueStorage getInstance( )                          | 获取键值存储实例 |
| byte[] readValue(String group, String key)              | 读取键值存储值   |
| void writeValue(String group, String key, byte[] value) | 写入键值存储值   |
| void deleteGroup(String group)                          | 删除键值存储组   |
| void deleteKey(String group, String key)                | 删除键值存储键   |

### SharedBuffer 共享缓冲区存储

主要方法如下：

| 方法                                                         | 说明                 |
| ------------------------------------------------------------ | -------------------- |
| SharedBuffer getInstance( )                                  | 获取共享缓冲区实例   |
| int getSize( )                                               | 获取共享缓冲区大小   |
| int read(byte[] dest, int destOffset, int srcOffset, int length) | 从共享缓冲区读取数据 |
| int write(byte[] src, int srcOffset, int destOffset, int length) | 将数据写入共享缓冲区 |

## WIFI网络 platform.wlan包

tijos.framework.platform.wlan

### TiWiFi

WIFI设置方法如下：

|  方法       |  说明           |
| -------------------------- | -------------------------- |
| TiWiFi getInstance() | 获取WiFi实例 |
| void startup(int timeout) | 启动WiFi并连接AP, timeout以秒为单位 |
| void shutdown() | 关闭WiFi |
| void startupSoftAP(boolean hidden) | 启动WiFi SoftAP功能, hidden为true时， SSID将不广播 |
| void shutdownSoftAP() | 关闭WiFi SoftAP |
| void changeSSID(String SSID) | 更改WiFi的SSID，更改后重新启动WiFi生效 |
| void changeSoftAPSSID(String SSID) | 更新softAP的SSID，更改后重新启动SoftAP生效 |
| String getSSID() | 获取WiFi将连接的SSID名称 |
| String getSoftAPSSID() | 获取SoftAP广播的SSID名称 |
| void changePassword(String password) | 更改WiFi的Password，更改后重启WiFi生效 |
| void changeSoftAPPassword(String password) | 更改SoftAP密码，更改后重启SoftAP生效 |
| void setStaticAddress(String localAddress, String gateWayAddress, int subnetPrefix) | 设置WiFi的静态地址，设置后重启生效, 如果任何一个参数为null将使用DHCP动态IP地址，默认为DHCP动态IP |
| void setSoftAPStaticAddress(String localAddress, String gateWayAddress, int subnetPrefix) | 设置SoftAP的静态地址, 如果任何一个参数为null将使用默认IP地址 |
| String getAddress() | 获取当前IP地址 |
| String getSoftAPAddress() | 获取SoftAP当前IP地址 |
| void smartConfig() | 启动WIFI Smart Config, 可通过手机来进行无线WIFI快速设置 |

## 工具类 util包
tijos.framework.util
| 类名 | 说明 |
| ------------------ | --------- |
| BigBitConverter | Byte与Int,Long相互转换，大尾模式 |
| LittleBitConverter | Byte与Int, Long相互转换，小尾模式 |
| Delay | 延时，支持毫秒，微妙延时 |
| Formatter | 支持Double/float/byte[] 转字符串, 方便显示 |

### BigBitConverter 大端数据转换

### LittleBitConverter 小端数据转换

主要方法如下：

| 方法                                    | 说明                                   |
| --------------------------------------- | -------------------------------------- |
| short ToInt16(byte[] bytes, int offset) | 将数组中指定位置的2字节数据转换为short |
| int ToUInt16(byte[] bytes, int offset)  | 将数组中指定位置的2字节数据转换为int   |
| int ToInt32(byte[] bytes, int offset)   | 将数组中指定位置的4字节数据转换为int   |
| long ToUInt32(byte[] bytes, int offset) | 将数组中指定位置的4字节数据转换为long  |
| byte[] GetBytes(int value)              | 将int数据转换为4字节byte数组           |
| byte[] GetBytes(short value)            | 将short数据转换为2字节byte数组         |

### Delay延时

| 方法                      | 说明           |
| ------------------------- | -------------- |
| void msDelay(long period) | 按指定毫秒延时 |
| void usDelay(long period) | 按指定微秒延时 |
| void nsDelay(long period) | 按指定纳秒延时 |

### Formatter 字符串转换

| 方法                                        | 说明                                                      |
| ------------------------------------------- | --------------------------------------------------------- |
| String format(double value, String pattern) | 将double数据转成指定位数小数点的字符串，pattern支持"#.##" |
| String format(float value, String pattern)  | 将float数据转成指定位数小数点的字符串，pattern支持"#.##"  |
| String toHexString(byte [] byteArray)       | 将byte数组转为16进制字符串                                |
| byte[] hexStringToByte(String str)          | 将16进制字符串转为byte数组                                |

## BASE64 编码 util.base64包

tijos.framework.util.base64


主要方法如下：

| 方法       | 说明                                   |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| String encode(byte[] buf)                                 | 将byte数据进行BASE64编码                                     |
| String encode(byte[] buf, int tw)                         | 将byte数据进行BASE64编码，一行最多tw字符，超过自动加换行， tw=0时不加换行 |
| String encode(byte[] buf, int offset, int length, int tw) | 将byte数组从offset开始length长度进行BASE64编码，一行最多tw字符，超过自动加换行， tw=0时不加换行 |
| byte[] decode(String b64)                                 | BASE64解码，将BASE64编码字符串还原为byte数组                 |

## CRC校验码 util.crc包
tijos.framework.util.crc

| 类                | 说明                                             |
| ----------------- | ------------------------------------------------ |
| CRC16_IBM         | IBM标准， 基于多项式X^16 + X^15 + X^2 + 1        |
| CRC8              | 单总线标准， 基于多项工 X^8 + X^5 + X^4 + 1      |
| CRC16_CCITT_FALSE | CCITT FALSE标准，基于多项式X^16 + X^15 + X^2 + 1 |



### CRC8校验码 

| 方法                                                        | 说明                                                    |
| ----------------------------------------------------------- | ------------------------------------------------------- |
| int compute (byte dataToCrc [], int seed)                   | 对数组进行CRC计算，seed为初始值                         |
| int compute (byte dataToCrc [], int off, int len, int seed) | 对数组从off偏移开始len长度数据进行CRC计算，seed为初始值 |



### CRC16校验码

相关类如下

|                   |                 |
| ----------------- | --------------- |
| CRC16 _IBM        | BM标准          |
| CRC16_CCITT_FALSE | CCITT FALSE标准 |

主要方法如下：

| 方法                                    | 说明                                  |
| --------------------------------------- | ------------------------------------- |
| void update(byte[] b)                   | 对数组进行CRC16运算                   |
| void update(byte[] b, int off, int len) | 对数组off开始len长度数据进行CRC16运算 |
| int getValue()                          | 获取CRC16结果                         |

## JSON编码 util.json包
tijos.framework.util.json

主要类说明

| 类          | 说明                           |
| ----------- | ------------------------------ |
| JSONObject  | JSON对象, 可用于构造JSON字符串 |
| JSONArray   | JSON数组                       |
| JSONTokener | JSON解析类                     |

## 日志输出 util.logging包
tijos.framework.util

主要方法如下

| 方法                                          | 说明           |
| --------------------------------------------- | -------------- |
| severe(String componentName, String message)  | 严重等级的日志 |
| warning(String componentName, String message) | 警告等级的日志 |
| info(String componentName, String message)    | 信息等级的日志 |
