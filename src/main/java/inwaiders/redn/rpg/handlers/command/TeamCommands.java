package inwaiders.redn.rpg.handlers.command;

import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.storage.server.TeamServer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
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
		 PlayerInfoServer te = PlayerInfoManagerServer.instance.get(ep);
		 
		 if(args.length < 1){

			 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
			 return;
		 }
	   
		 switch(args[0]){
	   	
		 		case "join" :
		 			if(args.length < 2){

		 				 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
		 				 return;
		 			 }
		 			if(te.team.equals("ANY")){
		 				
			 			TeamServer teg = TeamManagerServer.instance.get(args[1]);
			 			System.out.println(args[1] + "/" + teg);
			 			if(teg.getTeamSize() == 0){
			 				
			 				teg.joinToPlayer(ep);
			 				te.team = args[1];
					   		ep.addChatComponentMessage(new ChatComponentText("You first joined to Team : " + args[1] + " !"));
					   		teg.setOwner(ep);
			 			}
			 			else{
			 				
			 				teg.joinToAcces(ep);
					   		ep.addChatComponentMessage(new ChatComponentText("You send request to Team : " + args[1] + " !"));
			 			}
				   		
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You are in Team : " + te.team + " !"));
		 			}
				
			   	break;
			   	
		 		case "approve" :
		 			if(args.length < 2){

		 				 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
		 				 return;
		 			 }
		 			if(!te.team.equals("ANY")){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.team);
		 				
		 					if(teg.getOwnerName().equals(ep.getCommandSenderName())){
		 						
		 						if(teg.accesWait.containsKey(args[1])){
		 							
		 							EntityPlayer accesingPlayer = teg.accesWait.get(args[1]);
		 							PlayerInfoServer te1 = PlayerInfoManagerServer.instance.get(accesingPlayer);
					 				te1.team = teg.getTeamName();
					 				teg.accesWait.get(accesingPlayer).addChatComponentMessage(new ChatComponentText("Your approve to join team : " + teg.getTeamName()));
					 				teg.approve(accesingPlayer);
		 							ep.addChatComponentMessage(new ChatComponentText("Your approved player  "+args[1]));
		 
		 						}
		 						else{
		 							ep.addChatComponentMessage(new ChatComponentText("This Player not has in AccesList"));
		 						}
		 						
		 					}
		 					else{
		 						ep.addChatComponentMessage(new ChatComponentText("You no Owner"));
		 					}
		 					
		 				TeamManagerServer.instance.set(te.team, teg);
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You no have team ;("));
		 			}
		 			
		 		break;
		 	
		 		case "leave" :
		 		
		 			if(!te.team.equals("ANY")){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.team);
		 				if(teg != null)
		 				teg.leavePlayer(ep);
			 			ep.addChatComponentMessage(new ChatComponentText("You leave of Team : " + te.team + " !"));
			 			te.team = "ANY";
		 			}
			
		   		break;
		   		
		 		case "kick" :
		 			if(args.length < 2){

		 				 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
		 				 return;
		 			 }
 					
		 			PlayerInfoServer te2 = PlayerInfoManagerServer.instance.get((EntityPlayer) MinecraftServer.getServer().getConfigurationManager().getPlayerList(args[1]).get(0));
		 			if(te2 == null) {
 						ep.addChatComponentMessage(new ChatComponentText("Player not Found !"));
 						return;
 					}
		 			if(te.team.equals(te2.team)){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.team);
		 				
		 				if(teg.getOwnerName().equals(ep.getCommandSenderName())){

		 					teg.leavePlayer(ep.worldObj.getPlayerEntityByName(args[1]));
		 					te2.team = "ANY";
		 					ep.addChatComponentMessage(new ChatComponentText("You kicked " + args[1] + " !"));
		 				}
			 			else{
			 				ep.addChatComponentMessage(new ChatComponentText("You are not Owner"));
			 			}
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText(args[1] + " In Another Team !"));
		 			}
		 			
		   		break;
		   		
		 		case "iowner" :
			 		
		 			if(!te.team.equals("ANY")){
		 				
		 				TeamServer teg = TeamManagerServer.instance.get(te.team);
		 				
		 					if(teg.getOwnerName().equals("")){
		 						teg.setOwner(ep);
		 						ep.addChatComponentMessage(new ChatComponentText("You are owner now"));
		 					}
		 					else{
		 						ep.addChatComponentMessage(new ChatComponentText("Team alredy has owner"));
		 					}
		 			}
		 			else{
		 				ep.addChatComponentMessage(new ChatComponentText("You have no team :("));
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
