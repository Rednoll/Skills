package Inwaiders.redn.teamengine.teams;

import java.util.ArrayList;
import java.util.List;

public class GeterTMStoP {

	static List par = new ArrayList();
	
	public GeterTMStoP(){
		
	}
	
	public static void setParamToEntity(String name, TeamMainClassServerProvider buffer){
		
		if(isRegister(name) == -1){
			par.add(new ComposerTeamMainClassServerProvider(name, buffer));
		}
		else{
			par.set(isRegister(name), new ComposerTeamMainClassServerProvider(name, buffer));
		}
	}
	
	public static int isRegister(String name){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerTeamMainClassServerProvider c = (ComposerTeamMainClassServerProvider) par.get(i);
			
			if(c.getId().equals(name)){
				return i;
			}
		}
		
		return -1;
	}
	
	public static TeamMainClassServerProvider getParam(String name){
		
		if(isRegister(name) == -1){
			setParamToEntity(name, new TeamMainClassServerProvider(name));
			return getParam(name);
		}
		else{
			
			ComposerTeamMainClassServerProvider c = (ComposerTeamMainClassServerProvider) par.get(isRegister(name));
			return c.getParam();
		}
	}
}
