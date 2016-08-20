package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.skills.item.CooldownDecrease;
import inwaiders.redn.rpg.skills.item.InstantShield;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;

public class ItemSkillRegistry {
	private static HashMap<String, Class<? extends ItemSkillBase>> skills = new HashMap<String, Class<? extends ItemSkillBase>>();

	public static void register(String name, Class<? extends ItemSkillBase> skill) {
		if (skills.containsKey(name)) {
			FMLLog.warning("ItemSpell with name " + name + " alredy registered, skipping");
			return;
		}
		skills.put(name, skill);
	}

	public static void register(ItemSkillBase skill) {
		register(skill.getName(), skill.getClass());
	}

	public static ItemSkillBase getByName(String name) {
		if (skills.containsKey(name)) {
			try {
				return skills.get(name).newInstance();
			} catch (Exception e) {
				MiscUtils.crashGame("Unable to get ItemSkill with name " + name + " from registry, contact mod author", e);
			}
		}
		return null;
	}
	
	public static void init()
	{
		register(new CooldownDecrease());
		register(new InstantShield());
	}
}
