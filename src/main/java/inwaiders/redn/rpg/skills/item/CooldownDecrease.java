package inwaiders.redn.rpg.skills.item;

import net.minecraft.entity.player.EntityPlayer;
import inwaiders.redn.rpg.files.CFG;

public class CooldownDecrease extends ItemSkillBase {

	
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
	public int getId() {
		return CFG.cooldowdecID;
	}

	@Override
	public String getName() {
		return "ItemSkillCoolldownDecrease";
	}

	@Override
	public int getInstallCost() {
		return 2 * getLevel();
	}

}
