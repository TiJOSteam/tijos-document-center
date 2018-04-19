# 初识TiDevManager设备管理器

TiDevManager设备管理器是TiStudio一部分，无需单独安装, 该工具主要用于操作TiKit开发板进行设备诊断、配置、应用执行等功能。

TiDevManager设备管理器是TiKit的控制面板，可非常方便的对设备及应用进行管理和测试，包括：

- 通过应用下载端口与TiKit建立连接并获取设备信息
- 对TiKit开发板基础信息及网络进行设置
- 快速下载应用到TiKit开发板中
- 运行TiKit开发板中应用并输出日志

钛极OS(TiJOS)应用开发主要在Eclipse中进行, TiDevManager只是方便用户进行设备的设置和诊断，在需要时使用即可。

## 启动TiDevManager

安装TiStudio后可通过Eclipse工具导航菜单中点击红框图标打开TiDevManager工具，托盘显示如下图

![TiDevManager](.\img\vstools.png)

## 连接TiKit开发板

当TiDevManager检测到TiKit连接时，会自动加载设备信息并连接设备的日志打印口，如下图：

![DevInfo](.\img\DevInfo.png)

连接成功后，显示设备的ID、版本、处理器、存储空间等信息。通过菜单栏可对设备进行连接、断开、修改端口等操作，如系统中识别多个设备，可通过修改端口来切换。

## 网络设置

网络设置是TiDevManager的另一重要功能，网络设置通过设备属性进行设置， 可设置包括主机名称，WLAN, IP, DNS等， 如果支持AP， 还可设置AP相关的广播名称和连接密码等。

注意:网络设置在用户应用中通过TiWLAN启动网络连接时起作用，钛极OS(TiJOS)不会自动连接网络， 如果应用中用不到网络，也可不进行设置。

#### 主机名称

当设备连接成功后，点击设备属性，如下图，主机名称，类似于计算机连接路由器，在路由器中显示的客户端名称

![netinfo](.\img\netinfo.png)

#### WLAN

类似于无线终端，本身并不接受无线的接入，它可以连接到AP，一般无线网卡即工作在该模式，设置SSID及密码，可自动获取IP地址，或手动填写指定IP、子网掩码、网关信息

![netinfo2](.\img\netinfo2.png)

#### AP

AP模式，提供无线接入服务，允许其它无线设备接入，提供数据访问，设置SSID及密码，IP、可自动获取IP地址，或手动填写指定IP、子网掩码、网关信息

![netinfo3](.\img\netinfo3.png)

#### DNS

首选DNS与备用DNS设置，如果没有更优的DNS可采用设备默认DNS

![netinfo4](.\img\netinfo4.png)

## 系统工作模式设置

钛极OS(TiJOS)支持两种用户模式：BOOT和USER, 只有在BOOT模式下才能通过TiDevManager进行连接，在USER模式下，系统在上电后会自动运行用户应用，此时无法通过TiDevManager进行连接， 需要时可手动切换至BOOT模式， 具体可参考相关文档。

工作模式可通过TiDevManager进行设置， 在开发过程中建议保持BOOT模式， 当应用开发完成后，可设置为USER模式以支持上电后自动运行:

![netinfoUserApp](.\img\netinfoUserApp.png)

## 日志输出控制

钛极OS(TiJOS)提供了日志类Logger, 可用于在应用中输出日志， 同时， 日志极别可通过TiDevManager进行设置以方便通过日志进行分析

输出日志等级，根据开发的过程设置输出相关日志

![netinfo5](.\img\netinfo5.png)

以上信息，修改后点击确定即可

## 手工下载应用到设备

在开发过程中TiStudio会负责应用的自动下载和执行，当应用开发完成后，可能需要将应用发送给其他人进行测试，此时可通过TiStudio导出tapk应用文件，并通过TiDevManager手工下载并运行进行测试。

通过点击"下载APP"，选择要下载的应用tapk文件，如下图：

![appdownload](.\img\appdown.png)

下载过程中请勿断开设备，以免造成下载失败

### 设备中运行应用

APP下载完成后，可点击“运行”按钮，日志窗口会有日志输出，如下图：

![](.\img\logs.png)

日志可以保存成文件，也可输出到文件实时监测COM口数据输出，在日志窗口中右键，如下图：

![](.\img\logsmenue.png)

设置日志输出：可将日志输出到临时目录的文件中，方便查看





