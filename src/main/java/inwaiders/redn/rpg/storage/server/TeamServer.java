package inwaiders.redn.rpg.storage.server;

import java.util.HashMap;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.SaveAndLoadTeam;
import inwaiders.redn.rpg.files.json.TeamJson;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.packet.sync.SyncTeam;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.client.TeamClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class TeamServer {

	private boolean loaded = false;
	private int iCount = 0;
	protected String name = "";
	public final HashMap<String, EntityPlayer> players = new HashMap<String, EntityPlayer>();
	public final HashMap<String, EntityPlayer> accesWait = new HashMap<String, EntityPlayer>();
	protected String nickOwner = "";
	
	public TeamServer(String name) {
	}


	public void update(EntityPlayer ep){
		if(players.size() == 0)
		{
			if(accesWait.size() == 0)
			{
				TeamManagerServer.instance.set(name, null);
				new TeamJson(name, MinecraftServer.getServer().worldServerForDimension(0).getSaveHandler().getWorldDirectory()).delete();
			}
			else
			{
				approve(accesWait.values().iterator().next());
			}
		}
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
	
	public void joinToAcces(EntityPlayer ep) {

		if (!players.containsValue(ep) && !accesWait.containsValue(ep)) {
			this.accesWait.put(ep.getCommandSenderName(), ep);
		}
	}

	public int getTeamSize() {
		return players.size();
	}

	public void joinToPlayer(EntityPlayer ep) {

		if (ep != null && players.containsValue(ep)) {
			this.players.put(ep.getCommandSenderName(), ep);
		}
	}

	public void approve(EntityPlayer ep) {

		this.joinToPlayer(ep);
		this.accesWait.remove(ep.getCommandSenderName());
	}

	public void leavePlayer(EntityPlayer ep) {
		players.remove(ep);
		if (ep.getCommandSenderName().equals(nickOwner)) {
			autoResetOwner();
		}
	}

	public void setOwner(EntityPlayer ep) {
		if (ep == null)
			return;
		this.nickOwner = ep.getCommandSenderName();
	}

	public void setNickOwner(String nick) {
		this.nickOwner = nick;
	}

	public String getOwnerName() {
		return this.nickOwner;
	}

	private void autoResetOwner() {

		if (this.players.size() > 0) {
			EntityPlayer p = this.players.get(Core.r.nextInt(this.players.size()));
			this.nickOwner = p.getCommandSenderName();
			p.addChatComponentMessage(new ChatComponentText("You are now owner of team " + name));
		} else
			nickOwner = "";
	}

	public String getTeamName() {
		return name;
	}
}
