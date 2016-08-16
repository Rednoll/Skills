package Inwaiders.redn.skillsengine.examples;

import Inwaiders.redn.rpg.base.base;
import Inwaiders.redn.skillsengine.bank.anno.isSkill;
import Inwaiders.redn.teamengine.targeting.Targeting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillBackJump extends BaseSkill{

	public SkillBackJump (){
		
		setLevel(1);
		
		init();
	}
	
	@Override
	public void skillStart(EntityPlayer ep){
		
		float cosX = (float)Math.cos(Math.toRadians(ep.rotationYaw)); 
		float sinX = (float)Math.sin(Math.toRadians(ep.rotationYaw)); 

		float cosY = (float)Math.cos(Math.toRadians(ep.rotationPitch)); 
		float sinY = (float)Math.sin(Math.toRadians(ep.rotationPitch)); 

		float speed = getLevel();
		float wertSpeed = getLevel()/1.5F;
		
		ep.motionZ -= cosX * speed;
		ep.motionX += sinX * speed;
		ep.motionY += wertSpeed/2F;
		
	}
	
	public void init(){
		
		setMaxCast(1, 0);
		setMaxCast(2, 0);
		setMaxCast(3, 0);
		setMaxCast(4, 0);
		
		setMaxCoolDownByLevel(1, 1800);
		setMaxCoolDownByLevel(2, 2700);
	}
	
	@Override
	public void whileUpdate(EntityPlayer ep){
		
	}
	
	@Override
	public int getId(){
		return 1;
	}
	
	@Override
	public ResourceLocation getTexture(){
		return new ResourceLocation(base.MODID, "textures/sicons/backJump.png");
	}
}
