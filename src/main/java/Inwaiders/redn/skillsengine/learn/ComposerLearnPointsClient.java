package Inwaiders.redn.skillsengine.learn;

public class ComposerLearnPointsClient {

	private int id = 0;
	
	private LearnPointsClientProvider bank;
		
	public ComposerLearnPointsClient(int id, LearnPointsClientProvider bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerLearnPointsClient setId(int i){
			this.id = i;
			return this;
	}
		
	public ComposerLearnPointsClient setParam(LearnPointsClientProvider i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public LearnPointsClientProvider getParam(){
		return this.bank;
	}
	
}
