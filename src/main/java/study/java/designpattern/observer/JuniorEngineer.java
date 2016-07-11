package study.java.designpattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JuniorEngineer implements ITalent {
	
	private static final Logger log = LoggerFactory.getLogger(JuniorEngineer.class);
	
	public void newJob(String job) {
		log.info("juniorengineer gets position {}", job);
	}
}
