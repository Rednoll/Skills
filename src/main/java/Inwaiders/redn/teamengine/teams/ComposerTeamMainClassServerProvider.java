package Inwaiders.redn.teamengine.teams;

public class ComposerTeamMainClassServerProvider {

	private String name = "";
	
	private TeamMainClassServerProvider bank;
		
	public ComposerTeamMainClassServerProvider(String name, TeamMainClassServerProvider bank){
			
		this.name = name;
		this.bank = bank;
	}
		
	public ComposerTeamMainClassServerProvider setId(String i){
			this.name = i;
			return this;
	}
		
	public ComposerTeamMainClassServerProvider setParam(TeamMainClassServerProvider i){
		this.bank = i;
		return this;
	}
		
	public String getId(){
		return this.name;
	}
		
	public TeamMainClassServerProvider getParam(){
		return this.bank;
	}
}