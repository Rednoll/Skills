package inwaiders.redn.rpg.skills.damage;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.entity.VipeEntity;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillVipeStrike extends BaseSkill {

	public SkillVipeStrike() {
		super(5);
		init();
	}

	public void init() {

		setMaxCoolDownByLevel(0, 200);
		setMaxCoolDownByLevel(1, 300);
		setMaxCoolDownByLevel(2, 500);
		setMaxCoolDownByLevel(3, 700);
		setMaxCoolDownByLevel(4, 900);
		setMaxCoolDownByLevel(5, 1100);
		
		setDamageByLevel(0, 4);
		setDamageByLevel(1, 6);
		setDamageByLevel(2, 8);
		setDamageByLevel(3, 10);
		setDamageByLevel(4, 12);
		setDamageByLevel(5, 14);
	}

	@Override
	public void skillStart(EntityPlayer ep) {

		VipeEntity ve = new VipeEntity(ep.worldObj, ep, 2F, getDamage(ep), getLevel());
		ep.worldObj.spawnEntityInWorld(ve);
	}

	@Override
	public String getName() {

		return "vs";
	}

	@Override
	public LearnPointsPrice getPrice() {

		return new LearnPointsPrice(this, 3);
	}

	@Override
	public ResourceLocation getTexture() {

		return Core.skillrlgen.generate("vipeStrike");
	}

}
