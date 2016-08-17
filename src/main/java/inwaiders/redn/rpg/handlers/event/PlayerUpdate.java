package inwaiders.redn.rpg.handlers.event;

import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.managers.client.TeamManagerClient;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerUpdate
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

}
