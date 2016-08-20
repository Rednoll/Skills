package inwaiders.redn.rpg.registry;

import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.skills.damage.LightningBoltStrike;
import inwaiders.redn.rpg.skills.damage.ReleaseOfPrana;
import inwaiders.redn.rpg.skills.damage.SkillEarthquake;
import inwaiders.redn.rpg.skills.damage.SkillVipeStrike;
import inwaiders.redn.rpg.skills.utility.SkillBackJump;
import inwaiders.redn.rpg.skills.utility.SkillSwap;
import inwaiders.redn.rpg.skills.utility.SkillVortex;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;

public class SkillsRegistry {

	private static HashMap<String, Class<? extends BaseSkill>> allSkills = new HashMap<String, Class<? extends BaseSkill>>();

	public static void registerSkill(String id, Class<? extends BaseSkill> base) {
		allSkills.put(id, base);
	}

	public static void registerSkill(BaseSkill skill) {
		if (allSkills.containsKey(skill.getName())) {
			FMLLog.warning("Skill with name " + skill.getName() + " alredy registered, skipping");
			return;
		}
		registerSkill(skill.getName(), skill.getClass());
	}
	
	public static String getRandomSkillName()
	{
		String[] names = (String[]) allSkills.keySet().toArray(new String[allSkills.keySet().size()]);
		return names[Core.r.nextInt(names.length)];
	}

	/*
	 * private static Class<?> getSkill(int i){ return allSkills.get(i); }
	 */

	public static int getSize() {
		return allSkills.size();
	}

	public static BaseSkill getSkillByName(String name) {
		if (allSkills.containsKey(name)) {
			try {
				return allSkills.get(name).newInstance();
			} catch (Exception e) {
				MiscUtils.crashGame("Unable to get skill form registry, contact mod author", e);
			}
		}
		return null;
	}

	public static void init() {

		registerSkill(new SkillBackJump());
		registerSkill(new ReleaseOfPrana());
		registerSkill(new SkillEarthquake());
		registerSkill(new LightningBoltStrike());
		registerSkill(new SkillVortex());
		registerSkill(new SkillSwap());
		registerSkill(new SkillVipeStrike());
	}

	public static boolean learnSkill(PlayerInfoServer p, String name) {
		BaseSkill s = p.getSkillByName(name);
		if(s == null)
		{
			s = getSkillByName(name);
		}
		if (p.canLearn(s) == 0) {
			p.learnSkill(name);
			p.learn(s);
			return true;
		}
		return false;

	}

	public static boolean learnSkill(EntityPlayer p, String name) {
		return learnSkill(PlayerInfoManagerServer.instance.get(p), name);
	}
}
