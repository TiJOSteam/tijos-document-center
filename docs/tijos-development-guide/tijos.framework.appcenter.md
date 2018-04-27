# appcenter - 应用管理中心

TiJOS通过应用管理中心为用户提供了应用管理功能，包括应用安装，删除，运行等等，用户可根据实际应用的需要通过不同方式，如：无线WIFI等，进行应用的OTA升级，TiJOS提供的应用管理功能具有安装升级失败自动回滚特性，保证用户应用不会被损坏。

钛极OS(TiJOS)系统的强大之处是支持多应用，应用程序之间可以相互调用，同时支持带参数启动；用户可以将具备不同功能的应用都下载到设备中，根据需要启动不同应用程序。

TiJOS自带的终端可以满足大部分应用管理需求，应用管理中心一般用于用户实现自定义的OTA功能， 如通过网络，GPRS等方式进行远程应用安装升级等等。

## Java包
tijos.framework.appcenter

包含类如下:

| 类名           | 说明        |
| ------------ | --------- |
| TiAPP        | TiJOS 应用类 |
| TiAPPManager | 应用管理类     |

## TiAPP

| 方法                                       | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| int getId()                              | 获取应用ID                                   |
| String getName()                         | 获取应用名称                                   |
| void execute(boolean immediate, String args) | 执行应用 immediate : true立即执行  false 退出当前应用后执行 args: 应用参数 |
| void delete()                            | 删除自身，在程序退出时会实际删除应用                       |
| void enableAutorun()                     | 将应用设置为上电自动运行                             |
|                                          |                                          |

## TiAPPManager

应用管理器，主要方法如下：

| 方法                            | 说明                                       |
| ----------------------------- | ---------------------------------------- |
| TiAPPManager getInstance()    | 获取TiAppManager实例                         |
| OutputStream create(int size) | 创建新的APP并获取输出流操作实例                        |
| TiAPP activate(int type)      | 激活当前应用 type: TiAPP.APP_GENERIC    TiAPP.APP_SHELL |
| void format()                 | 格式化应用存储区，除Shell外所有的应用都将被删除               |
| TiAPP getRunningAPP()         | 获取当前正在运行的应用对象，也就是调用此API的应用               |
| int getTotalSize()            | 获取应用存储区总空间                               |
| int getFreeSize()             | 获取应用存储区剩余空间                              |

## 应用类型

钛极OS(TiJOS)系统支持两种类型的应用程序，分别为：普通程序(**generic**)和壳程序(**shell**)，两种类型应用程序的区别为操作权限的不同，分别如下：

- generic类型应用程序**无权限调用格式化**应用存储区方法，也**无权限删除其他的应用程序**，只能删除自身。
- shell类型应用程序可删除所有类型应用程序。
- generic类型应用程序只能**创建安装**类型为generic的应用程序，shell应用程序则能创建安装所有类型应用程序。
- shell类型应用程序可设置某应用程序为**自动运行属性**，generic类型应用程序无权限操作。

## 上电自动运行

具有自动运行属性的应用程序在系统启动时自动启动，任何类型的应用程序都可以设置为自动运行属性，系统默认的自动运行程序为ID=0的应用程序(**shell**)，该应用程序为钛极OS(TiJOS)系统**预装的终端程序**，用户无权删除，用户可通过终端程序(连接PC端设备管理器)下载应用程序、更改系统配置等。

`当用户应用开发测试完成后，可以设置为上电自动运行作为正式产品` 

## 应用安装

应用一般通过TiDevManager来进行安装、删除和运行，当用户需要开发OTA功能时，即需要通过TiAPPManager来实现相应的应用安装功能。

调用过程如下所示：

```java
...
...
int appFileId = 0; //tapk文件ID自动分配
int appFileSize = ...; //tapk文件大小
TiAPPManager appManager = TiAPPManager.getInstance(); //获取TiAppManager实例
OutputStream stream = appManager.create(appFileId, appFileSize);	//创建文件并获取流操作实例
...
stream.write(transBuffer, 0, transLength);		//流写入文件
...
stream.close();
TiAPP app =appManager.activate(TiAPP.APP_GENERIC); //应用激活，generic类型
app.enableAutorun(); //使能自动运行

//如果需要可删除当前的旧应用
TiAPP oldApp = appManager.getRunningAPP(); //获取当前运行的应用
oldApp.delete(); //删除自身以释放空间

System.exit(0); //此处可调用系统退出，新应用在启动时自动运行，从而完成OTA的过程
...
...
```

## 应用删除

用户应用只能删除自己，通过 TiAPPManager中的getRunningAPP 可获得当前应用实例， 通过TiApp.delete即可删除自身, 执行删除操作时请谨慎。

```java
TiAPP oldApp = appManager.getRunningAPP(); //获取当前运行的应用
oldApp.delete(); //删除自身以释放空间

```

## 应用运行

TiAPP对象中execute用于执行指定应用，一般用于OTA过程中应用更新过程。
需要上电运行指定应用时， 通过设备管理器安装设置即可，无需在应用中处理。