package Inwaiders.redn.skillsengine.bank;

import java.io.FileNotFoundException;
import java.io.IOException;

import Inwaiders.redn.rpg.base.LAS;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.hotbar.GeterHtoP;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveBankAndHot {

	@SubscribeEvent
	public void save(SaveToFile e){
		
		SkillsBankServerProvider b = GeterBStoP.getParam(e.entityPlayer);
		SkillsHotBar h = GeterHtoP.getParam(e.entityPlayer.getEntityId());
		
		for(int i = 0;i<b.skills.size();i++){
			
			int[] args = new int[3];
			
			args[0] = b.skills.get(i).getId();
			args[1] = b.skills.get(i).getLevel();
			args[2] = b.skills.get(i).getCoolDown();

			
			String par = SuperPuperZipWinRar.packing(args);
			
			try {
				LAS.setString(e.entityPlayer.getCommandSenderName(), "Bank"+i, par);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		
		for(int i = 0;i < h.skills.length;i++){
			try {
				LAS.setInteger(e.entityPlayer.getCommandSenderName(), "Hot"+i, h.skills[i]);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void load(EntityPlayer ep){
		
		SkillsBankServerProvider b = GeterBStoP.getParam(ep);
		SkillsHotBar h = GeterHtoP.getParam(ep.getEntityId());
		
		int iCount = 0;
		
		while(true){
			
			try {
				
				if(LAS.getString(ep.getCommandSenderName(), "Bank"+iCount) != ""){
					String pack = LAS.getString(ep.getCommandSenderName(), "Bank"+iCount);
					
					int id = SuperPuperZipWinRar.getAr(pack, 0);
					int Level = SuperPuperZipWinRar.getAr(pack, 1);
					int cd = SuperPuperZipWinRar.getAr(pack, 2);
					
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
					

				}
				else{
					break;
				}
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			iCount++;
		}
		
		for(int i = 0;i<h.skills.length;i++){
			
			try {
				h.skills[i] = LAS.getInteger(ep.getCommandSenderName(), "Hot"+i);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	
	}
	
	
}
