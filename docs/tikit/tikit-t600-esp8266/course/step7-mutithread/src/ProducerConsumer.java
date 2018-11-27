package multiThreads;


//生产者-消费者
class  ProducerConsumer
{
	public static void main(String[] args) 
	{
		Warehouse w = new Warehouse();
		ProducerTask p = new ProducerTask(w);
		ConsumerTask c = new ConsumerTask(w);
		Thread tp = new Thread(p);
		Thread tc = new Thread(c);
		tp.start();
		tc.start();
	}
}

//产品
class Product
{
	private int id;
	
	Product(int id){
		this.id = id;
	}

	public String toString(){
		return "Product :" + id;
	}
}

//产品仓库
class Warehouse
{
	Product productStore[] = new Product[6];
	int index = 0;
	
	/** 
	* 生产方法.
	* 该方法为同步方法，持有方法锁
	* 首先循环判断满否，满的话使该线程等待，释放同步方法锁，等待消费；
	* 当不满时首先唤醒正在等待的消费方法，但是也只能让其进入就绪状态，
	* 等生产结束释放同步方法锁后消费才能持有该锁进行消费
	* 
	* @param p 产品
	* @return  
	*/ 

	public synchronized void push(Product p){
		try{
			while(index == productStore.length){
				System.out.println("[Produce] Warehouse: Full - wait consumer");
				this.wait();
			}
			this.notify();
				
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IllegalMonitorStateException e){
			e.printStackTrace();
		}	
		
		productStore[index] = p;
		index++;
		System.out.println("[Produce]:" + p + " [Total]: " + index);

	}

	/** 
	* 消费方法
	* 该方法为同步方法，持有方法锁
	* 首先循环判断空否，空的话使该线程等待，释放同步方法锁，允许生产；
	* 当不空时首先唤醒正在等待的生产方法，但是也只能让其进入就绪状态
	* 等消费结束释放同步方法锁后生产才能持有该锁进行生产
	* @return  
	*/ 
	public synchronized Product pop(){
		try{
			while(index == 0){
				System.out.println("[Consume] Warehouse: Empty - wait producer");
				this.wait();
			}
			this.notify();
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IllegalMonitorStateException e){
			e.printStackTrace();
		}
		
		index--;
		System.out.println("[Consume]: " + productStore[index] + "[Total]: " + index);
		return productStore[index];
	}
}

/**
 * 
 * 生产者线程
 *
 */
class ProducerTask implements Runnable
{
	Warehouse wh;
	ProducerTask(Warehouse w){
		this.wh = w;
	}

	//生产过程
	public void run(){
		for(int i = 0;i < 20;i++){
			Product m = new Product(i);
			wh.push(m);
			try{
				Thread.sleep((int)(Math.random()*500));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

/**
 * 
 * 消费者线程
 *
 */
class ConsumerTask implements Runnable
{
	Warehouse wh = null;
	ConsumerTask(Warehouse w){
		this.wh = w;
	}

	//消费过程
	public void run(){
		for(int i = 0;i < 20;i++){
			Product p = wh.pop();
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}