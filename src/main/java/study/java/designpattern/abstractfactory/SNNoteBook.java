package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SNNoteBook extends NoteBook {
	private static final Logger log = LoggerFactory.getLogger(SNNoteBook.class);
	@Override
	public void compute(String val) {
		log.info("I'm SNNotebook, and give a compute of {}", val);
	}

}
