package inwaiders.redn.rpg.base.proxy;

import inwaiders.redn.rpg.base.CFG;
import inwaiders.redn.rpg.base.PlayerUpdate;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.skillsengine.bank.SaveBankAndHot;
import inwaiders.redn.skillsengine.bank.SkillsRegistry;
import inwaiders.redn.teamengine.teams.SaveAndLoadTeam;
import inwaiders.redn.teamengine.teams.SaveAndLoadTeamMain;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e)
	{
		CFG.init(new Configuration(e.getSuggestedConfigurationFile()));
	}
	
	public void init(FMLInitializationEvent e)
	{
		PacketDispatcher.registerPackets();
		MinecraftForge.EVENT_BUS.register(new PlayerUpdate());
		MinecraftForge.EVENT_BUS.register(new SaveBankAndHot());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadTeam());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadTeamMain());
		SkillsRegistry.init();
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
