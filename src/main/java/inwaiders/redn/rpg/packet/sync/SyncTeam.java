package inwaiders.redn.rpg.packet.sync;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import inwaiders.redn.rpg.managers.client.TeamManagerClient;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import inwaiders.redn.rpg.storage.client.TeamClient;
import inwaiders.redn.rpg.storage.server.TeamServer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncTeam implements IMessage{

	NBTTagCompound nbt;
	private int i = 0;
	public SyncTeam(){
		
	}
	
	public SyncTeam(String name){	
		nbt = new NBTTagCompound();
		
		TeamServer teg = TeamManagerServer.instance.get(name);
		
		nbt.setString("TeamName", teg.getTeamName());
		nbt.setString("OwnerName", teg.getOwnerName());
		teg.players.forEach((pname, entity) -> nbt.setString("Players"+i++, pname));
		
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

	public static class Handler extends AbstractClientMessageHandler<SyncTeam> {
		
		@Override
		public IMessage handleClientMessage(EntityPlayer player, SyncTeam message, MessageContext ctx) {
			
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
