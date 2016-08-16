package Inwaiders.redn.skillsengine.examples;

import Inwaiders.redn.rpg.base.base;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class BaseSkill {
	
	int[] maxCoolDown = new int[100];
	int coolDown = 0;
	int isPassive = 0;
	int level = 1;
	int isAura = 0;
	int[] damage = new int[100];
	int[] radius = new int[100];
	int cast = 0;
	int[] maxCast = new int[100];
	boolean casting = false;
	int interval = 0;
	int maxInterval = 0;
	String target;
	
	public BaseSkill(){
		
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
		return maxCoolDown[i];
	}
	
	public int getRadiusByLevel(int i){
		return radius[i];
	}
	
	public int getMaxInterval(){
		return maxInterval;
	}
	
	public int getCast(){
		return cast;
	}
	
	public int getMaxCastByLevel(int i){
		return maxCast[i];
	}
	
	public int getCoolDown(){
		return coolDown;
	}
	
	public String getTarget(){
		return target;
	}
	
	public void setMaxCoolDownByLevel(int i, int c){
		maxCoolDown[i] = c;
	}
	
	public void setTarget(String i){
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
		return damage[i];
	}
	
	public void setMaxCast(int i, int c){
		maxCast[i] = c;
	}
	
	public int getId(){
		return 0;
	}
	
	public ResourceLocation getTexture(){
		return new ResourceLocation(base.MODID, "textures/sicons/lol.png");
	}
}
