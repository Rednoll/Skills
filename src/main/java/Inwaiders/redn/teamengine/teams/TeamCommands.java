package inwaiders.redn.teamengine.teams;

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
	 public void processCommand(ICommandSender icommandsender, String[] args){

		 EntityPlayer ep = (EntityPlayer)icommandsender;
		 PlayerTeamServer te = PlayerTeamManagerServer.instance.get(ep);
		 
		 if(args.length <= 1){

			 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
			 return;
		 }
	   
		 switch(args[0]){
	   	
		 		case "join" :
		 			
		 			if(te.getTeam().equals("ANY")){
		 				
			 			TeamServer teg = TeamManagerServer.instance.get(args[1]);
			 			
			 			if(teg.getTeamSize() == 0){
			 				
			 				teg.joinToPlayer(ep);
			 				te.setTeam(args[1]);
					   		ep.addChatComponentMessage(new ChatComponentText("You first join to Team : " + args[1] + " !"));
			 			}
			 			else{
			 				
			 				teg.joinToAcces(ep);
					   		ep.addChatComponentMessage(new ChatComponentText("You send acces to Team : " + args[1] + " !"));
			 			}
				   		
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You in Team : " + te.getTeam() + " !"));
		 			}
				
			   	break;
			   	
		 		case "acces" :
		 			
		 			if(!te.getTeam().equals("ANY")){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.getTeam());
		 				
		 					if(teg.getOwnerName().equals(ep.getCommandSenderName())){
		 						
		 						if(teg.hasInAcces(args[1]) != -1){
		 							
		 							
		 							PlayerTeamServer te1 = PlayerTeamManagerServer.instance.get(teg.accesWait.get(teg.hasInAcces(args[1])));
					 				te1.setTeam(teg.getTeamName());
					 				
					 				PlayerTeamManagerServer.instance.set(teg.accesWait.get(teg.hasInAcces(args[1])), te1);
					 				teg.accesWait.get(teg.hasInAcces(args[1])).addChatComponentMessage(new ChatComponentText("Your Accesed To Team : " + teg.getTeamName()));
					 				teg.accesing(teg.hasInAcces(args[1]));
					 				PlayerTeamManagerServer.instance.set(ep, te);
		 							ep.addChatComponentMessage(new ChatComponentText("Your Acces "+args[1]));
		 
		 						}
		 						else{
		 							ep.addChatComponentMessage(new ChatComponentText("This Player not has in AccesList"));
		 						}
		 						
		 					}
		 					else{
		 						ep.addChatComponentMessage(new ChatComponentText("You no Owner"));
		 					}
		 					
		 				TeamManagerServer.instance.set(te.getTeam(), teg);
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You no have team ;("));
		 			}
		 			
		 		break;
		 	
		 		case "leave" :
		 		
		 			if(!te.getTeam().equals("ANY")){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(args[1]);
		 				teg.leavePlayer(ep);
			 			ep.addChatComponentMessage(new ChatComponentText("You leave of Team : " + te.getTeam() + " !"));
			 			te.setTeam("ANY");
			 			PlayerTeamManagerServer.instance.set(ep, te);
			 			TeamManagerServer.instance.set(args[1], teg);
		 			}
			
		   		break;
		   		
		 		case "kick" :
		 			
 					if(ep.worldObj.getPlayerEntityByName(args[1]) == null) {
 						ep.addChatComponentMessage(new ChatComponentText("Player not Found !"));
 						return;
 					}
 					
		 			PlayerTeamServer te2 = PlayerTeamManagerServer.instance.get(ep.worldObj.getPlayerEntityByName(args[1]));
		 			
		 			if(te.getTeam().equals(te2.getTeam())){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.getTeam());
		 				
		 				if(teg.getOwnerName().equals(ep.getCommandSenderName())){

		 					teg.leavePlayer(ep.worldObj.getPlayerEntityByName(args[1]));
		 					te2.setTeam("ANY");
		 					ep.addChatComponentMessage(new ChatComponentText("You kicked " + args[1] + " !"));
				 			PlayerTeamManagerServer.instance.set(ep.worldObj.getPlayerEntityByName(args[1]), te2);
				 			TeamManagerServer.instance.set(te.getTeam(), teg);
		 				}
			 			else{
			 				ep.addChatComponentMessage(new ChatComponentText("You no Owner"));
			 			}
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText(args[1] + " In Another Team !"));
		 			}
		 			
		   		break;
		   		
		 		case "iowner" :
			 		
		 			if(!te.getTeam().equals("ANY")){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.getTeam());
		 				
		 					if(teg.getOwnerName().equals("")){
		 						teg.setOwner(ep);
		 						ep.addChatComponentMessage(new ChatComponentText("You set Owner"));
		 					}
		 					else{
		 						ep.addChatComponentMessage(new ChatComponentText("Owner last seted"));
		 					}
		 					
		 				TeamManagerServer.instance.set(ep.getCommandSenderName(), teg);
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
