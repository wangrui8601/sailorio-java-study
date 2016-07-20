package study.java.thread;

import java.util.concurrent.atomic.AtomicLong;


public class TestVolatile {
	private static volatile long longVal = 0;
	//private static AtomicLong longVal = new AtomicLong(0L);
	
	private static class LoopVolatile implements Runnable{

		@Override
		public void run() {
			long val = 0;
			while(val < 10000000L){
				longVal++;
				//longVal.getAndIncrement();
				val++;
			}
			
		}
	}
	
	private static class LoopVolatile2 implements Runnable{

		@Override
		public void run() {
			long val = 0;
			while(val < 10000000L){
				longVal++;
				//longVal.getAndIncrement();
				val++;
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		Thread t1 = new Thread(new LoopVolatile());
		t1.start();
		
		Thread t2 = new Thread(new LoopVolatile2());
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("final val is : " + longVal);
		
	}
}
