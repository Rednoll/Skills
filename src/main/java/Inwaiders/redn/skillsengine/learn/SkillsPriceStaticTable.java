package Inwaiders.redn.skillsengine.learn;

import java.util.ArrayList;
import java.util.List;

import Inwaiders.redn.skillsengine.bank.SkillsBankServerProvider;
import Inwaiders.redn.skillsengine.bank.SkillsStaticTable;

public class SkillsPriceStaticTable {

	static List<ComposerPrice> allSkills = new ArrayList<ComposerPrice>();
	
	public static void registerSkill(ComposerPrice base){
		allSkills.add(base);
	}
	
	private static ComposerPrice getSkill(int i){
		return allSkills.get(i);
	}
	
	public static int getSize(){
		return allSkills.size();
	}
	
	public static ComposerPrice getSkillCPById(int id) throws InstantiationException, IllegalAccessException{
		
		for(int i = 0;i<SkillsStaticTable.getSize();i++){
			
			ComposerPrice p = (ComposerPrice)SkillsPriceStaticTable.getSkill(i);
			
			if(p.getId() == id){
				return p;
			}
		}
		return null;
	}
	
	public static void init(){
		registerSkill(new ComposerPrice(1, 100, "You Make Back Jump !"));
		registerSkill(new ComposerPrice(2, 500, "You Release you Prana And Damage Another !"));
	}
	
	public static void learnSkill(SkillsBankServerProvider s, LearnPointsServerProvider p, int id){
		
		ComposerPrice c;
		
		try {
			c = getSkillCPById(id);
			
			if(p.canLearn(c.getPrice())){
				s.learnSkill(id);
				p.learn(c.getPrice());
			}
			
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
