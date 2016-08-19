package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.items.DebugItem;
import inwaiders.redn.rpg.items.ExperienceScroll;
import inwaiders.redn.rpg.items.CAD.CadMemoryCard;
import inwaiders.redn.rpg.items.CAD.CastingAssistantDevice;
import inwaiders.redn.rpg.items.armor.SkillArmor;
import inwaiders.redn.rpg.items.itemskills.CooldowDecreaser;
import net.minecraft.item.Item;

public class ItemRegistry
{
	public static Item debugItem;
	public static Item xpScroll;
	public static Item skillHelm;
	public static Item skillChestplater;
	public static Item skillLeggins;
	public static Item skillBoots;
	public static Item cdd;
	public static Item cadMemory;
	public static Item cad;
	public static void init()
	{
		debugItem = new DebugItem();
		xpScroll = new ExperienceScroll();
		skillHelm = new SkillArmor(0);
		skillChestplater = new SkillArmor(1);
		skillLeggins = new SkillArmor(2);
		skillBoots = new SkillArmor(3);
		cdd = new CooldowDecreaser();
		cadMemory = new CadMemoryCard();
		cad = new CastingAssistantDevice();
		
	}
}
