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
		nbt = new NBTTagCompound();
		PlayerInfoServer b = PlayerInfoManagerServer.instance.get(ep);
		b.getSkills().forEach((id, skill) -> nbt.setIntArray("Bank" + i++, new int[] { skill.getId(), skill.getLevel(), skill.getCoolDown() }));
		i = 0;
		nbt.setIntArray("Hot", b.hotbarSkills);
		nbt.setString("Team", b.getTeam());
		nbt.setInteger("LP", b.getLearnPoints());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends
			AbstractClientMessageHandler<PlayerInfoSync> {

		@Override
		public IMessage handleClientMessage(EntityPlayer player,
				PlayerInfoSync message, MessageContext ctx) {

			PlayerInfoClient b = PlayerInfoManagerClient.instance.get(player);

			int iCount = 0;
			b.hotbarSkills = message.nbt.getIntArray("Hot");
			HashMap<Integer, BaseSkill> skills = new HashMap<Integer, BaseSkill>();
			b.setSkills(skills);

			while (message.nbt.hasKey("Bank" + iCount)) {
				int[] skill = message.nbt.getIntArray("Bank" + iCount++);
				BaseSkill s;
				s = SkillsRegistry.getSkillById(skill[0]);
				s.setLevel(skill[1]);
				s.setCoolDown(skill[2]);
				skills.put(s.getId(), s);

			}
			b.setTeam(message.nbt.getString("Team"));
			b.setLearnPoints(message.nbt.getInteger("LP"));
			return null;
		}
	}

}
