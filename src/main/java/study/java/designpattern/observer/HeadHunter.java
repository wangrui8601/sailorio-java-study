package study.java.designpattern.observer;

public class HeadHunter extends AbstractHR{
	@Override
	public void publishJob(String job) {
		for(ITalent talent : talents){
			talent.newJob(job);
		}
	}
}
