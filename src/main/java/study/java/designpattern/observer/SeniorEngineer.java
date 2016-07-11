package study.java.designpattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeniorEngineer implements ITalent {
	private static final Logger log = LoggerFactory.getLogger(SeniorEngineer.class);
	
	public void newJob(String job){
		log.info("seniorengineer gets new position {}", job);
	}
}
