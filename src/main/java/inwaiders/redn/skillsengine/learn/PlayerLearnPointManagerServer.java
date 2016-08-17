package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.rpg.base.ManagerBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerLearnPointManagerServer extends ManagerBase<LearnPointsServer>{

	public static final PlayerLearnPointManagerServer instance = new PlayerLearnPointManagerServer();

	@Override
	protected LearnPointsServer getDefaultValue(EntityPlayer key)
	{
		return new LearnPointsServer();
	}
	
}
