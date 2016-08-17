package inwaiders.redn.rpg.packet;

import inwaiders.redn.rpg.packetdispatcher.AbstractServerMessageHandler;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class LearnSkillPackect implements IMessage
{

	
	
	private int id;
	
	public LearnSkillPackect()
	{
		
	}
	
	public LearnSkillPackect(int id)
	{
		this.id = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		id = ByteBufUtils.readVarShort(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarShort(buf, id);
	}

	public static class Handler extends AbstractServerMessageHandler<LearnSkillPackect>
	{

		@Override
		public IMessage handleServerMessage(EntityPlayer player, LearnSkillPackect message, MessageContext ctx)
		{
			SkillsRegistry.learnSkill(player, message.id);
			return null;
		}
		
	}
	
}
