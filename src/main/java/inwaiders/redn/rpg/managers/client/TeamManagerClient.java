package inwaiders.redn.rpg.managers.client;

import inwaiders.redn.rpg.managers.AbstractManager;
import inwaiders.redn.rpg.storage.client.TeamClient;

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
