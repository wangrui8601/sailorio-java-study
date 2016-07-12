package study.java.designpattern.simplefactory;

public abstract class Car {
	protected String name;
	
	public abstract void drive();
	
	public String getName(){
		return name;
	}
}
