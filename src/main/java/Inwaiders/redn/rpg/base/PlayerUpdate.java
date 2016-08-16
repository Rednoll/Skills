package Inwaiders.redn.rpg.base;


import Inwaiders.redn.skillsengine.bank.GeterBCtoP;
import Inwaiders.redn.skillsengine.bank.GeterBStoP;
import Inwaiders.redn.skillsengine.bank.SkillsBankClientProvider;
import Inwaiders.redn.skillsengine.bank.SkillsBankServerProvider;
import Inwaiders.redn.skillsengine.learn.GeterLpStoP;
import Inwaiders.redn.skillsengine.learn.LearnPointsServerProvider;
import Inwaiders.redn.teamengine.teams.GeterTCtoP;
import Inwaiders.redn.teamengine.teams.GeterTMCtoP;
import Inwaiders.redn.teamengine.teams.GeterTMStoP;
import Inwaiders.redn.teamengine.teams.GeterTStoP;
import Inwaiders.redn.teamengine.teams.TeamEngineClientProvider;
import Inwaiders.redn.teamengine.teams.TeamEngineServerProvider;
import Inwaiders.redn.teamengine.teams.TeamMainClassClientProvider;
import Inwaiders.redn.teamengine.teams.TeamMainClassServerProvider;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PlayerUpdate {

	@SubscribeEvent
	public void updater(LivingUpdateEvent event){
		
		if(event.entityLiving instanceof EntityPlayer){		
			
			EntityPlayer ep = (EntityPlayer)event.entityLiving;
			
			if(!event.entity.worldObj.isRemote){
				SkillsBankServerProvider serverBank = GeterBStoP.getParam(ep);
				serverBank.updateEngine();
				
				TeamEngineServerProvider serverPersTeam = GeterTStoP.getParam(ep.getEntityId());
				serverPersTeam.updateEngine(ep);
				
				TeamMainClassServerProvider serverMainTeam = GeterTMStoP.getParam(serverPersTeam.getTeam());
				serverMainTeam.updateEngine(ep);
				
				LearnPointsServerProvider serverLearnPoints = GeterLpStoP.getParam(ep);
				serverLearnPoints.updateEngine(ep);
			}
			else{
				SkillsBankClientProvider client = GeterBCtoP.getParam(ep);
				client.updateEngine();
				
				TeamEngineClientProvider clientPersTeam = GeterTCtoP.getParam(ep.getEntityId());
				clientPersTeam.updateEngine(ep);
				
				TeamMainClassClientProvider clientMainTeam = GeterTMCtoP.getParam(clientPersTeam.getTeam());
				clientMainTeam.updateEngine();
			
			}
		}
	}
	
}
