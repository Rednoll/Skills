package inwaiders.redn.teamengine.targeting;

import inwaiders.redn.teamengine.teams.PlayerTeamManagerServer;
import inwaiders.redn.teamengine.teams.PlayerTeamServer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Targeting {

	public static final String TARGET_ANOTHER = "TARGET_ANOTHER";
	public static final String TARGET_FRIEND = "TARGET_FRIEND";
	public static final String TARGET_ALL = "TARGET_ALL";
	
	public static boolean canAttack(EntityLivingBase p, EntityLivingBase t, String type){
		if(!(p instanceof EntityPlayer) || !(t instanceof EntityPlayer))
		{
			return true;
		}
		if(type.equals(TARGET_ALL)) return true;
		EntityPlayer player = (EntityPlayer) p, toAttack = (EntityPlayer) t;
		PlayerTeamServer te1 = PlayerTeamManagerServer.instance.get(toAttack);
		PlayerTeamServer te2 = PlayerTeamManagerServer.instance.get(player);
		
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
