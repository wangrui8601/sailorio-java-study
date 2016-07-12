package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class INoteBook extends NoteBook {
	private static final Logger log = LoggerFactory.getLogger(INoteBook.class);

	@Override
	public void compute(String val) {
		log.info("I'm INoteBook, and give a compute of {}", val);
	}

}
