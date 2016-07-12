package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SNCamera extends Camera{
	private static final Logger log = LoggerFactory.getLogger(SNCamera.class);
	
	public void photo(String name){
		log.info("I'm SNCamera, and give a photo for {}", name);
	}

}
