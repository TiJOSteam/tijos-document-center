# 欢迎来到TiJOS世界—新建工程Hello TiJOS

## 温馨提示

- 进行TiJOS应用开发前，请先确定在Eclipse安装TiStudio 插件。
- 如没安装，请先从Eclipse中安装TiStudio， 具体过程请参考入门篇第三课 。     
- 运行程序前，先确定TiKit_T600_ESP8266B开发板是否连接正常，TiDevManager中状态是否正常。  

## 一、新建工程

　　在Eclipse中新建菜单中选择"Other"。

![](.\img\TiJOS1.png)

　　从弹出的新建项目类型中选择“TiJOS Development”下的“TiJOS Application Project”，点击“Next"。

![](.\img\TiJOS2.png)

　　输入工程名称，其它的选择默认即可，点击“Finish" 即可完成工程创建。

![](.\img\TiJOS3.png)

　　创建TiJOS Application Project的过程与标准Java过程类似， 一般只需输入工程名称其它选择默认即可完成工程的创建过程， 创建后的工程如下图所示：

![](.\img\TiJOS4.png)

## 二、创建HelloTiJOS类

　　我们从最简单的HelloTiJOS程序开始，首先创建一个HelloTiJOS类, 在HelloTiJOS工程中新建“Java Class"。

![](.\img\TiJOS5.png)

　　输入类名HelloTiJOS,  包名Package可不填，同时选中“Public static void main(Sring[] args)"加入标准的java main方法（函数）作为程序的入口， 点击“Finish"即可创建成功。

![](.\img\TiJOS6.png)

　　类创建成功后，如下图所示：

![](.\img\TiJOS7.png)

　　加入如下代码将输出打印到TiDevManager日志输出中。


    public class HelloTiJOS {      
         public static void main (String[] args) {
            System.out.println("Hello TiJOS");
        }
      }


![](.\img\TiJOS8.png)

　　这样，最简单的Hello TiJOS工程创建成功。

## 三、编译、运行

　　接下来，对创建完成的工程进行编译运行， 在进行编译之前，请确保机器连接Internet，在开发板上运行TiJOS应用时，请确保在Ti-Dev Manger设备管理器中可看到TiKit开发板已连接,整个过程与标准Java类似，在工程右键菜单中选择Run As下的"TiJOS Application"。

![](.\img\TiJOS9.png)

　　点击"OK"，即可运行， 并在Console中观察编译和运行结果。

![](.\img\TiJOS10.png)



## 四、调试

　　当需要进行调试时， 如果是与硬件无关的操作，可通过Debug As 中选择 "Java Application"进行调试， 如果是与硬件相应的功能调试， 则需要通过打印的方式观察输出结果。

![](.\img\TiJOS12.png)

　　硬件开发板在线调试目前暂时不支持，将在后续版本中增加此功能。 

## 五、导出

　　当应用调试测试成功后， 可将编译结果导出为tapk文件通过TiDevManager直接下载到设备中进行测试， 同时该文件也用于最新的量产或OTA应用更新过程。

　　导出tapk时， 在工程右键菜单中选择"Export"。

![](.\img\TiJOS13.png)

　　在弹出的导出类型列表中选择“TiJOS Development"下的”Export TiJOS Application Package..."

![](.\img\TiJOS14.png)

　　点击Next后，选择HelloTiJOS工程。

![](.\img\TiJOS15.png)

　　点击"Next"后选择主类及tapk文件存储路径：

![](.\img\TiJOS16.png)

　　点击"Finish"即可完成tapk应用包导出， 导出的tapk文件可通过TiDevManager中的下载、运行功能直接对实际设备进行测试。

## 六、下载 ##

　　打开TiDevManager，点击“下载APP”。

![](.\img\TiJOS17.png)

　　弹出"下载APP"对话框，点击添加路径。

![](.\img\TiJOS18.png)

　　选择导出tapk文件存储路径，点击“HelloTiJOS.tapk”。点击打开。

![](.\img\TiJOS19.png)

　　点击应用列表，选择"Hello TiJOS",点击运行。

![](.\img\TiJOS20.png)

　　如下图所示，祝贺你调试成功。

![](.\img\TiJOS21.png)



