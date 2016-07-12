package study.java.designpattern.simplefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Benz extends Car{
	private static final Logger log = LoggerFactory.getLogger(Benz.class);
	
	public Benz(){
		name = "Benz";
	}
	
	public void drive(){
		log.info("my name is {}, I'm on the way", name);
	}

}
