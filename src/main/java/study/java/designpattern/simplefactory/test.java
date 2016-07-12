package study.java.designpattern.simplefactory;

public class test {
	public static void main(String[] args){
		Car car = null;
		car = CarFactory.newCar();
		if(car != null){
			car.drive();
		}
	}
}
