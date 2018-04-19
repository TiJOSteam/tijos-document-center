# Java 标准核心类支持

TiJOS JDK中包含了大部分常用的JAVA基础核心类，包括java.lang, java.io, java.net, java.util等，这几个包中的类是整个Java JDK的基础， 可以满足各种应用开发需求。

 考虑到TiJOS 运行于资源非常有限的MCU下, 以下Java 特性在TiJOS JDK中不支持

1. 反射 :  不支持
2. 序列化 : 不支持
3. 正则表达式 : 不支持， 可使用字符串相关的操作函数来实现相应的功能
4. Locale :  不支持
5. finalize:  不要在对象的finalize方法中执行代码，此方法不会被调用到



TiJOS JDK支持标准Java JDK中提供的核心包，包括

| 标准JAVA包   | 说明                   |
| --------- | -------------------- |
| java.lang | java语言的基础类           |
| java.io   | 流支持，包含所有的输出输出stream类 |
| java.net  | 网络支持类                |
| java.util | 包含了集合及各种工具类          |



## java.lang 包支持说明

TiJOS JDK中支持标准java.lang核心包中的大部分类， 但是不支持与反射相关的类和方法，如ClassLoader等等， 下面仅列出TiJOS JDK所支持的标准Java类和接口，具体的使用方法请参考标准Java文档

**支持的Classes 列表**

- Boolean
- Byte
- Character
- Class
- Double
- Enum
- Float
- Integer
- Long
- Math
- Number
- Object
- Runtime
- Short
- String
- StringBuffer
- StringBuilder
- System
- Thread
- Throwable
- Void


## java.io包支持说明

TiJOS JDK中支持标准java.io核心包中的主要类, 不支持序列化。

下面仅列出TiJOS JDK所支持的标准Java类和接口，具体的使用方法请参考标准Java文档

**支持的Classes 列表**

- BufferedInputStream
- BufferedOutputStream
- BufferedReader
- BufferedWriter
- ByteArrayInputStream
- ByteArrayOutputStream
- CharArrayReader
- CharArrayWriter
- DataInputStream
- DataOutputStream
- File
- FileInputStream
- FileOutputStream
- FileReader
- FileWriter
- FilterInputStream
- FilterOutputStream
- FilterReader
- FilterWriter
- InputStream
- InputStreamReader
- LineNumberInputStream
- LineNumberReader
- OutputStream
- OutputStreamWriter
- PipedInputStream
- PipedOutputStream
- PipedReader
- PipedWriter
- PrintStream
- PrintWriter
- PushbackInputStream
- PushbackReader
- Reader
- SequenceInputStream
- StreamTokenizer
- StringBufferInputStream
- StringReader
- StringWriter
- Writer

## java.net包支持说明

TiJOS JDK中支持标准java.net核心包中与TCP, UDP, DNS等网络协议相关的类， 与Socket, ServerSocket,DatagramSocket, URI等类， 暂不支持Proxy, HTTP, URL相关的类。

下面仅列出TiJOS JDK所支持的标准Java类和接口，具体的使用方法请参考标准Java文档

**支持的Classes 列表**

- DatagramPacket
- DatagramSocket
- Inet4Address
- InetAddress
- InetSocketAddress
- RemoteSocket
- ServerSocket
- Socket
- SocketAddress
- URI

## java.util包支持说明

TiJOS JDK中支持java.util中大部分类，包括集合，时钟，等等，暂不支持Locale, TimeZone等功能。

下面仅列出TiJOS JDK所支持的标准Java类和接口，具体的使用方法请参考标准Java文档

- AbstractCollection
- AbstractList
- AbstractMap
- AbstractMap.SimpleEntry
- AbstractMap.SimpleImmutableEntry
- AbstractQueue
- AbstractSequentialList
- AbstractSet
- ArrayDeque
- ArrayList
- Arrays
- BitSet
- Calendar
- Collections
- Date
- Dictionary
- GregorianCalendar
- HashMap
- HashSet
- Hashtable
- IdentityHashMap
- LinkedHashMap
- LinkedHashSet
- LinkedList
- Observable
- PriorityQueue
- Properties
- Random
- Stack
- StringTokenizer
- Timer
- TimerTask
- TreeMap
- TreeSet
- UUID
- Vector

## 总结

以上为目前TiJOS JDK中所支持的包和相关的类，用法与标准Java相同，这些类是Java编程中最常用的核心类，同时我们会根据需求逐渐增加其它更多的类方便用户使用。 