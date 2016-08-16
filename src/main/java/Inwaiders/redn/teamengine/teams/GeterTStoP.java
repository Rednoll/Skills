package Inwaiders.redn.teamengine.teams;

import java.util.ArrayList;
import java.util.List;

import Inwaiders.redn.skillsengine.hotbar.ComposerHotBar;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;

public class GeterTStoP {

	static List par = new ArrayList();
	
	public GeterTStoP(){
		
	}
	
	public static void setParamToEntity(int id, TeamEngineServerProvider buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerTeamPersonalEngineServerProvider(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerTeamPersonalEngineServerProvider(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerTeamPersonalEngineServerProvider c = (ComposerTeamPersonalEngineServerProvider) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static TeamEngineServerProvider getParam(int id){
		
		if(isRegister(id) == -1){
			setParamToEntity(id, new TeamEngineServerProvider("ANY"));
			return getParam(id);
		}
		else{
			
			ComposerTeamPersonalEngineServerProvider c = (ComposerTeamPersonalEngineServerProvider) par.get(isRegister(id));
			return c.getParam();
		}
	}
	
}
