package inwaiders.redn.teamengine.teams;

import cpw.mods.fml.common.Mod.Instance;
import inwaiders.redn.rpg.base.AbstractManager;

public class TeamManagerServer extends AbstractManager<String, TeamServer> {

	public static final TeamManagerServer instance = new TeamManagerServer(); 
	
	@Override
	public void set(String key, TeamServer value)
	{
		if(!value.getTeamName().equals("ANY") && !value.getTeamName().equals(""))
		super.set(key, value);
	}
	
	@Override
	protected TeamServer getDefaultValue(String key)
	{
		return new TeamServer(key);
	}
}
