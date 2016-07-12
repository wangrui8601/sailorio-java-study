package study.java.designpattern.simplefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BMW extends Car{
	private static final Logger log = LoggerFactory.getLogger(BMW.class);

	public BMW(){
		name = "BMW";
	}
	
	public void drive(){
		log.info("my name is {}, I'm on the way", name);
	}
}
