package inwaiders.redn.skillsengine.bank;

import inwaiders.redn.rpg.base.utils.MiscUtils;
import inwaiders.redn.skillsengine.examples.BaseSkill;
import inwaiders.redn.skillsengine.examples.ReleaseOfPrana;
import inwaiders.redn.skillsengine.examples.SkillBackJump;
import inwaiders.redn.skillsengine.examples.SkillEarthquake;
import inwaiders.redn.skillsengine.learn.SkillPriceRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cpw.mods.fml.common.FMLLog;

public class SkillsRegistry {

	private static HashMap<Integer, Class<? extends BaseSkill>> allSkills = new HashMap<Integer, Class<? extends BaseSkill>>();
	
	public static void registerSkill(int id, Class<? extends BaseSkill> base){
		allSkills.put(id, base);
	}
	public static void registerSkill(BaseSkill skill)
	{
		if(allSkills.containsKey(skill.getId()))
		{
			FMLLog.warning("Skill with id " + skill.getId() + " alredy registered, skipping");
			return;
		}
		registerSkill(skill.getId(), skill.getClass());
		SkillPriceRegistry.registerSkill(skill.getId(), skill.getPrice());
	}
	
	/*private static Class<?> getSkill(int i){
		return allSkills.get(i);
	}*/
	
	public static int getSize(){
		return allSkills.size();
	}
	
	public static BaseSkill getSkillById(int id){
		
		try
		{
			return allSkills.get(id).newInstance();
		}
		catch (Exception e)
		{
			MiscUtils.crashGame("Unable to get skill form registry, contact mod author", e);
		}
		return null;
	}
	
	public static void init(){
		
		registerSkill(new SkillBackJump());
		registerSkill(new ReleaseOfPrana());
		registerSkill(new SkillEarthquake());
	}
}