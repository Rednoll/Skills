package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.skillsengine.examples.BaseSkill;

public class LeanPointsPrice {
	
	int id = 0;
	int price = 0;
	String description = "";
	
	public LeanPointsPrice(BaseSkill skill, int price, String description){
		
		this.id = skill.getId();
		this.price = price;
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getId(){
		return id;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
}
