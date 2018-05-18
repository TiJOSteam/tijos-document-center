# 第一个TiJOS应用 - Hello World

在开始进行TiJOS应用开发前， 请从Eclipse中安装TiStudio， 具体过程请参考TiJOS应用开发环境搭建文档。

## 开发环境

操作系统：Win7及以上

Eclipse4.6 及以上

**Internet网络连接正常**

## 准备工作

1. 在Eclipse安装TiStudio 插件
2. TiKit 开发板连接正常并在TiDevManager中状态正常

## 新建工程

在Eclipse中新建菜单中选择"Other"

![NewTiJOSProject_Other](.\img\NewTiJOSProject_Other.png)

从弹出的新建项目类型中选择“TiJOS Development"下的”TiJOS Application Project":



![NewTiJOSProject_TiJOSProject](.\img\NewTiJOSProject_TiJOSProject.png)

点击“Next" , 输入工程名称，JRE选择默认即可，点击“Finish" 即可完成工程创建。

![NewTiJOSProject_ProjectName](.\img\NewTiJOSProject_ProjectName.png)

创建TiJOS Application Project的过程与标准Java过程类似， 一般只需输入工程名称其它选择默认即可完成工程的创建过程， 创建后的工程如下图所示：

![NewTiJOSProject_Tree](.\img\NewTiJOSProject_Tree.png)

## 创建HelloWorld类

我们从最简单的hello world程序开始，首先创建一个HelloTiJOS类, 在HelloTiJOS工程中新建“Java Class", 输入类名HelloTiJOS,  包名Package可选，同时选中“Public static void main(Sring[] args)"加入标准的java main方法（函数）作为程序的入口， 点击“Finish"即可创建成功，如下图所示：

![NewTiJOSClass_Name](.\img\NewTiJOSClass_Name.png)

类创建成功后，如下图所示：

![HellowTiJOS_Class](.\img\HellowTiJOS_Class.png)

加入代码将输出打印到日志输出中

```
  public class HelloWorld {
        public static void main (String[] args) {
          System.out.println("Hello TiJOS");
        }
      }
```

![HellowTiJOS_OutputHello](.\img\HellowTiJOS_OutputHello.png)

这样，最简单的Hello TiJOS例子是完成了

## 编译、运行

接下来，对完成的例子进行编译运行， 在进行编译之前，请确保机器连接Internet， 整个过程与标准Java类似，在工程右键菜单或当前Java文件右键菜单中选择Run As下的"TiJOS Application"，即可运行， 并在Console中观察编译和运行结果：

![RunAsTiJOS_Application](.\img\RunAsTiJOS_Application.png)

在开发板上运行TiJOS应用时，请确保在Ti-Dev Manger设备管理器中可看到TiKit开发板已连接。

## 调试

当需要进行调试时， 如果是与硬件无关的操作，可通过Debug As 中选择 "Java Application"进行调试， 如果是与硬件相应的功能调试， 则需要通过打印的方式观察输出结果。

硬件开发板在线调试目前暂时不支持，将在后续版本中增加此功能。 

## 导出

当应用调试测试成功后， 可将编译结果导出为tapk文件通过TiDevManager直接下载到设备中进行测试， 同时该文件也用于最新的量产或OTA应用更新过程。

导出tapk时， 在工程右键菜单中选择"Export", 在弹出的导出类型列表中选择“TiJOS Development"下的”Export TiJOS Application Package..."

![ExportTAPK_ProjectSelection](.\img\ExportTAPK_Selection.png)

点击Next后， 选择HelloTiJOS工程

![ExportTAPK_ProjectSelection](.\img\ExportTAPK_ProjectSelection.png)

点击"Next"后选择主类及tapk文件存储路径：

![ExportTAPK_StorageOptions](.\img\ExportTAPK_StorageOptions.png)

点击"Finish"即可完成tapk应用包导出， 导出的tapk文件可通过TiDevManager手工下载、运行功能直接对实际设备进行测试。

