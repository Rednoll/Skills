package inwaiders.redn.rpg.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.files.SaveAndLoadPlayer;
import inwaiders.redn.rpg.files.SaveAndLoadTeam;
import inwaiders.redn.rpg.gui.GuiHandler;
import inwaiders.redn.rpg.handlers.event.EntityDie;
import inwaiders.redn.rpg.handlers.event.PlayerEventHandler;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.BlockRegistry;
import inwaiders.redn.rpg.registry.ItemRegistry;
import inwaiders.redn.rpg.registry.ItemSkillRegistry;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.registry.XPRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e)
	{
		CFG.init(new Configuration(e.getSuggestedConfigurationFile()));
		ItemRegistry.init();
		BlockRegistry.init();
		XPRegistry.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		PacketDispatcher.registerPackets();
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadPlayer());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadTeam());
		MinecraftForge.EVENT_BUS.register(new EntityDie());
		NetworkRegistry.INSTANCE.registerGuiHandler(Core.instance, new GuiHandler());
		SkillsRegistry.init();
		ItemSkillRegistry.init();
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
