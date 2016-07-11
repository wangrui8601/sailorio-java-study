package study.java.designpattern.observer;

public class test {
	public static void main(String[] args){
		ITalent t1 = new Architect();
		ITalent t2 = new JuniorEngineer();
		ITalent t3 = new SeniorEngineer();
		
		AbstractHR hr = new HeadHunter();
		hr.addTalent(t1);
		hr.addTalent(t2);
		hr.addTalent(t3);
		
		hr.publishJob("top 500 big data position");
		hr.removeTalent(t3);
		hr.publishJob("one once");
	}

}
