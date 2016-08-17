package inwaiders.redn.skillsengine.examples;

import inwaiders.redn.rpg.base.CFG;
import inwaiders.redn.rpg.base.core.Core;
import inwaiders.redn.rpg.base.utils.MiscUtils;
import inwaiders.redn.skillsengine.learn.LearnPointsPrice;
import inwaiders.redn.teamengine.targeting.Targeting.Target;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class LightningBoltStrike extends BaseSkill{

	
	public LightningBoltStrike(){
		
		setLevel(1);
		setTarget(Target.TARGET_ANOTHER);
		setMaxCoolDownByLevel(1, 2000);
		setMaxCoolDownByLevel(2, 3000);
		setMaxCoolDownByLevel(3, 4000);
		setMaxCoolDownByLevel(4, 7000);
		setDamageByLevel(1, 8);
		setDamageByLevel(2, 10);
		setDamageByLevel(3, 12);
		setDamageByLevel(4, 14);	
		setRadiusByLevel(1, 30);
		setRadiusByLevel(2, 60);
		setRadiusByLevel(3, 90);
		setRadiusByLevel(4, 120);
	}
	
	
	@Override
	public void skillStart(EntityPlayer ep){
		
		MovingObjectPosition object = MiscUtils.getPlayerTarget(ep, getRadiusByLevel(getLevel()), 0);
		
		if(object == null) return;
		
		EntityLightningBolt lightning = new EntityLightningBolt(ep.worldObj, object.blockX, object.blockY+1, object.blockZ);
		ep.worldObj.spawnEntityInWorld(lightning);
		
		//System.out.println("Coords : X = "+MiscUtils.getPlayerTarget(ep, 30, 0).blockX+", Y = "+MiscUtils.getPlayerTarget(ep, 30, 0).blockY+", Z ="+MiscUtils.getPlayerTarget(ep, 30, 0).blockZ);
	}
	
	@Override
	public int getId() {
		return CFG.LightBoltStrikeID;
	}

	@Override
	public String getName() {
		
		return "LightBoltStrike";
	}

	@Override
	public LearnPointsPrice getPrice() {
		
		return new LearnPointsPrice(this, 500);
	}

	@Override
	public ResourceLocation getTexture() {
		
		return Core.skillrlgen.generate("lightBoltStrike");
	}

}
