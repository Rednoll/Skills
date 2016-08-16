package Inwaiders.redn.skillsengine.bank;

import java.util.ArrayList;
import java.util.List;

import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.examples.ReleaseOfPrana;
import Inwaiders.redn.skillsengine.examples.SkillBackJump;

public class SkillsStaticTable {

	static List<Class<?>> allSkills = new ArrayList<Class<?>>();
	
	public static void registerSkill(Class<?> base){
		allSkills.add(base);
	}
	
	private static Class<?> getSkill(int i){
		return allSkills.get(i);
	}
	
	public static int getSize(){
		return allSkills.size();
	}
	
	public static BaseSkill getSkillById(int id) throws InstantiationException, IllegalAccessException{
		
		for(int i = 0;i<SkillsStaticTable.getSize();i++){
			
			BaseSkill s = (BaseSkill)SkillsStaticTable.getSkill(i).newInstance();
			
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
	
	public static void init(){
		
		registerSkill(SkillBackJump.class);
		registerSkill(ReleaseOfPrana.class);
	}
}
