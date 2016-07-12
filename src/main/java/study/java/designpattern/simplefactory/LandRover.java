package study.java.designpattern.simplefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandRover extends Car{
	private static final Logger log = LoggerFactory.getLogger(LandRover.class);
	
	public LandRover(){
		name = "LandRover";
	}
	
	public void drive(){
		log.info("my name is {}, I'm on the way", name);
	}
}
