package inwaiders.redn.rpg.skills.item;

import javax.annotation.Nullable;

import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.handlers.EventCancelException;
import inwaiders.redn.rpg.skills.EasySkillCreator;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.ItemSkillType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class InstantShield extends ItemSkillBase {

	private float[] damageDefendet;

	public InstantShield() {
		super(4);
		damageDefendet = new float[] {1F, 3F, 5F, 7F};
		type = ItemSkillType.HITWEARER;
	}

	@Override
	public void perform(LivingHurtEvent e) {
		e.ammount = Math.max(0, e.ammount - damageDefendet[getLevel()]);
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
