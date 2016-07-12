package study.java.designpattern.abstractfactory;

public class test {
	public static void main(String[] args){
		Factory factory = new FactoryApple();
		Phone phone = factory.createPhone();
		phone.sendMessage("hello world");
		
		factory = new FactorySN();
		Camera camera = factory.createCamera();
		camera.photo("sailor jupiter");
		
		factory = new FactorySM();
		NoteBook note = factory.createNoteBook();
		note.compute("distince between mars and mercury");
	}

}
