package Inwaiders.redn.skillsengine.hotbar;

import java.util.ArrayList;
import java.util.List;

public class GeterHtoP {

	static List par = new ArrayList();
	
	public GeterHtoP(){
		
	}
	
	public static void setParamToEntity(int id, SkillsHotBar buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerHotBar(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerHotBar(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerHotBar c = (ComposerHotBar) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static SkillsHotBar getParam(int id){
		
		if(isRegister(id) == -1){
			setParamToEntity(id, new SkillsHotBar());
			return getParam(id);
		}
		else{
			
			ComposerHotBar c = (ComposerHotBar) par.get(isRegister(id));
			return c.getParam();
		}
	}
	
}
