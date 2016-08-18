package inwaiders.redn.rpg.items;

import cpw.mods.fml.common.registry.GameRegistry;
import inwaiders.redn.rpg.core.Core;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	public ItemBase(String name) {
		 setUnlocalizedName(name);
		 setTextureName(Core.MODID + ":" + name);
		 setCreativeTab(Core.tab);
		 GameRegistry.registerItem(this, name);
	}
}
