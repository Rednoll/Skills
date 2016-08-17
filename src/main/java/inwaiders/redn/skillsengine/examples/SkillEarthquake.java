package inwaiders.redn.skillsengine.examples;

import inwaiders.redn.rpg.base.CFG;
import inwaiders.redn.rpg.base.core.Core;
import inwaiders.redn.skillsengine.examples.EasySkillCreator.IEffect;
import inwaiders.redn.skillsengine.learn.LearnPointsPrice;
import inwaiders.redn.teamengine.targeting.Targeting.Target;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillEarthquake extends BaseSkill
{

	public SkillEarthquake(){
		
		setLevel(1);
		setTarget(Target.TARGET_ANOTHER);
		setMaxCoolDownByLevel(1, 2000);
		setMaxCoolDownByLevel(2, 3000);
		setMaxCoolDownByLevel(3, 4000);
		setMaxCoolDownByLevel(4, 7000);
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
			
			switch(Core.r.nextInt(4)){
				
				case 0 :
					target.motionX += getDamageByLevel(getLevel()) / 8F;
				break;
				
				case 1 :
					target.motionX -= getDamageByLevel(getLevel()) / 8F;
				break;
				
				case 2 :
					target.motionZ += getDamageByLevel(getLevel()) / 8F;
				break;
				
				case 3 :
					target.motionZ -= getDamageByLevel(getLevel()) / 8F;
				break;
			}
			
		});
	}
	
	@Override
	public LearnPointsPrice getPrice()
	{
		return new LearnPointsPrice(this, 500);
	}

	@Override
	public ResourceLocation getTexture()
	{
		return Core.skillrlgen.generate("earthquake");
	}

	@Override
	public int getId()
	{
		return CFG.EarthquakeID;
	}
	
	@Override
	public String getName()
	{
		return "eq";
	}

}
