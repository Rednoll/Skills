package inwaiders.redn.rpg.packet;

import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.packetdispatcher.AbstractServerMessageHandler;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SetHotbarPacket implements IMessage {

	private String name;
	private int slotnum;
	public SetHotbarPacket() {}
	
	public SetHotbarPacket(String name, int slotnum) {
		this.name = name;
		this.slotnum = slotnum;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound nbt = ByteBufUtils.readTag(buf);
		name = nbt.getString("name");
		slotnum = nbt.getInteger("slot");
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("name", name);
		nbt.setInteger("slot", slotnum);
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	public static class Handler extends AbstractServerMessageHandler<SetHotbarPacket>
	{

		@Override
		public IMessage handleServerMessage(EntityPlayer player, SetHotbarPacket message, MessageContext ctx)
		{
			PlayerInfoServer i = PlayerInfoManagerServer.instance.get(player);
			i.hotbar[message.slotnum] = message.name;
			return null;
		}
		
	}

}
