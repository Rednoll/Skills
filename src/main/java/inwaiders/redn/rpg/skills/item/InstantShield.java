package inwaiders.redn.rpg.skills.item;

import javax.annotation.Nullable;

import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.skills.EasySkillCreator;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.ItemSkillType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class InstantShield extends ItemSkillBase{

	int[] damageDefendet;
	
	public InstantShield() {
		
		damageDefendet = new int[]{3, 5, 7, 10};
		type = ItemSkillType.HITWEARER;
	}
	
	@Override
	public void perform(LivingHurtEvent e) {
		EasySkillCreator.attack((EntityPlayer) e.entityLiving, e.entityLiving, (int) (e.ammount - damageDefendet[getLevel()]));
	}
	
	@Override
	public int getId() {
		
		return CFG.InstantShieldID;
	}

	@Override
	public String getName() {
		
		return "ItemSkillInstantShield";
	}

	@Override
	public int getInstallCost() {
		
		return 2 * getLevel();
	}

}
