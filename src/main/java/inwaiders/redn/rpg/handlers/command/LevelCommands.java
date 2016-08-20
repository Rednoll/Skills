package inwaiders.redn.rpg.handlers.command;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.managers.server.TeamManagerServer;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.storage.server.TeamServer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class LevelCommands implements ICommand{

	 private List aliases;
	 
	 public LevelCommands(){
	   this.aliases = new ArrayList();
	 }
	 
	 @Override
	 public String getCommandName(){
	   return "level";
	 }
	 
	 @Override
	 public String getCommandUsage(ICommandSender icommandsender){
	   return "level <text>";
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
	   	
		 		case "get" :
		 			
		 			ep.addChatComponentMessage(new ChatComponentText("Level : " + te.getLevel()));
		 			ep.addChatComponentMessage(new ChatComponentText("Exp : " + te.getXp() + "/" + te.getNextXp(te.getLevel() + 1) + "   " + ((te.getXp()*100)/te.getNextXp(te.getLevel() + 1))+"% !"));
		 			
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
