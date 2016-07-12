package study.java.designpattern.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryApple extends Factory {
	private static final Logger log = LoggerFactory.getLogger(FactoryApple.class);
	@Override
	public Phone createPhone() {
		log.info("here is apple factory, and create a IPhone");
		Phone phone = new IPhone();
		return phone;
	}

	@Override
	public Camera createCamera() {
		log.info("here is apple factory, and create a ICamera");
		Camera camera = new ICamera();
		return camera;
	}

	@Override
	public NoteBook createNoteBook() {
		log.info("here is apple factory, and create a INoteBook");
		NoteBook note = new INoteBook();
		return note;
	}

}
