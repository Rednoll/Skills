package Inwaiders.redn.skillsengine.bank;

import java.util.ArrayList;

import Inwaiders.redn.rpg.packetdispatcher.AbstractClientMessageHandler;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.hotbar.GeterHtoP;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncStoCProviders implements IMessage{

	NBTTagCompound nbt;
	
	public SyncStoCProviders(){
		
	}
	
	public SyncStoCProviders(EntityPlayer ep){	
		nbt = new NBTTagCompound();
		
		SkillsBankServerProvider b = GeterBStoP.getParam(ep); 
		SkillsHotBar h = GeterHtoP.getParam(ep.getEntityId());
		
		for(int i = 0;i<b.skills.size();i++){
			
			int[] args = new int[3];
			
			args[0] = b.skills.get(i).getId();
			args[1] = b.skills.get(i).getLevel();
			args[2] = b.skills.get(i).getCoolDown();
			
			nbt.setString("Bank"+i, SuperPuperZipWinRar.packing(args));
		}
		
		for(int i = 0;i<h.skills.length;i++){
			
			nbt.setInteger("Hot"+i, h.skills[i]);
		}
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler extends AbstractClientMessageHandler<SyncStoCProviders> {
		
		@Override
		 public IMessage handleClientMessage(EntityPlayer player, SyncStoCProviders message, MessageContext ctx) {
			
			SkillsHotBar se = null;
			
			se = GeterHtoP.getParam(player.getEntityId());
			
	    	SkillsBankClientProvider b = null;
	    	
	    	b = GeterBCtoP.getParam(player);
	    	
			int iCount = 0;
			
			for(int i = 0;i<6;i++){

				if(message.nbt.hasKey("Hot"+i)){
					se.skills[i] = message.nbt.getInteger("Hot"+i);
				}
				
			}
			
			b.skills = new ArrayList();
			
			while(true){
				if(message.nbt.hasKey("Bank"+iCount)){
					
					int id = SuperPuperZipWinRar.getAr(message.nbt.getString("Bank"+iCount), 0);
					int Level = SuperPuperZipWinRar.getAr(message.nbt.getString("Bank"+iCount), 1);
					int cd = SuperPuperZipWinRar.getAr(message.nbt.getString("Bank"+iCount), 2);
					
					BaseSkill s;
					try {
						s = SkillsStaticTable.getSkillById(id);
						s.setLevel(Level);
						s.setCoolDown(cd);
						
						b.skills.add(s);
					} catch (InstantiationException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}
					
					iCount++;
				}
				else{
					break;
				}
				
			}
			
			return null;
		}
	}

}