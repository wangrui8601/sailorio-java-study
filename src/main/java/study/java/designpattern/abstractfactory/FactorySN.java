package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactorySN extends Factory {
	private static final Logger log = LoggerFactory.getLogger(FactorySN.class);
	@Override
	public Phone createPhone() {
		log.info("here is SN factory, and create a SNPhone");
		Phone phone = new SNPhone();
		return phone;
	}

	@Override
	public Camera createCamera() {
		log.info("here is SN factory, and create a SNCamera");
		Camera camera = new SNCamera();
		return camera;
	}

	@Override
	public NoteBook createNoteBook() {
		log.info("here is SN factory, and create a SNNoteBook");
		NoteBook note = new SNNoteBook();
		return note;
	}

}
