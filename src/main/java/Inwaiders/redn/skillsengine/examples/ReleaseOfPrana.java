package inwaiders.redn.skillsengine.examples;

import inwaiders.redn.rpg.base.CFG;
import inwaiders.redn.rpg.base.core.Core;
import inwaiders.redn.skillsengine.learn.LearnPointsPrice;
import inwaiders.redn.teamengine.targeting.Targeting.Target;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ReleaseOfPrana extends BaseSkill{

	public ReleaseOfPrana (){
		
		setLevel(1);

		setTarget(Target.TARGET_ANOTHER);
		
		init();
	}
	
	public void init(){
		
		setMaxCast(1, 50);
		setMaxCast(2, 70);
		setMaxCast(3, 110);
		setMaxCast(4, 150);
		
		setDamageByLevel(1, 6);
		setDamageByLevel(2, 8);
		setDamageByLevel(3, 10);
		setDamageByLevel(4, 14);
		
		setRadiusByLevel(1, 8);
		setRadiusByLevel(2, 12);
		setRadiusByLevel(3, 16);
		setRadiusByLevel(4, 18);
		
		setMaxCoolDownByLevel(1, 1800);
		setMaxCoolDownByLevel(2, 2700);
	}
	
	@Override
	public void skillStart(EntityPlayer ep){
		
		EasySkillCreator.attackAOE(ep, getRadiusByLevel(getLevel()), getDamageByLevel(getLevel()), getTarget());
		
	}
	
	@Override
	public void whileUpdate(EntityPlayer ep){
		
	}
	
	@Override
	public ResourceLocation getTexture(){
		return Core.skillrlgen.generate("releaseOfPrana");
	}

	@Override
	public LearnPointsPrice getPrice()
	{
		return new LearnPointsPrice(this, 500);
	}

	@Override
	public int getId()
	{
		return CFG.ReleaseOfPranaID;
	}

	@Override
	public String getName()
	{
		return "rop";
	}
}
