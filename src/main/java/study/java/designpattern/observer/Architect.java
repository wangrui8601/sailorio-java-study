package study.java.designpattern.observer;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Architect implements ITalent {
	private static final Logger log = LoggerFactory.getLogger(Architect.class);
	
	public void newJob(String job){
		log.info("architect gets new position {}", job);
	}

}
