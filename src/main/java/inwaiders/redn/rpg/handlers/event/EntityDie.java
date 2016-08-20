package inwaiders.redn.rpg.handlers.event;

import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.registry.XPRegistry;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityDie {

	@SubscribeEvent
	public void onDie(LivingDeathEvent e){
		if (e.source.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.source.getEntity();
			int xp = XPRegistry.getXpFor(e.entityLiving);
			if(xp != 0 && !player.worldObj.isRemote)
			{
				addXpInRadius(e.entityLiving, xp);
			}
		}
	}
	
	
	private static void addXpInRadius(EntityLivingBase from, int xp)
	{
		List<EntityPlayer> players = from.worldObj.getEntitiesWithinAABB(EntityPlayer.class, MiscUtils.createAABBFormRadius(from.posX, from.posY, from.posZ, CFG.xpRadius));
		int xpByPlayer = (int) Math.round((double) xp / players.size());
		for(EntityPlayer p : players)
		{
			PlayerInfoManagerServer.instance.get(p).addXp(xpByPlayer);
			System.out.println(PlayerInfoManagerServer.instance.get(p).getXp());
		}
	}
}
