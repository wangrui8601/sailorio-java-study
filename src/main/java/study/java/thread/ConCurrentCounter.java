package study.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ConCurrentCounter {
	private AtomicInteger counter = new AtomicInteger();
	
	public void increment(){
		int oldValue;
		int newValue;
		do{
			oldValue = counter.get();
			newValue = oldValue + 1;
		}while(!counter.compareAndSet(oldValue, newValue));
	}
	
	public int get(){
		return counter.get();
	}
	
	static class MyRunnable implements Runnable{
		ConCurrentCounter counter;
		public MyRunnable(ConCurrentCounter counter){
			this.counter = counter;
		}
		public void run(){
			for(int i = 0; i < 500; ++i){
				counter.increment();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		ConCurrentCounter counter = new ConCurrentCounter();
		Runnable r = new MyRunnable(counter);
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		t1.start();
		t2.start();
		t3.start();
		
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println(counter.get());
	}
}
