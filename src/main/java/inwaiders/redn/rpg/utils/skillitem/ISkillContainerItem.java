package inwaiders.redn.rpg.utils.skillitem;

import net.minecraft.item.ItemStack;
import inwaiders.redn.rpg.registry.ItemSkillRegistry;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.utils.ItemNBT;

public interface ISkillContainerItem {
	public default void setSkill(ItemStack stack, ItemSkillBase skill)
	{
		ItemNBT.setString(stack, "SKILL", skill.getName());
	}
	public default ItemSkillBase getSkill(ItemStack stack)
	{
		return ItemSkillRegistry.getByName(ItemNBT.getString(stack, "SKILL", "NONE"));
	}
}
