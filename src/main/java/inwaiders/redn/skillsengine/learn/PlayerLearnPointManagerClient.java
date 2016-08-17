package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.rpg.base.ManagerBase;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerLearnPointManagerClient extends ManagerBase<LearnPointsClient> {

	public static final PlayerLearnPointManagerClient instance = new PlayerLearnPointManagerClient();
	
	@Override
	protected LearnPointsClient getDefaultValue(EntityPlayer key)
	{
		return new LearnPointsClient();
	}

	
	
}
