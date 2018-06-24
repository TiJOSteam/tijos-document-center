# 钛极OS(TiJOS) 例程参考

钛极OS(TiJOS)提供了相关例程供开发人员参考

| 例程分类             | 链接                                       |
| ---------------- | ---------------------------------------- |
| TiJOS JDK 常用功能例程 | [https://github.com/TiJOSteam/tijos-software-example](https://github.com/TiJOSteam/tijos-software-example) |
| TiJOS 外设操作例程     | [https://github.com/TiJOSteam/tijos-hardware-example](https://github.com/TiJOSteam/tijos-hardware-example) |

以上的链接中包含了如下例程， 相关例程在不断更新丰富中。

### TiJOS JDK 常用功能例程

TiJOS JDK与标准Java JDK兼容，本目录下列出了一些基于标准java类的常用例程，方便用户参考

| 目录                    | 说明                             |
| --------------------- | ------------------------------ |
| helloworld            | Hello TiJOS                    |
| datatype\BitOperation | java下的位操作及移位操作例程               |
| math                  | 常用数据函数例程，加减乘除，三角函数等等           |
| file                  | 文件操作例程，文件创建删除，读写等等             |
| thread                | 多线程例程，经典生产者消费者例程               |
| network\NTP           | 基于NTPUDPClient的网络时间协议例程        |
| network\tcp_client    | 基于SOCKET的TCP 客户端例程             |
| network\tcp_server    | 基于ServerSocket和Socket的TCP服务器例程 |
| network\udp           | 基于DatagramSocket的UDP例程         |
| network\dns           | 基于InetAddress的DNS例程            |
| base64                | BASE64编码解码例程                   |
| json                  | JSON字符串生成和解析例程                 |
| MQTT                  | 基于MqttClient的MQTT客户端例程         |
| SmartConfig           | WIFI SmartConfig快速连接例程         |



### TiJOS 外设操作相关例程说明

#### 目录构成：

| 目录           | 内容              |
| :----------- | --------------- |
| Components   | 包含各种传感器和外设的使用例程 |
| Integrations | 包含各种综合例程        |

#### Components目录包含：

| 目录              | 内容                           |
| --------------- | ---------------------------- |
| TiButton        | 四按键相关类使用例程及说明文档              |
| TiBuzzer        | 有源蜂鸣器相关类使用例程及说明文档            |
| TiDHT11         | DHT数字温湿度传感器相关类使用例程及说明文档      |
| TiDS18B20       | DS18B20数字温度传感器相关类使用例程及说明文档   |
| TiHCSR04        | HC-SR04超声波测距传感器相关类使用例程及说明文档  |
| TiLED           | LED灯类使用例程及说明文档               |
| TiMQ2           | MQ2可燃气体浓度检测传感器相关类使用例程及说明文档   |
| TiOLED_UG2864   | OLED12864屏幕相关类使用例程及说明文档      |
| TiRelay1CH      | 继电器相关类使用例程及说明文档              |
| TiRGBLED        | RGB三基色灯相关类使用例程及说明文档          |
| TiVS1838BNEC    | VS1838B红外遥控接收(NEC码)功能例程及说明文档 |
| TiGeneralSensor | 通用传感器功能例程及说明文档               |

#### Integrations目录包含：

| 目录                  | 内容                     |
| ------------------- | ---------------------- |
| GasMonitoringSample | 多功能可燃气体监测报警仪的设计例程及说明文档 |
| RangefinderSample   | 多功能超声波测距仪的设计例程及说明文档    |



