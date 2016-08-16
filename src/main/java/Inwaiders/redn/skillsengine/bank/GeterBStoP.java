package Inwaiders.redn.skillsengine.bank;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class GeterBStoP {

	static List par = new ArrayList();
	
	public GeterBStoP(){
		
	}
	
	public static void setParamToEntity(int id, SkillsBankServerProvider buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerBankServer(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerBankServer(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerBankServer c = (ComposerBankServer) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static SkillsBankServerProvider getParam(EntityPlayer ep){
		
		if(isRegister(ep.getEntityId()) == -1){
			setParamToEntity(ep.getEntityId(), new SkillsBankServerProvider(ep));
			return getParam(ep);
		}
		else{
			
			ComposerBankServer c = (ComposerBankServer) par.get(isRegister(ep.getEntityId()));
			return c.getParam();
		}
	}
	
}
