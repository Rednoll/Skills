package Inwaiders.redn.skillsengine.bank;

public class ComposerBankServer {

	private int id = 0;
	
	private SkillsBankServerProvider bank;
		
	public ComposerBankServer(int id, SkillsBankServerProvider bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerBankServer setId(int i){
			this.id = i;
			return this;
	}
		
	public ComposerBankServer setParam(SkillsBankServerProvider i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public SkillsBankServerProvider getParam(){
		return this.bank;
	}
	
}
