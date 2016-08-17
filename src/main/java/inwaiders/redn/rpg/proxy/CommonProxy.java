package inwaiders.redn.rpg.proxy;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.files.SaveAndLoadTeam;
import inwaiders.redn.rpg.files.SavePlayer;
import inwaiders.redn.rpg.gui.GuiHandler;
import inwaiders.redn.rpg.handlers.event.PlayerUpdate;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.ItemRegistry;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e)
	{
		CFG.init(new Configuration(e.getSuggestedConfigurationFile()));
		ItemRegistry.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		PacketDispatcher.registerPackets();
		MinecraftForge.EVENT_BUS.register(new PlayerUpdate());
		MinecraftForge.EVENT_BUS.register(new SavePlayer());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadTeam());
		NetworkRegistry.INSTANCE.registerGuiHandler(Core.instance, new GuiHandler());
		SkillsRegistry.init();
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
