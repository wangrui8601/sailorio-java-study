package study.java.designpattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimaryMemberStrategy implements MemberStrategy{
	private static final Logger logger = LoggerFactory.getLogger(PrimaryMemberStrategy.class);
	public double calPrice(double booksPrice){
		logger.info("初级会员没有折扣");
		return booksPrice;
	}
}
