package Inwaiders.redn.skillsengine.bank;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;


public class SkillsBankServerProvider extends SkillsBankClientProvider{

	int iCount = 0;
	boolean loadet = false;
	
	public SkillsBankServerProvider(EntityPlayer ep) {
		super(ep);

	}

	
	public void updateEngine(){
		
		super.updateEngine();
		synhronaizer();
		load();
	}
	
	public void synhronaizer(){
		
		if(iCount >= 30){
			PacketDispatcher.sendTo(new SyncStoCProviders(ep),  (EntityPlayerMP) ep);
			iCount = 0;
		}
		iCount++;
	}
	
	public void load(){
		
		if(!loadet){
			SaveBankAndHot.load(ep);
			loadet = true;
		}
		
	}
	
	
}
