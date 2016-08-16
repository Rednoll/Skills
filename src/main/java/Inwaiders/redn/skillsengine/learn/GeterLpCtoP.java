package Inwaiders.redn.skillsengine.learn;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class GeterLpCtoP {

	static List par = new ArrayList();
	
	public GeterLpCtoP(){
		
	}
	
	public static void setParamToEntity(int id, LearnPointsClientProvider buffer){
		
		if(isRegister(id) == -1){
			par.add(new ComposerLearnPointsClient(id, buffer));
		}
		else{
			par.set(isRegister(id), new ComposerLearnPointsClient(id, buffer));
		}
	}
	
	public static int isRegister(int id){
		
		for(int i = 0;i<par.size();i++){
			
			ComposerLearnPointsClient c = (ComposerLearnPointsClient) par.get(i);
			
			if(c.getId() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static LearnPointsClientProvider getParam(EntityPlayer ep){
		
		if(isRegister(ep.getEntityId()) == -1){
			setParamToEntity(ep.getEntityId(), new LearnPointsClientProvider());
			return getParam(ep);
		}
		else{
			
			ComposerLearnPointsClient c = (ComposerLearnPointsClient) par.get(isRegister(ep.getEntityId()));
			return c.getParam();
		}
	}
	
}
