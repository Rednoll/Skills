package Inwaiders.redn.teamengine.teams;

import java.util.ArrayList;
import java.util.List;

public class GeterTMCtoP {

	static List par = new ArrayList();
	
	public GeterTMCtoP(){
		
	}
	
	public static void setParamToEntity(String name, TeamMainClassClientProvider buffer){
		
		if(isRegister(name) == -1){
			par.add(new ComposerTeamMainClassClientProvider(name, buffer));
		}
		else{
			par.set(isRegister(name), new ComposerTeamMainClassClientProvider(name, buffer));
		}
	}
	
	public static int isRegister(String name){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerTeamMainClassClientProvider c = (ComposerTeamMainClassClientProvider) par.get(i);
			
			if(c.getId().equals(name)){
				return i;
			}
		}
		
		return -1;
	}
	
	public static TeamMainClassClientProvider getParam(String name){
		
		if(isRegister(name) == -1){
			setParamToEntity(name, new TeamMainClassClientProvider(name));
			return getParam(name);
		}
		else{
			
			ComposerTeamMainClassClientProvider c = (ComposerTeamMainClassClientProvider) par.get(isRegister(name));
			return c.getParam();
		}
	}
}
