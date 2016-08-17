package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.base.ManagerBase;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerTeamManagerClient extends ManagerBase<PlayerTeamClient> {

	public static final PlayerTeamManagerClient instance = new PlayerTeamManagerClient();
	
	@Override
	protected PlayerTeamClient getDefaultValue(EntityPlayer key)
	{
		return new PlayerTeamClient("ANY");
	}
}
