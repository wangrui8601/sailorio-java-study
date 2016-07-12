package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SNPhone extends Phone {
	private static final Logger log = LoggerFactory.getLogger(SNPhone.class);
	@Override
	public void sendMessage(String msg) {
		log.info("I'm SNPhone, and send a msg {}", msg);
	}

}
