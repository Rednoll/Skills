package Inwaiders.redn.teamengine.teams;

public class ComposerTeamMainClassClientProvider {

	private String name = "";
	
	private TeamMainClassClientProvider bank;
		
	public ComposerTeamMainClassClientProvider(String name, TeamMainClassClientProvider bank){
			
		this.name = name;
		this.bank = bank;
	}
		
	public ComposerTeamMainClassClientProvider setId(String i){
			this.name = i;
			return this;
	}
		
	public ComposerTeamMainClassClientProvider setParam(TeamMainClassClientProvider i){
		this.bank = i;
		return this;
	}
		
	public String getId(){
		return this.name;
	}
		
	public TeamMainClassClientProvider getParam(){
		return this.bank;
	}
}