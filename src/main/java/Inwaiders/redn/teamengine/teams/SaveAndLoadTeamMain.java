package Inwaiders.redn.teamengine.teams;

import java.io.FileNotFoundException;
import java.io.IOException;

import Inwaiders.redn.rpg.base.LAS;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveAndLoadTeamMain {

	@SubscribeEvent
	public void save(SaveToFile e){
		
		for(int i = 0;i<GeterTMStoP.par.size();i++){
			
			ComposerTeamMainClassServerProvider s1 = (ComposerTeamMainClassServerProvider) GeterTMStoP.par.get(i);
			TeamMainClassServerProvider s = s1.getParam();
			
			LAS.deleteFile("Team_"+s.getTeamName());
			try {
				
				if(s.getOwnerName() != "")
					LAS.setString("Team_"+s.getTeamName(), "TeamOwner", s.getOwnerName());
				
				for(int p = 0;p<s.players.size();p++){
					LAS.setString("Team_"+s.getTeamName(), "Players"+p, s.players.get(p).getCommandSenderName());
				}
				
				LAS.setInteger("Team_"+s.getTeamName(), "PlayersCount", s.players.size());
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void load(EntityPlayer ep, String name, TeamMainClassServerProvider s){
		
		try {
				s.setNickOwner(LAS.getString("Team_"+name, "TeamOwner"));
			
			for(int i = 0;i<LAS.getInteger("Team_"+name, "PlayersCount");i++){
				s.joinToAcces(ep.worldObj.getPlayerEntityByName(LAS.getString("Team_"+name, "Players"+i)));
			}
			
			GeterTMStoP.setParamToEntity(name, s);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
}
