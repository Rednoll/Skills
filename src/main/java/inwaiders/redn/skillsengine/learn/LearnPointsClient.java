package inwaiders.redn.skillsengine.learn;

public class LearnPointsClient {

	int lpoints = 0;
	
	public void setLearnPoints(int i){
		this.lpoints = i;
	}
	
	public int getLearnPoints(){
		return this.lpoints;
	}
	
	public boolean canLearn(int price){
		return this.getLearnPoints() - price >= 0 ? true : false; 
	}
	
	public void learn(int price){
		if(canLearn(price)) 
			this.setLearnPoints(this.getLearnPoints() - price);
	}
	
}
