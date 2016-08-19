package inwaiders.redn.rpg.skills.item;

import javax.annotation.Nullable;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.handlers.EventCancelException;
import inwaiders.redn.rpg.skills.EasySkillCreator;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.ItemSkillType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import scala.math.Ordering.CharOrdering;

public class InstantShield extends ItemSkillBase {

	int[] damageDefendet;

	public InstantShield() {
		super(5);
		damage = new int[] { 1, 3, 5, 7, 10 };
		type = ItemSkillType.HITWEARER;
	}

	@Override
	public void perform(LivingHurtEvent e) {
		if(Core.r.nextBoolean())
			e.ammount = Math.max(0, e.ammount - getDamage());
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
