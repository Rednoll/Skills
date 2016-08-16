package Inwaiders.redn.teamengine.targeting;

import Inwaiders.redn.teamengine.teams.GeterTStoP;
import Inwaiders.redn.teamengine.teams.TeamEngineServerProvider;
import net.minecraft.entity.EntityLivingBase;

public class Targeting {

	public static final String TARGET_ANOTHER = "TARGET_ANOTHER";
	public static final String TARGET_FRIEND = "TARGET_FRIEND";
	public static final String TARGET_ALL = "TARGET_ALL";
	
	public static boolean canAttack(EntityLivingBase player, EntityLivingBase toAttack, String type){
		
		if(type.equals(TARGET_ALL)) return true;
		
		TeamEngineServerProvider te1 = GeterTStoP.getParam(toAttack.getEntityId());
		TeamEngineServerProvider te2 = GeterTStoP.getParam(player.getEntityId());
		
		if(te1.getTeam().equals("ANY") || te2.getTeam().equals("ANY")){
			return true;
		}
			
		if(te1.getTeam().equals(te2.getTeam())){
			if(type.equals(TARGET_FRIEND)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(type.equals(TARGET_ANOTHER)){
				return true;
			}
			else{
				return false;
			}
		}
		
	}

}
