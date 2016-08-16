package Inwaiders.redn.skillsengine.learn;

import Inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import Inwaiders.redn.teamengine.teams.GeterTCtoP;
import Inwaiders.redn.teamengine.teams.GeterTStoP;
import Inwaiders.redn.teamengine.teams.SyncTeamPrivatePacket;
import Inwaiders.redn.teamengine.teams.TeamEngineClientProvider;
import Inwaiders.redn.teamengine.teams.TeamEngineServerProvider;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncLearnPoints implements IMessage{

	NBTTagCompound nbt;
	
	public SyncLearnPoints(){
		
	}
	
	public SyncLearnPoints(EntityPlayer ep){	
		nbt = new NBTTagCompound();
		
		LearnPointsServerProvider lp = GeterLpStoP.getParam(ep);
		
		nbt.setInteger("LearnPoints", lp.getLearnPoints());
	} 
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends AbstractClientMessageHandler<SyncLearnPoints> {
		
		@Override
		public IMessage handleClientMessage(EntityPlayer player, SyncLearnPoints message, MessageContext ctx) {
			
			LearnPointsClientProvider lp = GeterLpCtoP.getParam(player);
			lp.setLearnPoints(message.nbt.getInteger("LearnPoints"));
			return null;
		}
	}

}
