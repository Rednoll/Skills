package inwaiders.redn.rpg.packetdispatcher;


import inwaiders.redn.rpg.packet.LearnSkillPackect;
import inwaiders.redn.rpg.packet.LightningPacket;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packet.SetHotbarPacket;
import inwaiders.redn.rpg.packet.SkillsStartPacket;
import inwaiders.redn.rpg.packet.sync.PlayerInfoSync;
import inwaiders.redn.rpg.packet.sync.SyncTeam;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketDispatcher
{
	private static byte packetId = Byte.MIN_VALUE;
	
	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("rpg");
	
	public static final void registerPackets() {
		registerMessage(PlayerInfoSync.Handler.class, PlayerInfoSync.class, Side.CLIENT);
		registerMessage(SyncTeam.Handler.class, SyncTeam.class, Side.CLIENT);
		registerMessage(SkillsStartPacket.Handler.class, SkillsStartPacket.class, Side.SERVER);
		registerMessage(LearnSkillPackect.Handler.class, LearnSkillPackect.class, Side.SERVER);
		registerMessage(ParticlePacket.Handler.class, ParticlePacket.class, Side.CLIENT);
		registerMessage(LightningPacket.Handler.class, LightningPacket.class, Side.CLIENT);
		registerMessage(SetHotbarPacket.Handler.class, SetHotbarPacket.class, Side.SERVER);
	}

	private static final void registerMessage(Class handlerClass, Class<? extends IMessage> messageClass, Side side) {
		PacketDispatcher.dispatcher.registerMessage(handlerClass, messageClass, packetId++, side);
	}
	
	private static final void registerMessage(Class handlerClass, Class messageClass) {
		Side side = AbstractClientMessageHandler.class.isAssignableFrom(handlerClass) ? Side.CLIENT : Side.SERVER;
		registerMessage(handlerClass, messageClass, side);
	}

	public static final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketDispatcher.dispatcher.sendTo(message, player);
	}
	
	/**
	* Send this message to everyone within a certain range of a point.
	* See {@link SimpleNetworkWrapper#sendToDimension(IMessage, NetworkRegistry.TargetPoint)}
	*/
	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
		PacketDispatcher.dispatcher.sendToAllAround(message, point);
	}
	
	/**
	* Sends a message to everyone within a certain range of the coordinates in the same dimension.
	*/
	public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
		PacketDispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}
	
	/**
	* Sends a message to everyone within a certain range of the player provided.
	*/
	public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
		PacketDispatcher.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
	}
	
	/**
	* Send this message to everyone within the supplied dimension.
	* See {@link SimpleNetworkWrapper#sendToDimension(IMessage, int)}
	*/
	public static final void sendToDimension(IMessage message, int dimensionId) {
		PacketDispatcher.dispatcher.sendToDimension(message, dimensionId);
	}
	
	/**
	* Send this message to the server.
	* See {@link SimpleNetworkWrapper#sendToServer(IMessage)}
	*/
	public static final void sendToServer(IMessage message) {
		PacketDispatcher.dispatcher.sendToServer(message);
	}
}