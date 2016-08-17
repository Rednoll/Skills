package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import inwaiders.redn.skillsengine.bank.SyncStoCProviders;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncTeamPrivatePacket implements IMessage{

	NBTTagCompound nbt;
	
	public SyncTeamPrivatePacket(){
		
	}
	
	public SyncTeamPrivatePacket(EntityPlayer ep){	
		nbt = new NBTTagCompound();
		
		PlayerTeamServer te = PlayerTeamManagerServer.instance.get(ep);
		
		nbt.setString("Team", te.getTeam());
	} 
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends AbstractClientMessageHandler<SyncTeamPrivatePacket> {
		
		@Override
		public IMessage handleClientMessage(EntityPlayer player, SyncTeamPrivatePacket message, MessageContext ctx) {
			
			PlayerTeamClient te = PlayerTeamManagerClient.instance.get(player);
			te.setTeam(message.nbt.getString("Team"));
			PlayerTeamManagerClient.instance.set(player, te);
			
			return null;
		}
	}

}
