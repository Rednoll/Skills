package Inwaiders.redn.teamengine.teams;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TeamMainClassServerProvider extends TeamMainClassClientProvider{

	private boolean loadet = false;
	private int iCount = 0;
	
	public TeamMainClassServerProvider(String name) {
		super(name);
		
	}

	public void updateEngine(EntityPlayer ep){
		
		sync();
		load(ep);
	}
	
	public void load(EntityPlayer ep){
		
		if(!loadet){
			SaveAndLoadTeamMain.load(ep, getTeamName(), this);
			loadet = true;
		}
	}
	
	public void sync(){
		
		if(iCount >= 40){
			send();
			iCount = 0;
		}
		
		iCount++;
	}
	
	public void send(){
		for(int i = 0;i<players.size();i++){
			PacketDispatcher.sendTo(new SyncTeamMainClass(getTeamName()), (EntityPlayerMP) players.get(i));
		}
	}
}
