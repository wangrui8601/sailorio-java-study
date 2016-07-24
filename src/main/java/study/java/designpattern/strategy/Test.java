package study.java.designpattern.strategy;

public class Test {
	public static void main(String[] args){
		double price = 100;
		System.out.println("结果： " + Price.quote(price, new IntermediateMemberStrategy()));
	}
}
