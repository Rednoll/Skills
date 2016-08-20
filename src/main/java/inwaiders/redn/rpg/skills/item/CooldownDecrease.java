package inwaiders.redn.rpg.skills.item;

import net.minecraft.entity.player.EntityPlayer;
import inwaiders.redn.rpg.files.CFG;

public class CooldownDecrease extends ItemSkillBase {

	public static final String NAME = "ItemSkillCoolldownDecrease";

	public CooldownDecrease() {
		type = ItemSkillType.PASSIVE;
	}
	
	@Override
	public PassiveEffect getPassiveEffect(EntityPlayer ep) {
		PassiveEffect ret = new PassiveEffect();
		ret.cddecrease = 2 * getLevel() + 1;
		return ret;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getInstallCost() {
		return 2 * getLevel();
	}

}
