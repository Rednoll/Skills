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

	protected int coolDown = 0;
	protected int level = 1;
	protected ItemSkillType type;
	protected int[] damage;
	protected int[] radius;
	protected int[] maxCast;
	protected int[] maxCoolDown;
	protected int cast = 0;
	protected boolean casting = false;
	protected int interval = 0;
	protected int maxInterval = 0;
	protected Target target;
	protected int id;
	protected final int MAX_SKILL_LVL;

	public ItemSkillBase(int maxLvl) {
		MAX_SKILL_LVL = maxLvl;
		damage = new int[MAX_SKILL_LVL];
		radius = new int[MAX_SKILL_LVL];
		maxCast = new int[MAX_SKILL_LVL];
		maxCoolDown = new int[MAX_SKILL_LVL];
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

	public int getMaxCoolDown() {
		int lvl = getLevel();
		return maxCoolDown[lvl] == 0 ? maxCoolDown[1] * lvl : maxCoolDown[lvl];
	}

	public int getMaxCast() {
		int lvl = getLevel();
		return maxCast[lvl] == 0 ? getMaxCast(lvl - 1) : maxCast[lvl];
	}

	private int getMaxCast(int lvl) {
		return maxCast[lvl] == 0 ? getMaxCast(lvl - 1) : maxCast[lvl];
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

	public int getCast() {
		return cast;
	}

	public Target getTarget() {
		return target;
	}

	public ItemSkillType getType() {
		return type;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void skillStart(EntityPlayer ep) {

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

	public void preCast(EntityPlayer ep) {

		if (casting) {

			if (getCast() >= getMaxCast()) {

				cast = 0;
				casting = false;
				if (!ep.capabilities.isCreativeMode)
					coolDown = getMaxCoolDown();
				skillStart(ep);
			}

			castUpdate(ep);

			cast++;
		}

	}

	public void castUpdate(EntityPlayer ep) {

	}

	public void startCasting() {
		if (coolDown <= 0) {
			casting = true;
		}
	}

	public abstract int getId();

	public abstract String getName();

	public abstract int getInstallCost();
}
