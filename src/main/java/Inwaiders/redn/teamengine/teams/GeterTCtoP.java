package Inwaiders.redn.teamengine.teams;

import java.util.ArrayList;
import java.util.List;

import Inwaiders.redn.skillsengine.hotbar.ComposerHotBar;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;

public class GeterTCtoP {

	static List par = new ArrayList();
	
	public GeterTCtoP(){
		
	}
	
	public static void setParamToEntity(int id, TeamEngineClientProvider buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerTeamPersonalEngineClientProvider(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerTeamPersonalEngineClientProvider(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerTeamPersonalEngineClientProvider c = (ComposerTeamPersonalEngineClientProvider) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static TeamEngineClientProvider getParam(int id){
		
		if(isRegister(id) == -1){
			setParamToEntity(id, new TeamEngineClientProvider("ANY"));
			return getParam(id);
		}
		else{
			
			ComposerTeamPersonalEngineClientProvider c = (ComposerTeamPersonalEngineClientProvider) par.get(isRegister(id));
			return c.getParam();
		}
	}
	
}
