package inwaiders.redn.rpg.items.itemskills;

import net.minecraft.item.ItemStack;
import inwaiders.redn.rpg.items.ItemBase;
import inwaiders.redn.rpg.registry.ItemSkillRegistry;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.utils.skillitem.ISkillItem;

public class ItemSkillItemBase extends ItemBase implements ISkillItem {
	protected final int lvl;
	protected final String skillname;
	public ItemSkillItemBase(String name, String itemskillName, int lvl) {
		super(name);
		skillname = itemskillName;
		this.lvl = lvl;
	}

	@Override
	public ItemSkillBase getSkill(ItemStack stack) {
		ItemSkillBase ret = ItemSkillRegistry.getByName(skillname);
		ret.setLevel(lvl);
		return ret;
	}
	
}
