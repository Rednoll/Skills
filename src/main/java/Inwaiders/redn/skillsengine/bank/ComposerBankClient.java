package Inwaiders.redn.skillsengine.bank;

public class ComposerBankClient {

	private int id = 0;
	
	private SkillsBankClientProvider bank;
		
	public ComposerBankClient(int id, SkillsBankClientProvider bank){
			
		this.id = id;
		this.bank = bank;
	}
		
	public ComposerBankClient setId(int i){
			this.id = i;
			return this;
	}
		
	public ComposerBankClient setParam(SkillsBankClientProvider i){
		this.bank = i;
		return this;
	}
		
	public int getId(){
		return this.id;
	}
		
	public SkillsBankClientProvider getParam(){
		return this.bank;
	}
	
}
