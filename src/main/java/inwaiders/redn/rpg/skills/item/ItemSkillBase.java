package inwaiders.redn.rpg.skills.item;

import javax.annotation.Nullable;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class ItemSkillBase {

	public static enum ItemSkillType {
		PASSIVE,//Decrease cd, e.t.c
		TICK,
		HITWEARER,//Armor
		HITTARGET,//Sword 
	}

	public static class PassiveEffect
	{
		public int cddecrease = 0;
		public int manadecrease = 0;//Unused at this moment
		public int casttimedecrease = 0;
		public int intevaldecrease = 0;
		public int radiusincrease = 0;
		public int damageincrease = 0;
	}
	
	protected int level = 0;
	protected int cd;
	protected ItemSkillType type;
	protected int[] damage;
	protected int[] radius;
	protected int[] maxCd;
	protected int interval = 0;
	protected int maxInterval = 0;
	protected Target target;
	protected int id;
	protected final int MAX_SKILL_LVL;

	public ItemSkillBase(int maxLvl) {
		MAX_SKILL_LVL = maxLvl;
		damage = new int[MAX_SKILL_LVL];
		radius = new int[MAX_SKILL_LVL];
		maxCd = new int[MAX_SKILL_LVL];
	}

	public ItemSkillBase() {
		this(Constants.DEFAUL_MAX_SKILL_LVL);
	}

	public int getDamage() {
		int lvl = getLevel();
		return damage[lvl] == 0 ? damage[0] * lvl : damage[lvl];
	}

	public int getRadius() {
		int lvl = getLevel();
		return radius[lvl] == 0 ? radius[0] * lvl : radius[lvl];
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

	public int getCd() {
		return cd;
	}

	public void decrCd() {
		cd--;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	/**
	 * Called with each cast of any skill
	 */
	public PassiveEffect getPassiveEffect(EntityPlayer ep)
	{
		return null;
	}

	public void preWhileUpdate(EntityPlayer ep) {

		if (type == ItemSkillType.TICK) {
			if (interval >= getMaxInterval()) {

				interval = 0;
				perform(null);
			}

			interval++;
		}
	}

	public void preWearerHited(LivingHurtEvent e) {
		if(cd <= 0)
		{
			perform(e);
			cd = maxCd[level];
		}
	}

	public void preTargetHited(LivingHurtEvent e) {
		if(cd <= 0)
		{
			perform(e);
			cd = maxCd[level];
		}
	}

	/**
	 *  <div>E - {@code e.entity} or {@code e.entityLiving}</div>
	 *  <div>D - {@code e.amount} - damge dealt</div>
	 *  <div>A - {@code e.source.getEntity()}</div>
	 *  <br>
	 *  <div><i><span style="color: #993E83;">TYPE</span> - <span style="color: #6BDAF8;">HITWEARER</span>:</i></div>
	 *  E - Player<br>
	 *  A = Attacker<br>
	 * 	<div><i><span style="color: #993E83;">TYPE</span> - <span style="color: #6BDAF8;">HITTARGET</span>:</i></div>
	 *  E - target<br>
	 *  A - Player<br>
	 *  <div><i><span style="color: #993E83;">TYPE</span> - <span style="color: #6BDAF8;">TICK</span>:</i></div>
	 *  Argument always <span style="color: #A42867;">null</span>
	 *  <div><i><span style="color: #993E83;">TYPE</span> - <span style="color: #6BDAF8;">PASSIVE</span>:</i></div>
	 *  Never called
	 */
	public void perform(@Nullable LivingHurtEvent e) {
		
	}

	public abstract String getName();

	public abstract int getInstallCost();
}
