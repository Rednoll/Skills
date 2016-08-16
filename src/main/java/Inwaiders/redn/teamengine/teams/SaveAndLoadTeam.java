package Inwaiders.redn.teamengine.teams;

import java.io.FileNotFoundException;
import java.io.IOException;

import Inwaiders.redn.rpg.base.LAS;
import Inwaiders.redn.skillsengine.bank.GeterBStoP;
import Inwaiders.redn.skillsengine.bank.SkillsBankServerProvider;
import Inwaiders.redn.skillsengine.bank.SkillsStaticTable;
import Inwaiders.redn.skillsengine.bank.SuperPuperZipWinRar;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.hotbar.GeterHtoP;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveAndLoadTeam {

	@SubscribeEvent
	public void save(SaveToFile e){
		
		TeamEngineServerProvider te = GeterTStoP.getParam(e.entityPlayer.getEntityId());
		
		try {
			LAS.setString(e.entityPlayer.getCommandSenderName(), "Team", te.getTeam());
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
	
	public static void load(EntityPlayer ep){
		
		TeamEngineServerProvider te = GeterTStoP.getParam(ep.getEntityId());
		
		try {
			if(LAS.getString(ep.getCommandSenderName(), "Team") != "")
				te.setTeam(LAS.getString(ep.getCommandSenderName(), "Team"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		GeterTStoP.setParamToEntity(ep.getEntityId(), te);
	}
	
}
