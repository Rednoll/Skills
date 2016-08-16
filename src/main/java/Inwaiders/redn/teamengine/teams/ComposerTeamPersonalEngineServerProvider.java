package Inwaiders.redn.teamengine.teams;

import Inwaiders.redn.skillsengine.hotbar.ComposerHotBar;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;

public class ComposerTeamPersonalEngineServerProvider {

	private int id = 0;
	
	private TeamEngineServerProvider bank;
		
	public ComposerTeamPersonalEngineServerProvider(int id, TeamEngineServerProvider bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerTeamPersonalEngineServerProvider setId(int i){
			this.id = i;
			return this;
	}
	
	public ComposerTeamPersonalEngineServerProvider setParam(TeamEngineServerProvider i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public TeamEngineServerProvider getParam(){
		return this.bank;
	}
}
