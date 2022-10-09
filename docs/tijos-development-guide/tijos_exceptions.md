# 钛极OS常见Java Exception

由于系统资源的限制，钛极OS在产生异常并打印时显示异常Class 名称为ID， 该ID对应的异常类名称可通过导出Tapk应用文件时显示的日志信息中查看， 下面是标准库中支持常见Exception及对应的ID号，当产生Exception时可通过Class ID 对应的Exception来判断具体原因。 

| Java Exception                            | Class ID | 说明                                                         |
| ----------------------------------------- | -------- | ------------------------------------------------------------ |
| java.lang.OutOfMemoryError                | 3        | 内存不足，建议在代码中不要建立大量一直使用的对象             |
| java.lang.NoSuchMethodError               | 14       | 类中无此方法                                                 |
| java.lang.StackOverflowError              | 15       | 线程堆栈溢出，一般为内存不足                                 |
| java.lang.NullPointerException            | 16       | 空指针                                                       |
| java.lang.ClassCastException              | 26       | 强制类型转换异常                                             |
| java.lang.ArithmeticException             | 27       | 数学运算异常， 如除0                                         |
| java.lang.ArrayIndexOutOfBoundsException  | 28       | 访问数组越界                                                 |
| java.lang.IllegalArgumentException        | 29       | 非法参数异常                                                 |
| java.lang.InterruptedException            | 30       | 线程阻塞方法收到中断请求时发生此异常                         |
| java.lang.IllegalStateException           | 31       | 非法状态异常                                                 |
| java.lang.IllegalMonitorStateException    | 32       | 非法的监控状态异常，当某个线程试图等待一个自己并不拥有的对象时发生 |
| java.lang.ArrayStoreException             | 34       | 数组元素类型不匹配异常                                       |
| java.lang.NegativeArraySizeException      | 35       | 试图创建大小为负的数组，则抛出该异常                         |
| java.lang.CloneNotSupportedException      | 64       | 在没有实现`Cloneable` 接口的实例上调用 Object 的 clone 方法  |
| java.lang.RuntimeException                | 71       | 运行时异常                                                   |
| java.lang.IndexOutOfBoundsException       | 72       | 访问数组越界                                                 |
| java.lang.Exception                       | 73       | 异常                                                         |
| java.lang.StringIndexOutOfBoundsException | 77       | 访问字符串越界                                               |
| java.lang.UnsupportedOperationException   | 79       | 不支持的操作                                                 |
| java.io.IOException                       | 91       | IO异常                                                       |
| java.lang.NumberFormatException           | 93       | 数字格式异常                                                 |
| java.io.UnsupportedEncodingException      | 99       | 不支持字符编码                                               |
| java.util.NoSuchElementException          | 149      | 无此元素                                                     |

