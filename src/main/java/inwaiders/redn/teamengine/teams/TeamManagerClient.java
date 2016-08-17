package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.base.AbstractManager;
import java.util.ArrayList;
import java.util.List;

public class TeamManagerClient extends AbstractManager<String, TeamClient> {

	public static final TeamManagerClient instance = new TeamManagerClient();

	@Override
	protected TeamClient getDefaultValue(String key)
	{
		return new TeamClient(key);
	}
}
