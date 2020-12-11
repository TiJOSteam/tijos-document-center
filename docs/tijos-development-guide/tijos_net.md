# 钛极OS(TiJOS)网络应用开发指南

TiJOS 除支持标准JAVA的TCP, UDP,DNS网络接口外， 还提供了网络时间协议NTP, MQTT和COAP客户端等常用应用接口，标准网络应用通过java.net来支持，NTP、MQTT和COAP分别通过tijos.framework.networkcenter.ntp、tijos.framework.networkcenter.mqtt和tijos.framework.networkcenter.coap支持。

## 标准JAVA的TCP, UDP,DNS网络

标准网络应用通过java.net来支持， 可在应用中调用TCP、UDP和DNS的方法。

具体使用方式请参考java.net

## NTP网络时间客户端

通过NTP客户端， 可在应用中获得准确的网络时间来校准本地时间。

具体使用方式请参考tijos.framework.networkcenter.ntp

## HTTP客户端

HTTP标准协议，具体使用方式请参考tijos.framework.networkcenter.http

## MQTT 客户端

MQTT协议目前广泛应用于IoT物联网, 支持MQTT3.1.1协议，具体使用方式请参考tijos.framework.networkcenter.mqtt

## COAP 客户端

COAP协议目前广泛应用于NB-IoT物联网, 支持COAP标准协议，具体使用方式请参考tijos.framework.networkcenter.coap

## LWM2M客户端

LWM2M协议目基于COAP协议， 一般应用于中国移动/中国电信物联网平台，具体使用方式请参考tijos.framework.networkcenter.lwm2m

## 阿里云物联网平台客户端

基于MQTT的阿里云物联网平台客户端， 适用于阿里云物联网平台接入，具体使用方式请参考tijos.framework.networkcenter.alibaba

## 腾讯云物联网平台客户端

基于MQTT的腾讯云物联网平台客户端， 适用于腾讯云物联网平台接入，具体使用方式请参考tijos.framework.networkcenter.tencent

##中国移动OneNET Studio物联网平台客户端
基于MQTT的中国移动OneNET Studio物联网平台客户端， 适用于中国移动OneNET平台接入，具体使用方式请参考tijos.framework.networkcenter.onenet

