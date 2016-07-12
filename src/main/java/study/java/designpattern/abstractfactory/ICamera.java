package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ICamera extends Camera{
	private static final Logger log = LoggerFactory.getLogger(ICamera.class);

	@Override
	public void photo(String name) {
		log.info("I'm ICamera, and give a photo for {}", name);
	}
}
