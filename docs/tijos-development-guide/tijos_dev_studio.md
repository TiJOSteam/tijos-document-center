# 钛极OS(TiJOS)应用开发集成环境 - TiStudio 

## 概述

TiStudio是由钛云物联基于Eclipse开发的一款插件工具，通过此工具开发者可简单快捷的开发TiJOS应用。

### 产品功能

TiStudio提供如下功能:

​	创建TiJOS Application工程。

​	支持在线下载tapk应用至设备中。

​	获取并打印设备中输出的日志。

​	支持错误信息快速定位。

​	支持设备管理，快速添加设备至工具中。

​	支持在线安装方式，管理更方便。

## 安装

### 环境要求

- Windows 7及以上

- Eclipse 4.6 (Mars) or later （请选择Eclipse IDE for Java EE Developers 或 Eclipse IDE for Java Developers 版本)

- Java 6 or later

Eclipse下载地址: http://www.eclipse.org/downloads/eclipse-packages/

### 安装过程

   请参考《钛极OS(TiJOS)应用开发环境搭建》文档

## 创建工程

通过TiStudio创建TiJOS Application工程，过程如下。

1. 打开Eclipse点击菜单 File --> New --> Project...

![20170914093819](.\img\20170914093819.png)

1. 在New Project窗口中选择TiJOS Application Project后点击Next按钮下一步进入工程配置界面

![20170914094054](.\img\20170914094054.png)

1. 在工程配置界面输入工程名称及描述并选择TiJOS SDK版本后点击Next按钮下一步进入Settings界面或者Finish按钮完成创建

![20170914094744](.\img\20170914094744.png)

2. 进入Settings界面

   ![20170914095239](.\img\20170914095239.png)

3. 如果可能用到钛极OS提供基本传感器驱动，如LED, BUTTON, OLED等，可通过Libraries选项加入“TiJOS Driver Library ClassPath Container", 也可在用到时通过Java Build Path 设置中加入也可，如下图所示

	![1528160152630](.\img\1528160152630.png)

## 调试运行

开发者通过创建TiJOS Application工程进行开发后，可使用调试运行功能来辅助开发。调试运行可在线下载应用至设备中，打印设备输出的日志，解析定位异常信息。



### 链接运行

选择工程右键点击 Run As --> TiJOS Application后将进行代码链接并下载至设备中运行，代码链接将在Console中实时输出链接结果信息。

![20170914111648](.\img\20170914111648.png)

![20170914112324](.\img\20170914112324.png)



### 日志输出

当通过调试运行将TiJOS Application运行至设备中并且返回成功后，TiStudio中LogCat View将监听设备串口日志。打开Eclipse点击菜单 Window --> Show View --> TiJOS LogCat 显示日志界面。

![20170914112915](.\img\20170925183001.png)



### 异常定位

代码运行中抛出异常信息并打印在TiJOS LogCat中，可直接在TiJOS LogCat点击异常信息定位至代码具体位置中。

![20170914113234](.\img\20170925183601.png)

如果Console中无法定位至代码中，也可通过点击菜单TiJOS --> Exception Query弹出异常查询窗口进行异常查询。在查询窗口中输入相关的错误编码并选择好工程后点击Query查询错误信息，点击Go跳转至具体具体源码位置中。

![20170914113837](.\img\20170914114708.png)

## 常见问题

1. 安装TiStudio时出现下图提示

   ![20171009132559](.\img\20171009132559.png)

   出现这种提示请直接勾选后点击“OK”即可。

2. 提示"Device manager startup failed"

   请检查Preferences中TiStudio配置路径是否正确。并尝试在菜单TiJOS --> TiDevManager来启动工具。

3. Run as --> TiJOS Application在线链接失败?

   在线链接功能需连接网络，请检查网络是否正常，或换个时间段再进行链接。

4. Run as --> TiJOS Application运行失败?

   请检查TiDevManager中设备是否连接成功, 可进行手动连接。

5. TiJOS LogCat不输出日志

   请检查TiDevManager中是否打开端口，打开端口后日志输出恢复正常。如仍无法正常输出日志，请尝试在TiDevManager中手动连接进行测试。

6. 提示tijos.framework.sensor 或 tijos.framework.transducer 不存在
   请在Java Build Path中的Library中加入“TiJOS Driver Library ClassPath Container"，如下图所示：
   ![1528160152630](.\img\1528160152630.png)

