package inwaiders.redn.rpg.handlers.command;

import java.util.ArrayList;
import java.util.List;

import inwaiders.redn.rpg.items.CAD.CadMemoryCard;
import inwaiders.redn.rpg.utils.util;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class CADCommands implements ICommand{

	 private List aliases;
	 
	 public CADCommands(){
	   this.aliases = new ArrayList();
	 }
	 
	 @Override
	 public String getCommandName(){
	   return "CAD";
	 }
	 
	 @Override
	 public String getCommandUsage(ICommandSender icommandsender){
	   return "CAD <text>";
	 }
	 
	 @Override
	 public List getCommandAliases(){
	   return this.aliases;
	 }
	 
	 @Override
	 public void processCommand(ICommandSender icommandsender, String[] args){

		 EntityPlayer ep = (EntityPlayer)icommandsender;
		 
		 if(args.length <= 16){

			 ep.addChatComponentMessage(new ChatComponentText("Invalid arguments"));
			 return;
		 }
	   
		 switch(args[0]){
	   	
		 		case "inject" :

		 			ItemStack is = ep.getHeldItem();
		 			
		 			if(is == null){
		 				break;
		 			}
		 			
		 			if(is.getItem() instanceof CadMemoryCard){
		 				
		 				int l = 0;
		 				
		 				while(true){
		 					if(util.getString(is, "CODE_"+l, "") != ""){
		 						ep.addChatComponentMessage(new ChatComponentText("Have "+l+" : "+util.getString(is, "CODE_"+l, "")));
		 						l++;
		 					}
		 					else{
		 						break;
		 					}
		 				}
		 				
		 				l++;
		 				
		 				util.setString(is, "CODE_"+l, args[1]+" "+args[2]+" "+args[3]+" "+args[4]+" "+args[5]+" "+args[6]+" "+args[7]+" "+args[8]+" "+args[9]+" "+args[10]+" "+args[11]+" "+args[12]+" "+args[13]+" "+args[14]+" "+args[15]+" "+args[16]);
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

