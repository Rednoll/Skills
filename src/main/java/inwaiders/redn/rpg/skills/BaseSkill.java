package inwaiders.redn.rpg.skills;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class BaseSkill {
	
	protected int[] maxCoolDown = new int[100];
	protected int coolDown = 0;
	protected int isPassive = 0;
	protected int level = 1;
	protected int isAura = 0;
	protected int[] damage = new int[100];
	protected int[] radius = new int[100];
	protected int cast = 0;
	protected int[] maxCast = new int[100];
	protected boolean casting = false;
	protected int interval = 0;
	protected int maxInterval = 0;
	protected Target target;
	protected int id;
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
			
			if(getCast() >= getMaxCastByLevel(getLevel())){
				
				setCast(0);
				casting = false;
				setCoolDown(getMaxCoolDownByLevel(getLevel()));
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
	
	public int getMaxCoolDownByLevel(int i ){
		return maxCoolDown[i] == 0 ? maxCoolDown[1] * i : maxCoolDown[i]; 
	}
	
	public int getRadiusByLevel(int i){
		return radius[i] == 0 ? radius[1] * i : radius[i];
	}
	
	public int getMaxInterval(){
		return maxInterval;
	}
	
	public int getCast(){
		return cast;
	}
	
	public int getMaxCastByLevel(int i){
		return maxCast[i] == 0 ? maxCast[1] * i : maxCast[i];
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
		level = i;
	}
	
	public void setMaxInterval(int i){
		maxInterval = i;
	}
	
	public void setCast(int i){
		cast = i;
	}
	
	public int getDamageByLevel(int i){
		return damage[i] == 0 ? damage[1] * i : damage[i];
	}
	
	public void setMaxCast(int i, int c){
		maxCast[i] = c;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	public abstract int getId();
	public abstract String getName();
	public abstract LearnPointsPrice getPrice();
	public abstract ResourceLocation getTexture();
}
