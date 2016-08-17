package inwaiders.redn.skillsengine.bank;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import inwaiders.redn.rpg.packetdispatcher.AbstractServerMessageHandler;
import inwaiders.redn.skillsengine.examples.BaseSkill;
import inwaiders.redn.skillsengine.hotbar.SkillsHotBarManager;
import inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SkillsStartPacket  implements IMessage{
	
	private int i = 0;
	
	public SkillsStartPacket(){
		
	}
	
	public SkillsStartPacket(int i){
		this.i = i;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.i = ByteBufUtils.readVarShort(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarShort(buf, this.i);
		
	}

	public static class Handler extends AbstractServerMessageHandler<SkillsStartPacket> {
		
		 @Override
		 public IMessage handleServerMessage(EntityPlayer player, SkillsStartPacket message, MessageContext ctx) {
			 
		   PlayerSkillBankServer b = BankManagerServer.instance.get(player);
		   
		   b.activateSkill(message.i, player);
		   
	 	   return null;
		}
	 
	}
}
