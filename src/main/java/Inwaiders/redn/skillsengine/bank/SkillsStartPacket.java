package Inwaiders.redn.skillsengine.bank;

import Inwaiders.redn.rpg.packetdispatcher.AbstractServerMessageHandler;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.hotbar.GeterHtoP;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
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
			 
		   SkillsBankServerProvider b = GeterBStoP.getParam(player);
		   
		   b.activeSkill(message.i, player);
		   
	 	   return null;
		}
	 
	}
}
