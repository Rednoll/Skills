package Inwaiders.redn.teamengine.teams;

import Inwaiders.redn.skillsengine.hotbar.ComposerHotBar;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;

public class ComposerTeamPersonalEngineClientProvider {

	private int id = 0;
	
	private TeamEngineClientProvider bank;
		
	public ComposerTeamPersonalEngineClientProvider(int id, TeamEngineClientProvider bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerTeamPersonalEngineClientProvider setId(int i){
			this.id = i;
			return this;
	}
		
	public ComposerTeamPersonalEngineClientProvider setParam(TeamEngineClientProvider i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public TeamEngineClientProvider getParam(){
		return this.bank;
	}
}
