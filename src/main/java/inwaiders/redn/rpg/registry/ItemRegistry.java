package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.items.DebugItem;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry
{
	public static Item debugItem;
	private static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
	
	public static void init()
	{
		register(debugItem = new DebugItem());
	}
}