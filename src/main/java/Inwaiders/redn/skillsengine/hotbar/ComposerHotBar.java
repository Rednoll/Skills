package Inwaiders.redn.skillsengine.hotbar;

public class ComposerHotBar {

	private int id = 0;
	
	private SkillsHotBar bank;
		
	public ComposerHotBar(int id, SkillsHotBar bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerHotBar setId(int i){
			this.id = i;
			return this;
	}
		
	public ComposerHotBar setParam(SkillsHotBar i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public SkillsHotBar getParam(){
		return this.bank;
	}
	
}
