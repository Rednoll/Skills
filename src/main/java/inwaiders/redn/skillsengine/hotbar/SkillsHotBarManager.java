package inwaiders.redn.skillsengine.hotbar;

import inwaiders.redn.rpg.base.ManagerBase;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class SkillsHotBarManager extends ManagerBase<SkillsHotBar> {

	public static final SkillsHotBarManager instance = new SkillsHotBarManager();
	@Override
	protected SkillsHotBar getDefaultValue(EntityPlayer key)
	{
		return new SkillsHotBar();
	}

	
	
}
