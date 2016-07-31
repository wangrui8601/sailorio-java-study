package study.java.thread;
public class SafeLazySingleton {
	private static SafeLazySingleton instance = null;
	
	private SafeLazySingleton(){
		System.out.println("initialize once");
	}
	
	//使用synchronized加锁，存在效率问题
	/*public static SafeLazySingleton getInstance(){
		synchronized(SafeLazySingleton.class){
			if(instance == null){
				instance = new SafeLazySingleton();
			}
		}
		return instance;
	}*/
	
	//减小了加锁的范围，代码比较复杂，比前一种的效率没有太大提高
	/*public static SafeLazySingleton getInstance(){
		if(instance == null){
			synchronized(SafeLazySingleton.class){
				if(instance == null){
					instance = new SafeLazySingleton();
				}
			}
		}
		return instance;
	}*/
	
	//使用静态内部类，无锁，静态内部类和普通的内部类相同都是在第一次使用时加载
	static class InnerClass{
		private static final SafeLazySingleton inner = new SafeLazySingleton();
	}
	
	public static SafeLazySingleton getInstance(){
		return InnerClass.inner;
	}
	
	private static class LoopInit implements Runnable{

		@Override
		public void run() {
			SafeLazySingleton var;
			for(int index = 0; index < 100; ++index)
				var = SafeLazySingleton.getInstance();
		}
		
	}
	
	private static class LoopInit2 implements Runnable{

		@Override
		public void run() {
			SafeLazySingleton var;
			for(int index = 0; index < 100; ++index)
				var = SafeLazySingleton.getInstance();
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		Thread t1 = new Thread(new LoopInit());
		Thread t2 = new Thread(new LoopInit2());
		Thread t3 = new Thread(new LoopInit());
		Thread t4 = new Thread(new LoopInit2());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		
	}
}
