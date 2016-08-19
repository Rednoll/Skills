package inwaiders.redn.rpg.packet;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class LightningPacket implements IMessage {

	private double x, y, z;
	
	public LightningPacket() {}
	
	public LightningPacket(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound nbt = ByteBufUtils.readTag(buf);
		x = nbt.getDouble("X");
		y = nbt.getDouble("Y");
		z = nbt.getDouble("Z");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setDouble("X", x);
		nbt.setDouble("Y", y);
		nbt.setDouble("Z", z);
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	public static class Handler extends AbstractClientMessageHandler<LightningPacket>
	{

		@Override
		public IMessage handleClientMessage(EntityPlayer player, LightningPacket message, MessageContext ctx) {
			player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, message.x, message.y, message.z));
			return null;
		}
		
	}

}
