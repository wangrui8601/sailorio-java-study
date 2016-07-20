package study.java.thread;
public class SafeLazySingleton {
	private static SafeLazySingleton instance = null;
	
	private SafeLazySingleton(){}
	
	public static SafeLazySingleton getInstance(){
		synchronized(SafeLazySingleton.class){
			if(instance == null){
				instance = new SafeLazySingleton();
				System.out.println("initialize once");
			}
		}
		return instance;
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
