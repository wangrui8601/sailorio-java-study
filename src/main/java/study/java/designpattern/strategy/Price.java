package study.java.designpattern.strategy;

public class Price {
	public static double quote(double booksPrice, MemberStrategy strategy){
		return strategy.calPrice(booksPrice);
	}
}
