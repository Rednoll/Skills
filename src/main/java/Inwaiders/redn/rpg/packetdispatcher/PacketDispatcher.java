package Inwaiders.redn.rpg.packetdispatcher;


import Inwaiders.redn.skillsengine.bank.SkillsStartPacket;
import Inwaiders.redn.skillsengine.bank.SyncStoCProviders;
import Inwaiders.redn.skillsengine.learn.SyncLearnPoints;
import Inwaiders.redn.teamengine.teams.SyncTeamMainClass;
import Inwaiders.redn.teamengine.teams.SyncTeamPrivatePacket;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDispatcher
{
	private static byte packetId = 0;
	
	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("rpg");
	
	public static final void registerPackets() {

		//PacketDispatcher.dispatcher.registerMessage(SkillLoadToClient.Handler.class, SkillLoadToClient.class, packetId++, Side.CLIENT);

		//PacketDispatcher.dispatcher.registerMessage(SkillGeterPacket.Handler.class, SkillGeterPacket.class, packetId++, Side.SERVER);
		//SyncLearnPoints
		
		PacketDispatcher.dispatcher.registerMessage(SyncLearnPoints.Handler.class, SyncLearnPoints.class, packetId++, Side.CLIENT);
		
		PacketDispatcher.dispatcher.registerMessage(SyncStoCProviders.Handler.class, SyncStoCProviders.class, packetId++, Side.CLIENT);
		
		PacketDispatcher.dispatcher.registerMessage(SyncTeamMainClass.Handler.class, SyncTeamMainClass.class, packetId++, Side.CLIENT);
		
		PacketDispatcher.dispatcher.registerMessage(SyncTeamPrivatePacket.Handler.class, SyncTeamPrivatePacket.class, packetId++, Side.CLIENT);
		
		PacketDispatcher.dispatcher.registerMessage(SkillsStartPacket.Handler.class, SkillsStartPacket.class, packetId++, Side.SERVER);
	}

	private static final void registerMessage(Class handlerClass, Class messageClass, Side side) {
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