
/**
 * 扩展Thread类实现多线程程序
 */
public class CounterThread extends Thread {

    public CounterThread(String name) {
        super(name);
    }

    public void run() {
        int i = 0;
        while (true) {
            i++;
            System.out.println(this.getName() + " count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new CounterThread("thread 1");
        Thread t2 = new CounterThread("thread 2");
        t1.start();
        t2.start();
    }
}