package inwaiders.redn.rpg.packet.sync;

import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PlayerInfoSync implements IMessage {

	private NBTTagCompound nbt;

	public PlayerInfoSync() {

	}

	private int i = 0;

	public PlayerInfoSync(EntityPlayer ep) {
		PlayerInfoServer i = PlayerInfoManagerServer.instance.get(ep);
		i.saveBank();
		this.nbt = i.nbt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends AbstractClientMessageHandler<PlayerInfoSync> {

		@Override
		public IMessage handleClientMessage(EntityPlayer player, PlayerInfoSync message, MessageContext ctx) {
			PlayerInfoClient i = PlayerInfoManagerClient.instance.get(player);
			i.nbt = message.nbt;
			i.loadBank();
			return null;
		}
	}

}
