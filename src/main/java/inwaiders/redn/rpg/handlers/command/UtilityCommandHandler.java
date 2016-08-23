package inwaiders.redn.rpg.handlers.command;

import inwaiders.redn.rpg.files.nbt.PlayerNbt;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.registry.ItemRegistry;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.utils.ItemNBT;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	public List getCommandAliases() {
		return Arrays.asList("rpg");
	}

	private boolean checkargs(ICommandSender sender, int argc, String[] argv)
	{
		if(argv.length < argc)
		{
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments"));
			return false;
		}
		return true;
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(!checkargs(sender, 1, args))
		{
			return;
		}
		EntityPlayer ep = getCommandSenderAsPlayer(sender);
		PlayerInfoServer i = PlayerInfoManagerServer.instance.get(ep);
		switch (args[0]) {
			case ("help"): {
				sender.addChatMessage(new ChatComponentText("rpg reset - reset your nbt for RPG mod"));
				sender.addChatMessage(new ChatComponentText("rpg level - displays current level, will be removed after normal display will be created"));
				sender.addChatMessage(new ChatComponentText("rpg setlevel <level> - change your level, do not affect points"));
				sender.addChatMessage(new ChatComponentText("rpg setxp <xp> - change your xp"));
				sender.addChatMessage(new ChatComponentText("rpg setlp - change your learn points"));
				sender.addChatMessage(new ChatComponentText("rpg scroll [skillname] - get skill scroll, if skill is not specified, random scroll will be given"));
				return;
			}
			case ("reset"): {
				i.setSkills(new HashMap<String, BaseSkill>());
				PlayerNbt nbt = new PlayerNbt(i.ep);
				nbt.resetNBT();
				nbt.nsload(i);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succesfully reseted nbt"));
				return;
			}
			case ("level"): {
				ep.addChatComponentMessage(new ChatComponentText("Level : " + i.lvl));
				ep.addChatComponentMessage(new ChatComponentText("Exp : " + i.xp + "/" + i.getNextXp(i.lvl + 1) + "   " + ((i.xp * 100) / i.getNextXp(i.lvl + 1)) + "% !"));
				return;
			}
			case ("setlevel"):
			{
				if(!checkargs(sender, 2, args))
				{
					return;
				}
				i.lvl = parseInt(sender, args[1]);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succes"));
				return;
			}
			case("setxp"):
			{
				if(!checkargs(sender, 2, args))
				{
					return;
				}
				i.xp = parseInt(sender, args[1]);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succes"));
				return;
			}
			case("setlp"):
			{
				if(!checkargs(sender, 2, args))
				{
					return;
				}
				i.learnpoints = parseInt(sender, args[1]);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succes"));
				return;
			}
			case("scroll"):
			{
				ItemStack scroll = new ItemStack(ItemRegistry.skillScroll);
				if(checkargs(sender, 2, args))
				{
					if(SkillsRegistry.getSkillByName(args[1]) != null)
					{
						ItemNBT.setString(scroll, "Skill", args[1]);
					}
					else
					{
						sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid skill name"));
						return;
					}
				}
				else
				{
					scroll.getItem().onCreated(scroll, ep.worldObj, ep);
				}
				MiscUtils.addItemToPlayer(ep, scroll);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succes"));
				return;
			}
			default: {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Unknown RPG command"));
			}
		}
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender instanceof EntityPlayer;
	}

}
