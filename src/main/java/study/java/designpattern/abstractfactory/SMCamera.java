package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMCamera extends Camera{
	private static final Logger log = LoggerFactory.getLogger(SMCamera.class);

	@Override
	public void photo(String name) {
		log.info("I'm SMCamera, and give a photo for {}", name);
	}
}
