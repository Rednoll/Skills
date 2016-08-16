package Inwaiders.redn.skillsengine.learn;

public class ComposerLearnPointsServer {

	private int id = 0;
	
	private LearnPointsServerProvider bank;
		
	public ComposerLearnPointsServer(int id, LearnPointsServerProvider bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerLearnPointsServer setId(int i){
			this.id = i;
			return this;
	}
		
	public ComposerLearnPointsServer setParam(LearnPointsServerProvider i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public LearnPointsServerProvider getParam(){
		return this.bank;
	}
	
}
