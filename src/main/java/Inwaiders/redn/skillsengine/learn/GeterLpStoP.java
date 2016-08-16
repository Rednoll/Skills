package Inwaiders.redn.skillsengine.learn;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class GeterLpStoP {

	static List par = new ArrayList();
	
	public GeterLpStoP(){
		
	}
	
	public static void setParamToEntity(int id, LearnPointsServerProvider buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerLearnPointsServer(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerLearnPointsServer(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerLearnPointsServer c = (ComposerLearnPointsServer) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static LearnPointsServerProvider getParam(EntityPlayer ep){
		
		if(isRegister(ep.getEntityId()) == -1){
			setParamToEntity(ep.getEntityId(), new LearnPointsServerProvider());
			return getParam(ep);
		}
		else{
			
			ComposerLearnPointsServer c = (ComposerLearnPointsServer) par.get(isRegister(ep.getEntityId()));
			return c.getParam();
		}
	}
	
}
