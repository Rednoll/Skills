package Inwaiders.redn.teamengine.teams;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TeamEngineServerProvider extends TeamEngineClientProvider{
	
	public boolean loadet = false;
	int iCount = 0;
	
	public TeamEngineServerProvider(String team) {
		super(team);

	}
	
	public void updateEngine(EntityPlayer ep){
		super.updateEngine(ep);
		load(ep);
		sync();
	}
	
	public void load(EntityPlayer ep){
		
		if(!loadet){
			SaveAndLoadTeam.load(ep);
			loadet = true;
		}
	}
	
	public void send(){
		PacketDispatcher.sendTo(new SyncTeamPrivatePacket(ep), (EntityPlayerMP) ep);
	}
	
	public void sync(){
		
		if(iCount >= 40){
			send();
			iCount = 0;
		}
		
		iCount++;
	}
	
}
