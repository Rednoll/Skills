package inwaiders.redn.skillsengine.examples;

import inwaiders.redn.rpg.base.Core;
import inwaiders.redn.skillsengine.examples.EasySkillCreator.IEffect;
import inwaiders.redn.skillsengine.learn.LearnPointsPrice;
import inwaiders.redn.teamengine.targeting.Targeting.Target;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillEarthquake extends BaseSkill
{

	public SkillEarthquake()
	{
		setLevel(1);
		setTarget(Target.TARGET_ANOTHER);
		setMaxCoolDownByLevel(1, 20000);
		setMaxCoolDownByLevel(2, 30000);
		setMaxCoolDownByLevel(3, 40000);
		setMaxCoolDownByLevel(4, 70000);
		setDamageByLevel(1, 6);
		setDamageByLevel(2, 8);
		setDamageByLevel(3, 10);
		setDamageByLevel(4, 12);
		setRadiusByLevel(1, 5);
		setRadiusByLevel(2, 10);
		setRadiusByLevel(3, 12);
		setRadiusByLevel(4, 15);
	}
	
	@Override
	public void skillStart(EntityPlayer ep)
	{
		EasySkillCreator.applyAOEEffect(ep, getRadiusByLevel(getLevel()), target, (caster, target) ->{
			EasySkillCreator.attack(caster, target, getDamageByLevel(getLevel()));
			target.motionY += getDamageByLevel(getLevel()) / 8F;
		});
	}
	
	@Override
	public void setCoolDown(int i)
	{
		//super.setCoolDown(i);
	}
	
	@Override
	public LearnPointsPrice getPrice()
	{
		return new LearnPointsPrice(this, 500, "Jump and then deal damage to all around, launching them into air");
	}

	@Override
	public ResourceLocation getTexture()
	{
		return Core.skillrlgen.generate("earthquake");
	}

}
