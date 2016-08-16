package Inwaiders.redn.rpg.base;

import java.util.Random;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import Inwaiders.redn.skillsengine.bank.SaveBankAndHot;
import Inwaiders.redn.skillsengine.bank.SkillsStaticTable;
import Inwaiders.redn.skillsengine.hotbar.SkillRenderer;
import Inwaiders.redn.skillsengine.hotbar.SkillStartetHandler;
import Inwaiders.redn.skillsengine.hotbar.SkillsKeys;
import Inwaiders.redn.teamengine.teams.SaveAndLoadTeam;
import Inwaiders.redn.teamengine.teams.SaveAndLoadTeamMain;
import Inwaiders.redn.teamengine.teams.TeamCommands;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = base.MODID, version = base.VERSION)
public class base {
	
    public static final String MODID = "rpg";
    public static final String VERSION = "1.0";
    
	public static final String path = "Inwaiders.redn.rpg.base";
	
	public static Random r = new Random();
	
	@Instance(MODID)
	public static base instance;
	 
	@SidedProxy(clientSide=path+".ClientProxy", serverSide=path+".CommonProxy", modId="rpg")
	public static CommonProxy proxy;
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
            MinecraftForge.EVENT_BUS.register(new SkillRenderer(Minecraft.getMinecraft()));
		
		PacketDispatcher.registerPackets();
		MinecraftForge.EVENT_BUS.register(new PlayerUpdate());
		MinecraftForge.EVENT_BUS.register(new SaveBankAndHot());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadTeam());
		MinecraftForge.EVENT_BUS.register(new SaveAndLoadTeamMain());
		SkillsStaticTable.init();
		
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			SkillsKeys.init();
		
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			FMLCommonHandler.instance().bus().register(new SkillStartetHandler());
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
      event.registerServerCommand(new TeamCommands());
    }
}
