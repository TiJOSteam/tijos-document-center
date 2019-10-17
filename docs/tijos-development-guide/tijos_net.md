# 钛极OS(TiJOS)网络应用开发指南

TiJOS 除支持标准JAVA的TCP, UDP,DNS网络接口外， 还提供了网络时间协议NTP, MQTT和COAP客户端等常用应用接口，标准网络应用通过java.net来支持，NTP、MQTT和COAP分别通过tijos.framework.networkcenter.ntp、tijos.framework.networkcenter.mqtt和tijos.framework.networkcenter.coap支持。

## 标准JAVA的TCP, UDP,DNS网络

标准网络应用通过java.net来支持， 可在应用中调用TCP、UDP和DNS的方法。

具体使用方式请参考java.net

## NTP网络时间客户端

通过NTP客户端， 可在应用中获得准确的网络时间来校准本地时间。

具体使用方式请参考tijos.framework.networkcenter.ntp

## MQTT 客户端

MQTT协议目前广泛应用于IoT物联网, 支持MQTT3.1.1协议，具体使用方式请参考tijos.framework.networkcenter.mqtt

## COAP 客户端

COAP协议目前广泛应用于NB-IoT物联网, 支持COAP标准协议，具体使用方式请参考tijos.framework.networkcenter.coap