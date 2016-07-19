package study.java.thread.testthreadlocal;

import java.util.Random;

/*
 * 这个类有一个Student的私有变量，在run方法中，它随机产生一个整数。
 * 然后设置到student变量中，从student中读取设置后的值，然后睡眠5秒钟，最后再次读取student的age值
 */
public class ThreadDemo implements Runnable {
	private final static ThreadLocal<Student> studentLocal = new ThreadLocal<Student>();
	
	@Override
	public void run() {
		accessStudent();
	}
	
	public void accessStudent(){
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + " is running");
		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("thread " + currentThreadName + " set age to: " + age);
		Student student = getStudent();
		student.setAge(age);
		System.out.println("thread " + currentThreadName + " first read age is: " + student.getAge());
		try{
			Thread.sleep(5000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("thread " + currentThreadName + " second read age is: " + student.getAge());
	}
	
	protected Student getStudent(){
		Student student = (Student)studentLocal.get();
		if(student == null){
			student = new Student();
			studentLocal.set(student);
		}
		return student;
	}
	
	public static void main(String[] args){
		ThreadDemo td = new ThreadDemo();
		Thread t1 = new Thread(td, "a");
		Thread t2 = new Thread(td, "b");
		t1.start();
		t2.start();
	}
}
