package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SyncLearnPoints implements IMessage{

	NBTTagCompound nbt;
	
	public SyncLearnPoints(){
		
	}
	
	public SyncLearnPoints(EntityPlayer ep){	
		nbt = new NBTTagCompound();
		
		LearnPointsServer lp = PlayerLearnPointManagerServer.instance.get(ep);
		
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
			
			LearnPointsClient lp = PlayerLearnPointManagerClient.instance.get(player);
			lp.setLearnPoints(message.nbt.getInteger("LearnPoints"));
			return null;
		}
	}

}
