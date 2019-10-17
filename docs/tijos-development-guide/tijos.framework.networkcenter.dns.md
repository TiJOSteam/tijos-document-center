# dns - 域名解析系统协议客户端

域名系统（Domain Name System缩写DNS，Domain Name被译为域名）是因特网的一项核心服务，它作为可以将域名和IP地址相互映射的一个[分布式数据库](https://baike.sogou.com/lemma/ShowInnerLink.htm?lemmaId=450817&ss_c=ssc.citiao.link)，能够使人更方便的访问互联网，而不用去记住能够被机器直接读取的IP数串，具体可参考https://baike.sogou.com/v127037.htm?fromTitle=dns或https://baike.baidu.com/item/dns/427444?fr=aladdin。

## Java包
tijos.framework.networkcenter.dns

## DNS设置  - TiDNS

TiDNS中包含了所有与DNS服务器相关的设置， 默认DNS服务器使用OpenDNS服务器，IP为208.67.222.222，208.67.220.220， 用户可通过TiDNS进行修改。

TiDNS为单例，在操作网络时可通过getInstance获得实例并调用相应的方法。

主要方法如下：

| 方法                                       | 说明         |
| ---------------------------------------- | ---------- |
| TiDNS getInstance()                      | 获得TiDNS实例  |
| void startup()                           | 启动DNS客户端服务 |
| void shutdown()                          | 关闭DNS客户端服务 |
| void changeServer(String primaryAddress, String secondaryAddress) | 更改DNS服务器地址 |
| String[] getServer()                     | 获取DNS服务器地址 |

### DNS功能调用

```java
...
TiDNS.getInstance().startupDNS();
...
TiDNS.getInstance().shutdown();
```





