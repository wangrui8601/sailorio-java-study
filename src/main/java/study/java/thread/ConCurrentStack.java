package study.java.thread;

import java.util.concurrent.atomic.AtomicReference;

/*
 * 使用CAS非阻塞算法实现堆栈操作
 */
public class ConCurrentStack {
	static class MyRunnable implements Runnable{
		ConCurrentStack stack;
		public MyRunnable(ConCurrentStack stack){
			this.stack = stack;
		}
		
		public void run(){
			for(int index = 1; index < 21; index = index + 2){
				stack.push(index);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ConCurrentStack stack = new ConCurrentStack();
		Thread t = new Thread(new MyRunnable(stack));
		t.start();
		for(int index = 0; index < 20; index = index + 2){
			stack.push(index);
		}

		t.join();
		System.out.println(stack);
	}
	
	static class Node{
		Integer item;
		Node next;
		public Node(Integer item){
			this.item = item;
		}
	}
	
	AtomicReference<Node> header = new AtomicReference<Node>();
	
	public void push(Integer item){
		Node newHead = new Node(item);
		Node oldHead;
		do{
			oldHead = this.header.get();
			newHead.next = oldHead;
		}while(!this.header.compareAndSet(oldHead, newHead));
	}
	
	public Integer pop(){
		Node newHead;
		Node oldHead;
		do{
			oldHead = this.header.get();
			newHead = oldHead.next;
		}while(!this.header.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		Node node = this.header.get();
		while(node != null){
			builder.append(node.item).append("->");
			node = node.next;
		}
		return builder.toString();
	}
}
