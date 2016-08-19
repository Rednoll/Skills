package inwaiders.redn.rpg.utils.skillitem;

import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import net.minecraft.item.ItemStack;

public interface ISkillItem {
	public ItemSkillBase getSkill(ItemStack stack);
}
