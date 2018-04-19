# appcenter - 应用管理中心

TiJOS通过应用管理中心为用户提供了应用管理功能，包括应用安装，删除，运行等等，用户可根据实际应用的需要通过不同方式，如：无线WIFI等，进行应用的OTA升级，TiJOS提供的应用管理功能具有安装升级失败自动回滚特性，保证用户应用不会被损坏。

TiJOS自带的终端可以满足大部分应用管理需求，应用管理中心一般用于用户实现自定义的OTA功能， 如通过网络，GPRS等方式进行远程应用安装升级等等。

## Java包
tijos.framework.appcenter

包含类如下:

| 类名                | 说明      |
| ----------------- | ------- |
| TiAPP             | TiJOS 应用类    |
| TiAPPManager  | 应用管理类 |

## TiAPP

| 方法                                         | 说明 |
| -------------------------------------------- | ---- |
| int getId()  | 获取应用ID      |
| String getName()                                              |  获取应用名称    |
| void execute(boolean immediate, String args) | 执行应用 immediate : true立即执行  false 退出当前应用后执行 args: 应用参数    |
| void delete()                                | 删除自身，在程序退出时会实际删除应用 |
|                                              |      |

## TiAPPManager

应用管理器，主要方法如下：

| 方法                          | 说明                                            |
| ----------------------------- | ----------------------------------------------- |
| TiAPPManager getInstance()    | 获取TiAppManager实例                            |
| OutputStream create(int size) | 创建新的APP并获取输出流操作实例                 |
| TiAPP activate(int type)      | 激活当前应用 type: 0 普通  1 上电自动运行       |
| void format()                      | 格式化应用存储区，除Shell外所有的应用都将被删除 |
| TiAPP getRunningAPP()            | 获取当前正在运行的应用对象，也就是调用此API的应用                                    |
| int getTotalSize()            | 获取应用存储区总空间                            |
| int getFreeSize()             | 获取应用存储区剩余空间                          |

## 应用安装

应用一般通过TiDevManager来进行安装、删除和运行，当用户需要开发OTA功能时，即需要通过TiAPPManager来实现相应的应用安装功能。

调用过程如下所示：

```java
...
...
int appFileSize = ...; //tapk文件大小
TiAPPManager appManager = TiAPPManager.getInstance(); //获取TiAppManager实例
OutputStream stream = appManager.create(appFileSize);	  //创建文件并获取流操作实例
...
stream.write(transBuffer, 0, transLength);		//流写入文件
...
TiAPP app =appManager.active(1);//应用激活为上电自动运行 0 -普通应用 1- 上电自动运行应用

app.execute(false, null);//当前应用退出后执行新安装的应用，以后上电后将自动执行新安装的应用

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