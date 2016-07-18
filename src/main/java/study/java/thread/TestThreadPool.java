package study.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
	private static final class MyThread extends Thread{
		public void run(){
			System.out.println(Thread.currentThread().getName() + " is running!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void executeThread(ExecutorService pool){
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		
		//将thread放入线程池中执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		//关闭线程池
		pool.shutdown();
	}
	
	//创建一个可重用的固定线程数的线程池
	private static ExecutorService getFixedThreadPool(){
		return Executors.newFixedThreadPool(2);
	}
	
	//创建一个根据需要自动调整大小的线程池
	private static ExecutorService getCachedThreadPool(){
		return Executors.newCachedThreadPool();
	}
	
	//创建只存在一个线程的线程池
	private static ExecutorService getSingleThreadExecutor(){
		return Executors.newSingleThreadExecutor();
	}
	
	public static void main(String[] args) throws InterruptedException{
		System.out.println("-----------FixedThreadPool----------");
		ExecutorService fixedPool = getFixedThreadPool();
		executeThread(fixedPool);
		
		Thread.sleep(3000);
		
		System.out.println("-----------CachedThreadPool----------");
		ExecutorService cachedPool = getCachedThreadPool();
		executeThread(cachedPool);
		
		Thread.sleep(3000);
		
		System.out.println("-----------SingleThreadExecutor----------");
		ExecutorService singleExecutor = getSingleThreadExecutor();
		executeThread(singleExecutor);
	}
}
