package inwaiders.redn.rpg.base;

import inwaiders.redn.skillsengine.bank.BankManagerClient;
import inwaiders.redn.skillsengine.bank.BankManagerServer;
import inwaiders.redn.skillsengine.learn.PlayerLearnPointManagerServer;
import inwaiders.redn.teamengine.teams.PlayerTeamClient;
import inwaiders.redn.teamengine.teams.PlayerTeamManagerClient;
import inwaiders.redn.teamengine.teams.PlayerTeamManagerServer;
import inwaiders.redn.teamengine.teams.PlayerTeamServer;
import inwaiders.redn.teamengine.teams.TeamClient;
import inwaiders.redn.teamengine.teams.TeamManagerClient;
import inwaiders.redn.teamengine.teams.TeamManagerServer;
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
				BankManagerServer.instance.get(ep).update();
				PlayerTeamServer playerTeam = PlayerTeamManagerServer.instance.get(ep);
				playerTeam.update(ep);
				if(!playerTeam.getTeam().equals("ANY") && !playerTeam.getTeam().equals(""))
				{
					TeamManagerServer.instance.get(playerTeam.getTeam()).update(ep);
				}
				PlayerLearnPointManagerServer.instance.get(ep).update(ep);
			}
			else
			{
				BankManagerClient.instance.get(ep).update();
				PlayerTeamClient playerTeam = PlayerTeamManagerClient.instance.get(ep);
				playerTeam.update(ep);
				TeamManagerClient.instance.get(playerTeam.getTeam()).update(ep);

			}
		}
	}

}
