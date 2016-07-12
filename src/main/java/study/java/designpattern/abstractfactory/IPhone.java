package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPhone extends Phone {
	private static final Logger log = LoggerFactory.getLogger(IPhone.class);
	@Override
	public void sendMessage(String msg) {
		log.info("I'm IPhone, and send a msg {}", msg);
	}

}
