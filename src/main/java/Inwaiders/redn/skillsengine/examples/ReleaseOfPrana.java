package inwaiders.redn.skillsengine.examples;

import inwaiders.redn.rpg.base.Core;
import inwaiders.redn.skillsengine.learn.LeanPointsPrice;
import inwaiders.redn.teamengine.targeting.Targeting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ReleaseOfPrana extends BaseSkill{

	public ReleaseOfPrana (){
		
		setLevel(1);

		setTarget(Targeting.TARGET_ANOTHER);
		
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
	public int getId(){
		return 2;
	}
	
	@Override
	public ResourceLocation getTexture(){
		return new ResourceLocation(Core.MODID, "textures/sicons/releaseOfPrana.png");
	}

	@Override
	public LeanPointsPrice getPrice()
	{
		return new LeanPointsPrice(this, 500, "You Release you Prana And Damage Another !");
	}
}
