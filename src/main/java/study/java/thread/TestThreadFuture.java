package study.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadFuture {
	private final static class IntegerCallable implements Callable<Integer>{
		public Integer call() throws Exception {
			Thread.sleep(2000);
			return 2;
		}
	}
	
	private final static class BooleanCallable implements Callable<Boolean>{

		@Override
		public Boolean call() throws Exception {
			Thread.sleep(2000);
			return true;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Future[] rets = new Future[2];
		
		rets[0] = pool.submit(new IntegerCallable());
		rets[1] = pool.submit(new BooleanCallable());
		pool.shutdown();
		for(int index = 0; index < 2; ++index){
			System.out.println("返回值为 " + rets[index].get());
		}
		
		
	}

}
