package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMNoteBook extends NoteBook {
	private static final Logger log = LoggerFactory.getLogger(SMNoteBook.class);
	@Override
	public void compute(String val) {
		log.info("I'm SMNoteBook, and give a compute of {}", val);
	}

}
