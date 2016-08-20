package inwaiders.redn.rpg.handlers.command;

import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;

import java.util.HashMap;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class UtilityCommandHandler extends CommandBase {

	@Override
	public String getCommandName() {
		return "RPG";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/RPG help";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		switch(args[0])
		{
			case("help"):
			{
				sender.addChatMessage(new ChatComponentText("RGP reset - reset your nbt for RPG mod"));
				return;
			}
			case("reset"):
			{
				if(!(sender instanceof EntityPlayer))
				{
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Cannot use reset from command block"));
					return;
				}
				PlayerInfoServer i = PlayerInfoManagerServer.instance.get(getCommandSenderAsPlayer(sender));
				i.nbt = new NBTTagCompound();
				i.setSkills(new HashMap<String, BaseSkill>());
				i.resetNBT();
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succesfully reseted nbt"));
				return;
			}
			case("bank"):
			{
				PlayerInfoServer i = PlayerInfoManagerServer.instance.get(getCommandSenderAsPlayer(sender));
				i.setSkills(i.getSkills());
				break;
			}
			default:
			{
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Unknown RPG command"));
			}
		}
	}

}
