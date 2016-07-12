package study.java.designpattern.simplefactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarFactory {
	private static final Logger log = LoggerFactory.getLogger(CarFactory.class);
	
	public static Car newCar(){
		Car car = null;
		String name = null;
		
		try {
			XMLConfiguration config = new XMLConfiguration("car.xml");
			name = config.getString("car");
		} catch (ConfigurationException e) {
			log.error("parsing xml configuration file failed", e);
		}
		
		try {
			car = (Car)Class.forName(name).newInstance();
			log.info("create car class name is {}", name);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			log.error("Instantiate car {} failed", name);
		}
		
		return car;
	}

}
