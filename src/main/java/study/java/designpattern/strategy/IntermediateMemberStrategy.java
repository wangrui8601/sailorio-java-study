package study.java.designpattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntermediateMemberStrategy implements MemberStrategy{
	private static final Logger logger = LoggerFactory.getLogger(IntermediateMemberStrategy.class);
	public double calPrice(double booksPrice){
		logger.info("中级会员90%折扣");
		return booksPrice * 0.9;
	}
}
