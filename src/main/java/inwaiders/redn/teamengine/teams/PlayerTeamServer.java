package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerTeamServer extends PlayerTeamClient{
	
	public boolean loadet = false;
	int iCount = 0;
	
	public PlayerTeamServer(String team) {
		super(team);
	}
	
	@Override
	public void update(EntityPlayer ep){
		super.update(ep);
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
