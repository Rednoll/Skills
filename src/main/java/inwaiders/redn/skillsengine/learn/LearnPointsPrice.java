package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.rpg.base.core.Core;
import inwaiders.redn.skillsengine.examples.BaseSkill;

public class LearnPointsPrice {
	
	int price = 0;
	String description = "";
	
	public LearnPointsPrice(BaseSkill skill, int price){
		
		this.price = price;
		this.description = Core.skilldescgen.generate(skill.getName());
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
}
