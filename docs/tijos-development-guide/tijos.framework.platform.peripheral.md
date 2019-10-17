# peripheral - 平台外设

为了能方便用户使用人机交互功能，钛极OS(TiJOS)系统原生支持平台级的外设，如：指示灯和键盘等。

## Java包
tijos.framework.platform.peripheral

## 指示灯控制 - TiLight

主要方法如下：

| 方法                    | 说明                   |
| --------------------- | -------------------- |
| TiLight getInstance() | 获取指示灯实例              |
| void turnOn(int id)   | 控制指定指示灯打开，id=0-255   |
| void turnOff(int id)  | 控制指定指示灯关闭，id=0-255   |
| void turnOver(int id) | 控制指定指示灯状态翻转，id=0-255 |

TiLight类中他方法的技术说明请参考TiJOS Framework说明文档。

调用过程举例：

```java
...
	TiLight light = TiLight.getInstance();
    while(true)
    {
      //打开第一个指示灯
      light.turnOn(0);
      //延时500ms
      Delay.msDelay(500);
      //关闭第一个指示灯
      light.turnOn(1);
      Delay.msDelay(500);
    }
...
```

## 键盘输入 - TiKeyboard

主要类如下：

| 类名                  | 说明    |
| ------------------- | ----- |
| TiKeyboard          | 键盘类   |
| ITiKeyboardListener | 键盘监听类 |

键盘类主要方法如下：

| 方法                                       | 说明     |
| ---------------------------------------- | ------ |
| TiKeyboard getInstance()                 | 获取键盘实例 |
| void setEventListener(ITiKeyboardListener lc) | 设置监听者  |

键盘监听类主要接口如下：

| 接口名称                | 说明     |
| ------------------- | ------ |
| ITiKeyboardListener | 键盘监听接口 |

调用过程举例：

```java
...
  //获取实例
  TiKeyboard kb = TiKeyboard.getInstance();
  //设置监听对象
  kb.setEventListener(new KeyboardListener());
  while(true) ;
...
```

```java
class KeyboardListener implements ITiKeyboardListener {
    public void onPressed(int id, long time) {
        System.out.println("onPressed:" + id + "  " + time);
    }
  
  	public void onReleased(int id, long time){
        System.out.println("onReleased:" + id + "  " + time);        
    }
}
```

