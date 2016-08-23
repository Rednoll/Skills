package inwaiders.redn.rpg.storage.client;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.json.TeamJson;
import inwaiders.redn.rpg.managers.client.TeamManagerClient;
import inwaiders.redn.rpg.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.Constants;

public class TeamClient {

	protected String name = "";
	public final HashMap<String, EntityPlayer> players = new HashMap<String, EntityPlayer>();
	public final HashMap<String, EntityPlayer> accesWait = new HashMap<String, EntityPlayer>();
	protected String nickOwner = "";

	public TeamClient(String name) {
		this.name = name;
	}

	public void update(EntityPlayer ep) {
		
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
