package inwaiders.redn.rpg.managers.server;

import cpw.mods.fml.common.Mod.Instance;
import inwaiders.redn.rpg.managers.AbstractManager;
import inwaiders.redn.rpg.storage.server.TeamServer;

public class TeamManagerServer extends AbstractManager<String, TeamServer> {

	public static final TeamManagerServer instance = new TeamManagerServer(); 
	
	@Override
	public void set(String key, TeamServer value)
	{
		if(value == null || !value.getTeamName().equals("ANY") && !value.getTeamName().equals(""))
		super.set(key, value);
	}
	
	@Override
	protected TeamServer getDefaultValue(String key)
	{
		return new TeamServer(key);
	}
}
