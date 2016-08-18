package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.items.DebugItem;
import inwaiders.redn.rpg.items.ExperienceScroll;
import net.minecraft.item.Item;

public class ItemRegistry
{
	public static Item debugItem;
	public static Item xpScroll;
	public static void init()
	{
		debugItem = new DebugItem();
		xpScroll = new ExperienceScroll();
	}
}
