package inwaiders.redn.rpg.packet;

import inwaiders.redn.rpg.packetdispatcher.AbstractServerMessageHandler;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class LearnSkillPackect implements IMessage
{

	
	
	private String name;
	private int slotnum;
	public LearnSkillPackect()
	{
		
	}
	
	public LearnSkillPackect(String name, int slotnum)
	{
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

	public static class Handler extends AbstractServerMessageHandler<LearnSkillPackect>
	{

		@Override
		public IMessage handleServerMessage(EntityPlayer player, LearnSkillPackect message, MessageContext ctx)
		{
			SkillsRegistry.learnSkill(player, message.name);
			if(!player.capabilities.isCreativeMode)
			{
				player.inventory.setInventorySlotContents(message.slotnum, null);
			}
			return null;
		}
		
	}
	
}
