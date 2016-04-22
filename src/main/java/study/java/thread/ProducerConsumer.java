package study.java.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 测试生产者-消费者主类
 */
public class ProducerConsumer {
	public static void main(String[] args){
		SyncStack ss = new SyncStack();
		BreadProducer p = new BreadProducer(ss);
		BreadProducer p2 = new BreadProducer(ss);
		BreadConsumer c = new BreadConsumer(ss);
		Thread t1 = new Thread(p);
		t1.setName("生产者1");
		Thread t3 = new Thread(p2);
		t3.setName("生产者2");
		
		Thread t2 = new Thread(c);
		t2.setName("消费者1");
		
		t1.start();
		t3.start();
		try{
			Thread.sleep(1000);
		} catch(InterruptedException e ){
		}
		t2.start();
	}
}


/*
 * 面包类 
 */
class Bread{
	private String producer_name_;
	private int product_index_;
	
	public Bread(String producer_name, int product_index){
		this.producer_name_ = producer_name;
		this.product_index_ = product_index;
	}

	@Override
	public String toString() {
		return "bread: " + this.producer_name_ + "_" + this.product_index_;
	}
}

/*
 * 面包筐类
 */
class SyncStack{
	private static final Logger log_ = LoggerFactory.getLogger(SyncStack.class);
	private final int count_ = 5;
	private Bread[] breads_ = new Bread[this.count_];
	private int index_ = 0;
	
	public synchronized void put(Bread bread){
		log_.info("开始放入面包动作");
		while(this.index_ == this.count_){
			log_.warn("当前面包筐已经满了，等待...");;
			try{
				this.wait();
			} catch(InterruptedException e){
				log_.error("", e);
			}
			log_.info("有人从面包筐中拿走面包");
		}
		log_.info("面包筐有空间，放入面包...");
		this.notify();
		this.breads_[this.index_] = bread;
		this.index_ ++;
		log_.info("放入面包动作完成");
	}
	
	public synchronized Bread pop(){
		log_.info("开始拿走面包动作");
		while(this.index_ == 0){
			log_.warn("当前面包筐已经空了，等待...");
			try{
				this.wait();
			} catch(InterruptedException e){
				log_.error("", e);
			}
			log_.info("有人把面包放入面包筐中");
		}
		log_.info("面包筐有面包，拿走面包...");
		this.notify();
		this.index_ --;
		log_.info("拿走面包动作完成");
		return this.breads_[this.index_];
	}
}

/*
 * 生产者类
 */
class BreadProducer implements Runnable {
	private static final Logger log_ = LoggerFactory.getLogger(BreadProducer.class);
	private SyncStack ss_;
	public BreadProducer(SyncStack ss){
		this.ss_ = ss;
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			Bread bread = new Bread(Thread.currentThread().getName(), i);
			log_.info("生产面包： " + bread);
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e){
				log_.error("", e);
			}
			this.ss_.put(bread);
		}
	}
}

/*
 * 消费者类
 */
class BreadConsumer implements Runnable {
	private static final Logger log_ = LoggerFactory.getLogger(BreadConsumer.class);
	private SyncStack ss_;
	public BreadConsumer(SyncStack ss) {
		this.ss_ = ss;
	}
	
	public void run() {
		for(int i = 0; i < 20; i++) {
			Bread bread = this.ss_.pop();
			log_.info("消费面包： " + bread);
			try{
				Thread.sleep(100);
			} catch(InterruptedException e){
				log_.info("", e);
			}
		}
	}
}