package inwaiders.redn.rpg.skills;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class BaseSkill {
	
	
	protected int coolDown = 0;
	protected int isPassive = 0;
	protected int level = 1;
	protected int isAura = 0;
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
	public BaseSkill(int maxLvl) {
		MAX_SKILL_LVL = maxLvl + 1;
		damage = new int[MAX_SKILL_LVL];
		radius = new int[MAX_SKILL_LVL];
		maxCast = new int[MAX_SKILL_LVL];
		maxCoolDown = new int[MAX_SKILL_LVL];
	}
	
	public BaseSkill() {
		this(Constants.DEFAUL_MAX_SKILL_LVL);
	}
	
	public void skillStart(EntityPlayer ep){
		
	}
	
	public void preWhileUpdate(EntityPlayer ep){
		
		if(interval >= getMaxInterval()){
			
			interval = 0;
			whileUpdate(ep);
		}
		
		
		interval++;
	}
	
	public void whileUpdate(EntityPlayer ep){
	
	}
	
	public void preCast(EntityPlayer ep){
		
		if(casting){
			
			if(getCast() >= getMaxCast(ep)){
				
				setCast(0);
				casting = false;
				if(!ep.capabilities.isCreativeMode)
				setCoolDown(getMaxCoolDown(ep));
				skillStart(ep);
			}
		
			castUpdate(ep);
		
			setCast(getCast()+1);
		}
		
	}
	
	public void castUpdate(EntityPlayer ep){
		
	}
	
	public void startCasting(){
		if(getCoolDown() <= 0){
			casting = true;
		}
	}
	
	public int isPassive(){
		return isPassive;
	}
	
	public int isAura(){
		return isAura;
	}
	
	public void setPassive(int i){
		isPassive = i;
	}
	
	public void setRadiusByLevel(int i, int r){
		this.radius[i] = r;
	}
	
	public void setDamageByLevel(int i, int damage){
		this.damage[i] = damage;
	}
	
	
	public void setAura(int i){
		isAura = i;
	}
	
	public int getMaxCoolDown(EntityPlayer ep){
		int i = getLevel();
		return Math.max(0, (maxCoolDown[i] == 0 ? maxCoolDown[0] * i : maxCoolDown[i]) - PlayerInfoManagerServer.instance.get(ep).getPassiveEffects().cddecrease); 
	}
	
	public int getRadius(EntityPlayer ep){
		int i = getLevel();
		return Math.max(0, (radius[i] == 0 ? radius[0] * i : radius[i]) + PlayerInfoManagerServer.instance.get(ep).getPassiveEffects().radiusincrease);
	}
	
	public int getMaxInterval(){
		return maxInterval;
	}
	
	public int getCast(){
		return cast;
	}
	
	public int getMaxCast(EntityPlayer ep){
		int i = getLevel();
		return Math.max(0, (maxCast[i] == 0 ? maxCast[0] * i : maxCast[i]) - PlayerInfoManagerServer.instance.get(ep).getPassiveEffects().casttimedecrease);
	}
	
	public int getCoolDown(){
		return coolDown;
	}
	
	public Target getTarget(){
		return target;
	}
	
	public void setMaxCoolDownByLevel(int i, int c){
		maxCoolDown[i] = c;
	}
	
	public void setTarget(Target i){
		target = i;
	}
	
	public void setCoolDown(int i){
		coolDown = i;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int i){
		level = Math.min(MAX_SKILL_LVL, i);
	}
	
	public void setMaxInterval(int i){
		maxInterval = i;
	}
	
	public void setCast(int i){
		cast = i;
	}
	
	public int getDamage(EntityPlayer ep){
		int i = getLevel();
		return Math.max(0, (damage[i] == 0 ? damage[0] * i : damage[i]) - PlayerInfoManagerServer.instance.get(ep).getPassiveEffects().damageincrease);
	}
	
	public void setMaxCast(int i, int c){
		maxCast[i] = c;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getMaxLvl()
	{
		return MAX_SKILL_LVL;
	}
	
	public abstract int getId();
	public abstract String getName();
	public abstract LearnPointsPrice getPrice();
	public abstract ResourceLocation getTexture();
}
