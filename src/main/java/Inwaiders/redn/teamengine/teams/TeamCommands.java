package Inwaiders.redn.teamengine.teams;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class TeamCommands implements ICommand{

	 private List aliases;
	 
	 public TeamCommands(){
	   this.aliases = new ArrayList();
	   this.aliases.add("team");
	 }
	 
	 @Override
	 public String getCommandName(){
	   return "team";
	 }
	 
	 @Override
	 public String getCommandUsage(ICommandSender icommandsender){
	   return "team <text>";
	 }
	 
	 @Override
	 public List getCommandAliases(){
	   return this.aliases;
	 }
	 
	 @Override
	 public void processCommand(ICommandSender icommandsender, String[] astring){

		 EntityPlayer ep = (EntityPlayer)icommandsender;
		 TeamEngineServerProvider te = GeterTStoP.getParam(ep.getEntityId());
		 
		 if(astring.length <= 1){

			 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
			 return;
		 }
	   
		 switch(astring[0]){
	   	
		 		case "join" :
		 			
		 			if(te.getTeam().equals("ANY")){
		 				
			 			TeamMainClassServerProvider teg = GeterTMStoP.getParam(astring[1]);
			 			
			 			if(teg.getTeamSize() == 0){
			 				
			 				teg.joinToPlayer(ep);
			 				te.setTeam(astring[1]);
			 				GeterTStoP.setParamToEntity(ep.getEntityId(), te);
			 				
			 				GeterTMStoP.setParamToEntity(astring[1], teg);
					   		ep.addChatComponentMessage(new ChatComponentText("You first join to Team : " + astring[1] + " !"));
			 			}
			 			else{
			 				
			 				teg.joinToAcces(ep);
					   		GeterTMStoP.setParamToEntity(astring[1], teg);
					   		ep.addChatComponentMessage(new ChatComponentText("You send acces to Team : " + astring[1] + " !"));
			 			}
				   		
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You in Team : " + te.getTeam() + " !"));
		 			}
				
			   	break;
			   	
		 		case "acces" :
		 			
		 			if(!te.getTeam().equals("ANY")){
		 				
		 				TeamMainClassServerProvider teg = GeterTMStoP.getParam(te.getTeam());
		 				
		 					if(teg.getOwnerName().equals(ep.getCommandSenderName())){
		 						
		 						if(teg.hasInAcces(astring[1]) != -1){
		 							
		 							
		 							TeamEngineServerProvider te1 = GeterTStoP.getParam(teg.accesWait.get(teg.hasInAcces(astring[1])).getEntityId());
					 				te1.setTeam(teg.getTeamName());
					 				
					 				GeterTStoP.setParamToEntity(teg.accesWait.get(teg.hasInAcces(astring[1])).getEntityId(), te1);
					 				teg.accesWait.get(teg.hasInAcces(astring[1])).addChatComponentMessage(new ChatComponentText("Your Accesed To Team : " + teg.getTeamName()));
					 				teg.accesing(teg.hasInAcces(astring[1]));
					 				GeterTStoP.setParamToEntity(ep.getEntityId(), te);
		 							ep.addChatComponentMessage(new ChatComponentText("Your Acces "+astring[1]));
		 
		 						}
		 						else{
		 							ep.addChatComponentMessage(new ChatComponentText("This Player not has in AccesList"));
		 						}
		 						
		 					}
		 					else{
		 						ep.addChatComponentMessage(new ChatComponentText("You no Owner"));
		 					}
		 					
		 				GeterTMStoP.setParamToEntity(te.getTeam(), teg);
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You no have team ;("));
		 			}
		 			
		 		break;
		 	
		 		case "leave" :
		 		
		 			if(!te.getTeam().equals("ANY")){
		 				
		 				TeamMainClassServerProvider teg = GeterTMStoP.getParam(astring[1]);
		 				teg.leavePlayer(ep);
			 			ep.addChatComponentMessage(new ChatComponentText("You leave of Team : " + te.getTeam() + " !"));
			 			te.setTeam("ANY");
			 			GeterTStoP.setParamToEntity(ep.getEntityId(), te);
			 			GeterTMStoP.setParamToEntity(astring[1], teg);
		 			}
			
		   		break;
		   		
		 		case "kick" :
		 			
 					if(ep.worldObj.getPlayerEntityByName(astring[1]) == null) {
 						ep.addChatComponentMessage(new ChatComponentText("Player not Found !"));
 						return;
 					}
 					
		 			TeamEngineServerProvider te2 = GeterTStoP.getParam(ep.worldObj.getPlayerEntityByName(astring[1]).getEntityId());
		 			
		 			if(te.getTeam().equals(te2.getTeam())){
		 				
		 				TeamMainClassServerProvider teg = GeterTMStoP.getParam(te.getTeam());
		 				
		 				if(teg.getOwnerName().equals(ep.getCommandSenderName())){

		 					teg.leavePlayer(ep.worldObj.getPlayerEntityByName(astring[1]));
		 					te2.setTeam("ANY");
		 					ep.addChatComponentMessage(new ChatComponentText("You kicked " + astring[1] + " !"));
				 			GeterTStoP.setParamToEntity(ep.worldObj.getPlayerEntityByName(astring[1]).getEntityId(), te2);
				 			GeterTMStoP.setParamToEntity(te.getTeam(), teg);
		 				}
			 			else{
			 				ep.addChatComponentMessage(new ChatComponentText("You no Owner"));
			 			}
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText(astring[1] + " In Another Team !"));
		 			}
		 			
		   		break;
		   		
		 		case "iowner" :
			 		
		 			if(!te.getTeam().equals("ANY")){
		 				
		 				TeamMainClassServerProvider teg = GeterTMStoP.getParam(te.getTeam());
		 				
		 					if(teg.getOwnerName().equals("")){
		 						teg.setOwner(ep);
		 						ep.addChatComponentMessage(new ChatComponentText("You set Owner"));
		 					}
		 					else{
		 						ep.addChatComponentMessage(new ChatComponentText("Owner last seted"));
		 					}
		 					
		 				GeterTMStoP.setParamToEntity(ep.getCommandSenderName(), teg);
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You no have team ;("));
		 			}
			
		   		break;
	   
		 }
	   
	 }
	 
	 @Override
	 public boolean canCommandSenderUseCommand(ICommandSender icommandsender){
	   return true;
	 }
	 
	 @Override
	 public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring){
	   return null;
	 }
	 
	 @Override
	 public boolean isUsernameIndex(String[] astring, int i){
	   return false;
	 }
	 
	 @Override
	 public int compareTo(Object o){
	   return 0;
	 }
}
