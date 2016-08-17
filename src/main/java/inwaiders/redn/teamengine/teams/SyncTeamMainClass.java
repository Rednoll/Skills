package inwaiders.redn.teamengine.teams;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncTeamMainClass implements IMessage{

	NBTTagCompound nbt;
	
	public SyncTeamMainClass(){
		
	}
	
	public SyncTeamMainClass(String name){	
		nbt = new NBTTagCompound();
		
		TeamServer teg = TeamManagerServer.instance.get(name);
		
		nbt.setString("TeamName", teg.getTeamName());
		nbt.setString("OwnerName", teg.getOwnerName());
		
		for(int i = 0;i<teg.players.size();i++){
			nbt.setString("Players"+i, teg.players.get(i).getCommandSenderName());
		}
		
		nbt.setInteger("PlayersCount", teg.players.size());
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends AbstractClientMessageHandler<SyncTeamMainClass> {
		
		@Override
		public IMessage handleClientMessage(EntityPlayer player, SyncTeamMainClass message, MessageContext ctx) {
			
			TeamClient teg = new TeamClient(message.nbt.getString("TeamName"));
			
			teg.setNickOwner(message.nbt.getString("OwnerName"));
			
			for(int i = 0;i<message.nbt.getInteger("PlayersCount");i++){
				teg.joinToAcces(player.worldObj.getPlayerEntityByName(message.nbt.getString("Players"+i)));
			}
			
			TeamManagerClient.instance.set(message.nbt.getString("TeamName"), teg);
			
			return null;
		}
	}

}
