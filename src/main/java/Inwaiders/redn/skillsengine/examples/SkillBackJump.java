package inwaiders.redn.skillsengine.examples;

import inwaiders.redn.rpg.base.CFG;
import inwaiders.redn.rpg.base.core.Core;
import inwaiders.redn.skillsengine.bank.anno.isSkill;
import inwaiders.redn.skillsengine.learn.LearnPointsPrice;
import inwaiders.redn.teamengine.targeting.Targeting;
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
	public ResourceLocation getTexture(){
		return Core.skillrlgen.generate("backJump");
	}
	
	@Override
	public LearnPointsPrice getPrice()
	{
		return new LearnPointsPrice(this, 100);
	}

	@Override
	public int getId()
	{
		return CFG.BackJumpID;
	}

	@Override
	public String getName()
	{
		return "bj";
	}
}
