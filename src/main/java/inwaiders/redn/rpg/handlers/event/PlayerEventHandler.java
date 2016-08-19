package inwaiders.redn.rpg.handlers.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import inwaiders.redn.rpg.handlers.EventCancelException;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.managers.client.TeamManagerClient;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerEventHandler
{

	@SubscribeEvent
	public void updater(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer ep = (EntityPlayer) event.entityLiving;
			if (!event.entity.worldObj.isRemote)
			{
				PlayerInfoServer playerInfo = PlayerInfoManagerServer.instance.get(ep);
				playerInfo.update(ep);
				if(!playerInfo.getTeam().equals("ANY") && !playerInfo.getTeam().equals(""))
				{
					TeamManagerServer.instance.get(playerInfo.getTeam()).update(ep);
				}
			}
			else
			{
				PlayerInfoClient playerInfo = PlayerInfoManagerClient.instance.get(ep);
				playerInfo.update(ep);
				TeamManagerClient.instance.get(playerInfo.getTeam()).update(ep);

			}
		}
	}
	
	@SubscribeEvent
	public void onHit(LivingHurtEvent e)
	{
		try
		{
			if (e.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e.entityLiving;
				PlayerInfoServer info = PlayerInfoManagerServer.instance.get(player);
				info.updateOnWearerHurtSkills(e.source.getEntity(), (int)e.ammount);
			}
			else if (e.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) e.source.getEntity();
				PlayerInfoServer info = PlayerInfoManagerServer.instance.get(player);
				info.updateOnTargetHurtSkills(e.entity, (int)e.ammount);
			}
		}
		catch(EventCancelException ex)
		{
			e.setCanceled(true);
		}
	}

}
