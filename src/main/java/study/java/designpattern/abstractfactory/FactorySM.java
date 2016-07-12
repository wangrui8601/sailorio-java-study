package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactorySM extends Factory {
	private static final Logger log = LoggerFactory.getLogger(FactorySM.class);
	@Override
	public Phone createPhone() {
		log.info("here is SM factory, and create a SMPhone");
		Phone phone = new SMPhone();
		return phone;
	}

	@Override
	public Camera createCamera() {
		log.info("here is SM factory, and create a SMCamera");
		Camera camera = new SMCamera();
		return camera;
	}

	@Override
	public NoteBook createNoteBook() {
		log.info("here is SM factory, and create a SMNoteBook");
		NoteBook note = new SMNoteBook();
		return note;
	}

}
