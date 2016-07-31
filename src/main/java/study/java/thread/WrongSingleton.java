package study.java.thread;

public class WrongSingleton {
	private static volatile WrongSingleton instance = null;
	
	private WrongSingleton(){
		System.out.println("initialized once.");
	}
	
	public static WrongSingleton getInstance(){
		if(instance == null){
			instance = new WrongSingleton();
		}
		return instance;
	}
	
	private static class LoopInit implements Runnable{

		@Override
		public void run() {
			WrongSingleton var;
			for(int index = 0; index < 10; ++index)
				var = WrongSingleton.getInstance();
		}
		
	}
	
	private static class LoopInit2 implements Runnable{

		@Override
		public void run() {
			WrongSingleton var;
			for(int index = 0; index < 10; ++index)
				var = WrongSingleton.getInstance();
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
