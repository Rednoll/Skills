package Inwaiders.redn.teamengine.teams;

import Inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import Inwaiders.redn.skillsengine.bank.SyncStoCProviders;
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
		
		TeamEngineServerProvider te = GeterTStoP.getParam(ep.getEntityId());
		
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
			
			TeamEngineClientProvider te = GeterTCtoP.getParam(player.getEntityId());
				te.setTeam(message.nbt.getString("Team"));
			GeterTCtoP.setParamToEntity(player.getEntityId(), te);
			
			return null;
		}
	}

}
