package inwaiders.redn.rpg.storage.server;

import inwaiders.redn.rpg.files.SaveAndLoadTeam;
import inwaiders.redn.rpg.packet.sync.SyncTeam;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.client.TeamClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TeamServer extends TeamClient{

	private boolean loaded = false;
	private int iCount = 0;
	
	public TeamServer(String name) {
		super(name);	
	}

	@Override
	public void update(EntityPlayer ep){
		super.update(ep);
		sync();
		load(ep);
	}
	
	public void load(EntityPlayer ep){
		
		if(!loaded){
			SaveAndLoadTeam.load(ep, getTeamName(), this);
			loaded = true;
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
			PacketDispatcher.sendTo(new SyncTeam(getTeamName()), (EntityPlayerMP) players.get(i));
		}
	}
}
