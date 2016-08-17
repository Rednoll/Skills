package inwaiders.redn.skillsengine.bank;

import inwaiders.redn.skillsengine.examples.BaseSkill;
import inwaiders.redn.skillsengine.examples.ReleaseOfPrana;
import inwaiders.redn.skillsengine.examples.SkillBackJump;
import inwaiders.redn.skillsengine.learn.SkillPriceRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillsRegistry {

	private static HashMap<Integer, Class<? extends BaseSkill>> allSkills = new HashMap<Integer, Class<? extends BaseSkill>>();
	
	public static void registerSkill(int id, Class<? extends BaseSkill> base){
		allSkills.put(id, base);
	}
	private static int id = 1;
	public static void registerSkill(BaseSkill skill)
	{
		registerSkill(id++, skill.getClass());
		SkillPriceRegistry.registerSkill(id, skill.getPrice());
	}
	
	/*private static Class<?> getSkill(int i){
		return allSkills.get(i);
	}*/
	
	public static int getSize(){
		return allSkills.size();
	}
	
	public static BaseSkill getSkillById(int id) throws InstantiationException, IllegalAccessException{
		
		return allSkills.get(Integer.valueOf(id)).newInstance();
	}
	
	public static void init(){
		
		registerSkill(new SkillBackJump());
		registerSkill(new ReleaseOfPrana());
	}
}
