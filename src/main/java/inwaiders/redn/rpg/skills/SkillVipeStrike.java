package inwaiders.redn.rpg.skills;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.entity.VipeEntity;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillVipeStrike extends BaseSkill{

	public SkillVipeStrike(){
		super(4);
	}
	
	public void init(){
		
		setMaxCoolDownByLevel(1, 200);
		setMaxCoolDownByLevel(2, 300);
		setMaxCoolDownByLevel(3, 500);
		setMaxCoolDownByLevel(4, 700);
		
		setDamageByLevel(1, 4);
		setDamageByLevel(2, 6);
		setDamageByLevel(3, 8);
		setDamageByLevel(4, 10);
	}
	
	@Override
	public void skillStart(EntityPlayer ep) {
		
		VipeEntity ve = new VipeEntity(ep.worldObj, ep, 2F, getDamageByLevel(getLevel()), getLevel());
		ep.worldObj.spawnEntityInWorld(ve);
	}
	
	@Override
	public int getId() {
		
		return CFG.VipeID;
	}

	@Override
	public String getName() {
		
		return "vs";
	}

	@Override
	public LearnPointsPrice getPrice() {
		
		return new LearnPointsPrice(this, 600);
	}

	@Override
	public ResourceLocation getTexture() {
		
		return Core.skillrlgen.generate("vipeStrike");
	}

}
