package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMPhone extends Phone {
	private static final Logger log = LoggerFactory.getLogger(SMPhone.class);
	
	@Override
	public void sendMessage(String msg) {
		log.info("I'm SMPhone, and send a msg {}", msg);
	}

}
