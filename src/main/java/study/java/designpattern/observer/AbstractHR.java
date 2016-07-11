package study.java.designpattern.observer;

import java.util.Collection;
import java.util.ArrayList;

public abstract class AbstractHR{
	protected Collection<ITalent> talents = new ArrayList<ITalent>();
	
	public void addTalent(ITalent talent){
		talents.add(talent);
	}
	
	public void removeTalent(ITalent talent){
		talents.remove(talent);
	}
	
	public abstract void publishJob(String job);
	
}
