# HTTPClient - HTTP客户端

超文本传输协议（Hyper Text Transfer Protocol，HTTP）是一个简单的请求-响应协议，它通常运行在[TCP](https://baike.baidu.com/item/TCP/33012)之上。它指定了客户端可能发送给服务器什么样的消息以及得到什么样的响应。请求和响应消息的头以[ASCII](https://baike.baidu.com/item/ASCII/309296)形式给出；而消息内容则具有一个类似[MIME](https://baike.baidu.com/item/MIME/2900607)的格式。 具全可参考：[HTTP_百度百科 (baidu.com)](https://baike.baidu.com/item/http/243074)

## Java包

tijos.framework.networkcenter.httpclient

## HTTP客户端-HttpConnection

HttpConnect实现了HTTP客户端请求，简单易用， 支持标准HTTP GET/PUT/POST/DELETE请求， 支持HTTP/HTTPS. 

### HttpConnection类

| 方法                                              | 说明                                                         |
| ------------------------------------------------- | ------------------------------------------------------------ |
| HttpConnection(String url)                        | 创建一个HTTP连接， 目标是url                                 |
| void setRequestProperty(String key, String value) | 设置HTTP请求头的属性和值，如果属性值已存在则更新             |
| void addRequestProperty(String key, String value) | 增加HTTP请求头的属性和值，不覆盖已存在的属性值               |
| void setBasicAuth(String user, String password)   | 设置Basic Auth认证模式下的用户名和密码                       |
| void setContentType(int contentType)              | 设置请求content type, 默认text/plain                         |
| HttpResponse post(byte[] content)                 | HTTP POST 请求， content: POST的数据                         |
| HttpResponse get()                                | HTTP GET请求                                                 |
| HttpResponse put(byte[] content)                  | HTTP PUT请求                                                 |
| HttpResponse delete()                             | HTTP DELETE请求                                              |
| HttpResponse readMoreHttpResponse()               | 读取更多数据，如果返回的数据长度大于512时会用到该API来获取后续数据 |
| void setReadTimeout(int timeout)                  | 设置HTTP请求超时， 默认5秒钟                                 |

### HttpResponse类

HTTP请求的返回数据为HttpResponse

| 属性                  | 说明                                                         |
| --------------------- | ------------------------------------------------------------ |
| int result            | HTTP请求结果，0 成功 1 有更多数据需要接收 < 0 HTTP请求错误，参考错误码说明 |
| int statusCode        | HTTP 状态码 200为成功                                        |
| int totalContent      | HTTP请求返回数据总长度                                       |
| byte [] payload       | HTTP请求的返回数据                                           |
| 方法                  |                                                              |
| boolean hasMoreData() | 是否还有数据待接收， 返回true时， 可使用HttpConnection的readMoreHttpResponse来获取 |

**注意**

由于内存资源限制， HTTP请求返回的数据超过512字节时需要通过多次读取来处理， 可通过HttpResponse中的totalContent来获取本次HTTP请求的总数据长度， 通过hasMoreData方法来获取是否还有更多数据待读取。 

### 错误码

HttpResponse中的result值小于0时表示HTTP请求错误， 代表的含义如下：

| 错误码 | 说明                |
| ------ | ------------------- |
| -1     | HTTP连接错误        |
| -2     | 服务端关闭连接      |
| -3     | 未知错误            |
| -4     | 协议错误            |
| -5     | 目标地址DNS解析失败 |
| -6     | URL解析失败         |

### 使用说明

 HTTP请求调用流程一般为：

1. 通过目标地址url创建HttpConnection对象
2. 如果有请求头，可通过setRequestProperty加入HTTP请求头
3. 通过get/put/post/delte来执行请求并返回服务端响应HttpResponse
4. 解析HttpResponse中的错误码和数据来对相应的处理



```java
//GET例程：

String url = "http://img.tijos.net/img/version.txt";

//通过URL创建对象
HttpConnection httpConnection = new HttpConnection(url);

//HTTP GET
HttpResponse resp = httpConnection.get();

System.out.println(new String(resp.payload));

//如果有更多数据
while(resp.hasMoreData()) {
    resp = httpConnection.readMoreHttpResponse();
    System.out.println(new String(resp.payload));				
}
```

```java
//POST例程
String url = "http://img.tijos.net/v1/getToken";

HttpConnection httpConnection = new HttpConnection(url);

httpConnection.setContentType(HttpConnection.APPLICATION_JSON);

HttpResponse resp = httpConnection.post("secretKey=07aa20e77bcf4927ec4515ab97be674a".getBytes());

System.out.println(new String(resp.payload));

```

