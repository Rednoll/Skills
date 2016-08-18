package inwaiders.redn.rpg.skills;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.MiscUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;

public class SkillSwap extends BaseSkill {

	public SkillSwap(){
		setRadiusByLevel(1, 10);
		setRadiusByLevel(2, 15);
		setRadiusByLevel(3, 20);
		setRadiusByLevel(4, 25);
		setRadiusByLevel(4, 30);
	}
	
	@Override
	public void skillStart(EntityPlayer ep) {

		MovingObjectPosition object = MiscUtils.getPlayerTarget(ep, getRadiusByLevel(getLevel()), 0);

		System.out.println(object);
		
		if (object == null || object.typeOfHit != MovingObjectType.ENTITY) {
			return;
		}
		
		if (object.entityHit instanceof EntityLivingBase) {

			EntityLivingBase e = (EntityLivingBase) object.entityHit;

			double x_p = ep.posX;
			double y_p = ep.posY;
			double z_p = ep.posZ;

			double x_t = e.posX;
			double y_t = e.posY;
			double z_t = e.posZ;

			ep.setPositionAndUpdate(x_t, y_t, z_t);
			e.setPositionAndUpdate(x_p, y_p, z_p);
		}

	}

	@Override
	public int getId() {

		return CFG.SwapID;
	}

	@Override
	public String getName() {

		return "sw";
	}

	@Override
	public LearnPointsPrice getPrice() {

		return new LearnPointsPrice(this, 3);
	}

	@Override
	public ResourceLocation getTexture() {

		return Core.skillrlgen.generate("swap");
	}

}
