package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.skills.item.CooldownDecrease;
import inwaiders.redn.rpg.skills.item.InstantShield;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;

public class ItemSkillRegistry {
	private static HashMap<Integer, Class<? extends ItemSkillBase>> skills = new HashMap<Integer, Class<? extends ItemSkillBase>>();

	public static void register(int id, Class<? extends ItemSkillBase> skill) {
		if (skills.containsKey(id)) {
			FMLLog.warning("ItemSpell with id " + id + " alredy registered, skipping");
			return;
		}
		skills.put(id, skill);
	}

	public static void register(ItemSkillBase skill) {
		register(skill.getId(), skill.getClass());
	}

	public static ItemSkillBase getById(int id) {
		if (skills.containsKey(id)) {
			try {
				return skills.get(id).newInstance();
			} catch (Exception e) {
				MiscUtils.crashGame("Unable to get ItemSkill with id " + id + " from registry, contact mod author", e);
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
