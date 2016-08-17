package inwaiders.redn.rpg.utils;

import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Targeting {
	public static enum Target
	{
		TARGET_ANOTHER, TARGET_FRIEND, TARGET_ALL;
	}
	public static boolean canAttack(EntityLivingBase p, EntityLivingBase t, Target type){
		if(!(p instanceof EntityPlayer) || !(t instanceof EntityPlayer))
		{
			return true;
		}
		if(type.equals(Target.TARGET_ALL)) return true;
		EntityPlayer player = (EntityPlayer) p, toAttack = (EntityPlayer) t;
		PlayerInfoServer te1 = PlayerInfoManagerServer.instance.get(toAttack);
		PlayerInfoServer te2 = PlayerInfoManagerServer.instance.get(player);
		
		if(te1.getTeam().equals("ANY") || te2.getTeam().equals("ANY")){
			return true;
		}
			
		if(te1.getTeam().equals(te2.getTeam())){
			if(type.equals(Target.TARGET_FRIEND)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(type.equals(Target.TARGET_ANOTHER)){
				return true;
			}
			else{
				return false;
			}
		}
		
	}

}