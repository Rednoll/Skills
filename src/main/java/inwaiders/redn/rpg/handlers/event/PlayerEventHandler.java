package inwaiders.redn.rpg.handlers.event;

import inwaiders.redn.rpg.handlers.EventCancelException;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.managers.client.TeamManagerClient;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import inwaiders.redn.rpg.storage.client.TeamClient;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.storage.server.TeamServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerEventHandler {

	@SubscribeEvent
	public void updater(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) event.entityLiving;
			if (!event.entity.worldObj.isRemote) {
				PlayerInfoServer playerInfo = PlayerInfoManagerServer.instance.get(ep);
				playerInfo.update(ep);
				if (!playerInfo.team.equals("ANY") && !playerInfo.team.equals("")) {
					TeamServer team = TeamManagerServer.instance.get(playerInfo.team);
					if (team != null) {
						team.update(ep);
					}
					else
					{
						System.out.println("team " + playerInfo.team + " not found");
					}
				}
			} else {
				PlayerInfoClient playerInfo = PlayerInfoManagerClient.instance.get(ep);
				playerInfo.update(ep);
				if (!playerInfo.team.equals("ANY") && !playerInfo.team.equals("")) {
					TeamClient team = TeamManagerClient.instance.get(playerInfo.team);
					if (team != null) {
						
						team.update(ep);
					}
					else
					{
						System.out.println("team" + playerInfo.team + "not found");
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onHit(LivingHurtEvent e) {
		try {
			if (e.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e.entityLiving;
				PlayerInfoServer info = PlayerInfoManagerServer.instance.get(player);
				info.updateOnWearerHurtSkills(e);
			} else if (e.source.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e.source.getEntity();
				PlayerInfoServer info = PlayerInfoManagerServer.instance.get(player);
				info.updateOnTargetHurtSkills(e);
			}
		} catch (EventCancelException ex) {
			e.setCanceled(true);
		}
	}

}
