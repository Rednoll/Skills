package inwaiders.redn.rpg.skills.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.utils.Targeting.Target;

public abstract class ItemSkillBase {

	public static enum ItemSkillType {
		TICK, HITWEARER, /* Armor */
		HITTARGET, /* Sword */
	}
	protected int level = 1;
	protected ItemSkillType type;
	protected int[] damage;
	protected int[] radius;
	protected int interval = 0;
	protected int maxInterval = 0;
	protected Target target;
	protected int id;
	protected final int MAX_SKILL_LVL;

	public ItemSkillBase(int maxLvl) {
		MAX_SKILL_LVL = maxLvl;
		damage = new int[MAX_SKILL_LVL];
		radius = new int[MAX_SKILL_LVL];
	}
	
	public ItemSkillBase() {
		this(Constants.DEFAUL_MAX_SKILL_LVL);
	}

	public int getDamage() {
		int lvl = getLevel();
		return damage[lvl] == 0 ? damage[1] * lvl : damage[lvl];
	}

	public int getRadius() {
		int lvl = getLevel();
		return radius[lvl] == 0 ? radius[1] * lvl : radius[lvl];
	}

	public int getMaxInterval() {
		return maxInterval;
	}

	public int getMaxLvl() {
		return MAX_SKILL_LVL;
	}

	public int getLevel() {
		return level;
	}

	public int getInterval() {
		return interval;
	}

	public Target getTarget() {
		return target;
	}

	public ItemSkillType getType() {
		return type;
	}

	public void preWhileUpdate(EntityPlayer ep) {

		if (interval >= getMaxInterval()) {

			interval = 0;
			whileUpdate(ep);
		}

		interval++;
	}

	public void whileUpdate(EntityPlayer ep) {

	}

	public abstract int getId();

	public abstract String getName();

	public abstract int getInstallCost();
}
