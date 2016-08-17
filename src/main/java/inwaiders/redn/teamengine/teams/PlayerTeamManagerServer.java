package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.base.ManagerBase;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerTeamManagerServer extends ManagerBase<PlayerTeamServer>{

	public static final PlayerTeamManagerServer instance = new PlayerTeamManagerServer();

	@Override
	protected PlayerTeamServer getDefaultValue(EntityPlayer ep)
	{
		return new PlayerTeamServer("ANY");
	}
	
}
