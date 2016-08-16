package Inwaiders.redn.teamengine.teams;

import java.util.ArrayList;
import java.util.List;

import Inwaiders.redn.rpg.base.base;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class TeamMainClassClientProvider{

	String name = "";
	public List<EntityPlayer> players = new ArrayList();
	public List<EntityPlayer> accesWait = new ArrayList();
	String nickOwner = "";
	
	public TeamMainClassClientProvider (String name){
		this.name = name;
	}
	
	public void updateEngine(){

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
		
		for(int i = 0;i<players.size();i++){
			
			if(ep.getCommandSenderName() == players.get(i).getCommandSenderName())
				players.remove(i);
			
			if(ep.getCommandSenderName() == this.nickOwner)
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
			this.nickOwner = this.players.get(base.r.nextInt(this.players.size())).getCommandSenderName();
		else
			nickOwner = "";
	}
	
	public String getTeamName(){
		return name;
	}
}
