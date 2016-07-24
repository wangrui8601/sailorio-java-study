package study.java.designpattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancedMemberStrategy implements MemberStrategy{
	private static final Logger logger = LoggerFactory.getLogger(AdvancedMemberStrategy.class);
	
	public double calPrice(double booksPrice){
		logger.info("高级会员80%折扣");
		return booksPrice * 0.8;
	}
}
