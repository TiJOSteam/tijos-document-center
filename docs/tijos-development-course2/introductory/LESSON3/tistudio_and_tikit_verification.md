# 开启TiJOS之旅—开发环境搭建与开发板测试 #

## 温馨提示 ##
- 安装环境的路径中建议不要带中文或空格，最好是纯英文路径。
- 有基础的读者，且使用的电脑已经安装1.6及以上版本的JDK，同时还安装4.6及以上版本的Eclipse，可以直接安装TiJOS插件。
- 测试例程能把小龟自带外设功能都实现，还实现WIFI的快速连接功能。
- 通过在开发板上运行测试例程，基本能确定开发板是否完好。如有问题，请及时与客服联系。

## 一、JAVA JDK安装 ##

　　如果您已安装JDK, 跳过JDK安装即可。如您想安装我们提供的JDK版本，请您先卸载之前安装的jdk和jre，否则容易报错。JDK版本支持1.6 及以上版本。JDK也可在官网<http://www.oracle.com/technetwork/java/javase/downloads/index.html>下载最新版本。   

### JDK和JER安装 ###   

　　您的windows系统是64位需要安装64位的JDK，如果32位的windoes系统需要安装32（i586）位JDK。安装过程如下：

　　1.打开TiJOS文件夹，找到jdk-8u181_windows-x64.exe双击（右击点打开也可）。

![](./img/JDK/TiJOS0.png)

　　2.点击下一步。

![](./img/JDK/TiJOS1.png)

　　3.若更改安装路径请点击更改(C)。不更改安装路径，点下一步，直接跳到第6步。

![](./img/JDK/TiJOS2.png)

　　4.更改您的路径，更改完路径点击确定。

![](./img/JDK/TiJOS3.png) 

　　5.安装到，显示路径已改，点击下一步。

![](./img/JDK/TiJOS4.png)

　　6.Installing...

![](./img/JDK/TiJOS5.png)

　　7.点击确定。

![](./img/JDK/TiJOS6.png)

　　8.安装jre,安装路径建议和jdk一致。点击下一步。

![](./img/JDK/TiJOS7.png)

　　9.Installing...

![](./img/JDK/TiJOS8.png)

　　10.显示成功安装，说明您已安装成功，点击关闭。

![](./img/JDK/TiJOS9.png)

### 配置环境变量 ###

　　如果您的系统是windows10，可以选择配置环境变量也可以跳过，系统为Windows7和windows8则需要配置环境变量。配置过程如下：

　　1.右键点击计算机图标，接着点击菜单最下方的属性菜单项，或者连续打开控制面板--系统和安全--系统也可以。

![](./img/CEV/TiJOS1.png)

　　2.在弹出的窗口中点击左边的高级系统设置。

![](./img/CEV/TiJOS2.png)

　　3.在弹出的对话框中单击”高级“选项卡，接着点击下方的环境变量。

![](./img/CEV/TiJOS3.png)

　　4.在环境变量对话框中点击下方的系统变量的新建按钮。

![](./img/CEV/TiJOS4.png)

　　5.在弹出的新建系统变量里的变量名中输入JAVA_HOME，在变量值中输入JDK的根目录：D:\Program Files\Java\jdk1.8.0_181，注意，，根目录为您安装JDK目录。然后点击确定，返回环境变量对话框，再次点击新建按钮，分别输入classpath和.;%JAVA_HOME%\lib;，注意，此变量值以英文句点符号开始，以分号结束。然后点击确定，返回环境变量对话框。

![](./img/CEV/TiJOS5.png)

![](./img/CEV/TiJOS6.png)

　　6.在系统变量里面找到Path变量，注意，这次是点击编辑按钮，在弹出的对话框中的变量值的最后，一定是最后，添加如下字符串：;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin。注意，前面第一个是分号。如果没有Path变量，则添加Path变量，添加步骤和前面一样，不在重复。

![](./img/CEV/TiJOS7.png)

　　7.点击确定按钮。

![](./img/CEV/TiJOS8.png)


　　8.点击确定按钮。

![](./img/CEV/TiJOS9.png)


　　9.点击确定按钮。

![](./img/CEV/TiJOS10.png)


　　10.点击桌面菜单栏，windows打开搜索栏。

![](./img/CEV/TiJOS11.png)

　　11.搜索栏里输入CMD，点回车键。

![](./img/CEV/TiJOS12.png)

　　12.输入“java -version”,点击回车。

![](./img/CEV/TiJOS13.png)

　　13.输出如下图所示，环境变量配置成功。

![](./img/CEV/TiJOS14.png)

## 二、eclipse和TiJOS安装 ##
　　TiJOS应用与标准Java 应用类似，通过在Eclipse中安装TiStudio插件即可通过Eclipse进行TiJOS应用的开发和调试，Eclipse也可在官网<http://www.eclipse.org/downloads/> 下载最新版本。

　　如果您已安装Eclipse, 可直接安装TiStuido即可。

　　Eclipse版本支持4.6 及以上版本。
### Eclipse安装 ###
　　1.打开TiJOS文件夹，找到eclipse-jee-photon-R-win32-x86_64.zip右击解压文件。

![](./img/eclise_TiJOS/TiJOS1.png)

　　2.解压文件路径即Eclipse安装路径，记住安装路径。

![](./img/eclise_TiJOS/TiJOS2.png)

　　3.解压默认文件夹名为eclipse。

![](./img/eclise_TiJOS/TiJOS3.png)

　　4.打开eclipse文件夹，双击eclipse.exe打开。

![](./img/eclise_TiJOS/TiJOS4.png)

　　5.首次打开需要选择工程路径，默认即可（也可以自己选择路径）,点击Launch。

![](./img/eclise_TiJOS/TiJOS5.png)

　　6.出现如下界面，恭喜您安装成功。

![](./img/eclise_TiJOS/TiJOS6.png)


### 安装TiJOS ###

　　**TiStudio是由钛云物联基于Eclipse开发的一款插件工具，通过此工具开发者可简单快捷的开发TiJOS应用。**

　　在Eclipse中通过Help菜单下的"Install New Software" 安装TiStuido 插件, 安装过程如下:

　　1.从Eclipse菜单"Help"下选择"Install New Software"。

![](./img/eclise_TiJOS/TiJOS8.png)

　　2.从弹出的Install对话框中选择"Add"按钮后弹出“Add Repository"。

![](./img/eclise_TiJOS/TiJOS9.png)

　　Add Repository，填写TiStudio的插件，Name任意填（建议英文）；Location填 http://dev.tijos.net/studio/release。点击Add。

![](./img/eclise_TiJOS/TiJOS10.png)

　　3.从显示出的"Available Software"中选择"TiJOS"下的"TiStudio Release"后， 点击"Next"按钮开始进行安装。

![](./img/eclise_TiJOS/TiJOS11.png)

　　4.选择"I accept the terms of the license agreement" 后点击"Finish"。

![](./img/eclise_TiJOS/TiJOS12.png)

　　5.勾选“Beijing TiCloud Technology Co.Ltd; null; null”,点击“Accept selected”，接受所选。

![](./img/eclise_TiJOS/TiJOS13.png)

　　6.点击“Restart Now”,立即重启Eclipse。

![](./img/eclise_TiJOS/TiJOS15.png)

　　7.安装完成后重新启动Eclipse之后，在Eclipse的菜单中增加"TiJOS"。

![](./img/eclise_TiJOS/TiJOS14.png)

　　经过以上步骤后， TiStudio 即成功安装并运行在Eclipse当中。

## 三、驱动安装 ##

　　1.打开TiJOS文件夹，找到CH341SER.EXE双击（右击点打开也可）。

![](./img/TiJOS0.png)

　　2.点击安装，安装需要时间，请您耐心等待。

![](./img/TiJOS1.png)

　　3.显示驱动预安装成功，祝贺您驱动安装成功，点击确定。

![](./img/TiJOS2.png)

## 四、开发板测试 ##

　　1.打开Eclipse，点击File,选Import单击。

![](./img/Test/TiJOS5.png)

　　2.选择General目录下Existing Projects into Workspace,点击Next。

![](./img/Test/TiJOS6.png)

　　3.点击Browse。

![](./img/Test/TiJOS7.png)

　　4.选择测试工程test,点击选择文件。

![](./img/Test/TiJOS8.png)

　　5.点击Finish。

![](./img/Test/TiJOS9.png)

　　6.如下图所示，您已导入成功。

![](./img/Test/TiJOS10.png)

　　7.点TiJOS图标，弹出TiDevManger窗口。

![](./img/Test/TiJOS1.png)

　　8.点击连接设备。

![](./img/Test/TiJOS2.png)

　　9.系统终端选择USB-SERIAL CH340（COM？）点击确定。

![](./img/Test/TiJOS3.png)

　　10.如下图所示，您已成功连接小龟。

![](./img/Test/TiJOS4.png)

　　11.右击空白处，选Run As下TiJOS Application运行测试工程。

![](./img/Test/TiJOS12.png)

　　12.如下图所示，测试例程已在小龟里成功运行。

![](./img/Test/TiJOS13.png)



