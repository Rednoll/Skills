package inwaiders.redn.rpg.utils.skillitem;

import net.minecraft.item.ItemStack;
import inwaiders.redn.rpg.registry.ItemSkillRegistry;
import inwaiders.redn.rpg.skills.armor.ItemSkillBase;
import inwaiders.redn.rpg.utils.ItemNBT;

public interface ISkillContainerItem {
	public default void setSkill(ItemStack stack, ItemSkillBase skill)
	{
		ItemNBT.setInt(stack, "SKILL", skill.getId());
	}
	public default ItemSkillBase getSkill(ItemStack stack)
	{
		return ItemSkillRegistry.getById(ItemNBT.getInt(stack, "SKILL", -1));
	}
}
