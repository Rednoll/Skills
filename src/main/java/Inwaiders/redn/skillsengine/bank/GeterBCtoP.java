package Inwaiders.redn.skillsengine.bank;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class GeterBCtoP {

	static List par = new ArrayList();
	
	public GeterBCtoP(){
		
	}
	
	public static void setParamToEntity(int id, SkillsBankClientProvider buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerBankClient(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerBankClient(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerBankClient c = (ComposerBankClient) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static SkillsBankClientProvider getParam(EntityPlayer ep){
		
		if(isRegister(ep.getEntityId()) == -1){
			setParamToEntity(ep.getEntityId(), new SkillsBankClientProvider(ep));
			return getParam(ep);
		}
		else{
			
			ComposerBankClient c = (ComposerBankClient) par.get(isRegister(ep.getEntityId()));
			return c.getParam();
		}
	}
	
}
