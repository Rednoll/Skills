package inwaiders.redn.rpg.items.itemskills;

import net.minecraft.item.ItemStack;
import inwaiders.redn.rpg.items.ItemBase;
import inwaiders.redn.rpg.registry.ItemSkillRegistry;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.utils.skillitem.ISkillItem;

public class ItemSkillItemBase extends ItemBase implements ISkillItem {
	protected final int id, lvl;
	public ItemSkillItemBase(String name, int itemskillid, int lvl) {
		super(name);
		id = itemskillid;
		this.lvl = lvl;
	}

	@Override
	public ItemSkillBase getSkill(ItemStack stack) {
		ItemSkillBase ret = ItemSkillRegistry.getById(id);
		ret.setLevel(lvl);
		return ret;
	}
	
}
