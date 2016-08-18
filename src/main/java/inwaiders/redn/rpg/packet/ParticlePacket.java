package inwaiders.redn.rpg.packet;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.util.Color;

import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ParticlePacket implements IMessage {

	public ParticlePacket() {

	}

	private NBTTagCompound nbt;

	public ParticlePacket(Class<?> particleClass, float r, float g, float b, double x, double y, double z, double motionX, double motionY, double motionZ) {
		nbt = new NBTTagCompound();
		nbt.setString("name", particleClass.getName());
		nbt.setFloat("R", r);
		nbt.setFloat("G", g);
		nbt.setFloat("B", b);
		nbt.setDouble("X", x);
		nbt.setDouble("Y", y);
		nbt.setDouble("Z", z);
		nbt.setDouble("MX", motionX);
		nbt.setDouble("MY", motionY);
		nbt.setDouble("MZ", motionZ);
		nbt.setBoolean("Custom", true);
	}

	public ParticlePacket(String particleName, double x, double y, double z, double motionX, double motionY, double motionZ) {
		nbt = new NBTTagCompound();
		nbt.setString("name", particleName);
		nbt.setDouble("X", x);
		nbt.setDouble("Y", y);
		nbt.setDouble("Z", z);
		nbt.setDouble("MX", motionX);
		nbt.setDouble("MY", motionY);
		nbt.setDouble("MZ", motionZ);
		nbt.setBoolean("Custom", false);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends AbstractClientMessageHandler<ParticlePacket> {

		@Override
		public IMessage handleClientMessage(EntityPlayer player, ParticlePacket message, MessageContext ctx) {
			NBTTagCompound nbt = message.nbt;
			if (nbt.getBoolean("Custom")) {
				EntityFX particle = null;
				try {
					particle = (EntityFX) Class.forName(nbt.getString("name")).getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class).newInstance(player.worldObj, nbt.getDouble("X"), nbt.getDouble("Y"), nbt.getDouble("Z"), nbt.getDouble("MX"), nbt.getDouble("MY"), nbt.getDouble("MZ"));
				} catch (Exception e) {
					FMLLog.warning("Unable to restore particle from packet");
				}
				if (particle != null) {
					particle.setRBGColorF(nbt.getFloat("R"), nbt.getFloat("G"), nbt.getFloat("B"));
					Minecraft.getMinecraft().effectRenderer.addEffect(particle);
				}
			}
			else
			{
				player.worldObj.spawnParticle(nbt.getString("name"), nbt.getDouble("X"), nbt.getDouble("Y"), nbt.getDouble("Z"), nbt.getDouble("MX"), nbt.getDouble("MY"), nbt.getDouble("MZ"));
			}
			return null;
		}

	}

}
