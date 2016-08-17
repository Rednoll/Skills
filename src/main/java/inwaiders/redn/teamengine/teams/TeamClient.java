package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.base.Core;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.Constants;

public class TeamClient{

	private String name = "";
	public List<EntityPlayer> players = new ArrayList();
	public List<EntityPlayer> accesWait = new ArrayList();
	private String nickOwner = "";
	
	public TeamClient (String name){
		this.name = name;
	}
	
	public void update(EntityPlayer ep)
	{
		
	}
	
	public void joinToAcces(EntityPlayer ep){
		
		if(ep != null){
			for(int i = 0;i<accesWait.size();i++){
				if(ep.getCommandSenderName().equals(accesWait.get(i).getCommandSenderName()))
					return;
			}
		}
		
		if(ep != null){
			for(int i = 0;i<players.size();i++){
				if(ep.getCommandSenderName().equals(players.get(i).getCommandSenderName()))
					return;
			}
		}

		this.accesWait.add(ep);
	}
	
	public int getTeamSize(){
		return players.size();
	}
	
	public void joinToPlayer(EntityPlayer ep){
		
		if(ep != null){
			for(int i = 0;i<players.size();i++){
				if(ep.getCommandSenderName().equals(players.get(i).getCommandSenderName()))
					return;
			}
		}

		this.players.add(ep);
	}
	
	public int hasInAcces(String s){
		
		for(int i = 0;i<accesWait.size();i++){
			
			if(accesWait.get(i).getCommandSenderName().equals(s))
				return i;
		}
		
		return -1;
	}
	
	public void accesing(int i){
		
		this.joinToPlayer(this.accesWait.get(i));
		this.accesWait.remove(i);
	}
	
	public void leavePlayer(EntityPlayer ep){
		players.remove(ep);
		if(ep.getCommandSenderName().equals(nickOwner))
		{
			autoResetOwner();
		}
	}
	
	public void setOwner(EntityPlayer ep){
		if(ep == null)
			return;
		this.nickOwner = ep.getCommandSenderName();
	}
	
	public void setNickOwner(String nick){
		this.nickOwner = nick;
	}
	
	public String getOwnerName(){
		return this.nickOwner;
	}
	
	private void autoResetOwner(){
		
		if(this.players.size() > 0)
		{
			EntityPlayer p = this.players.get(Core.r.nextInt(this.players.size()));
			this.nickOwner = p.getCommandSenderName();
			p.addChatComponentMessage(new ChatComponentText("You are now owner of team " + name));
		}	
		else
			nickOwner = "";
	}
	
	public String getTeamName(){
		return name;
	}
}
