package inwaiders.redn.rpg.base;

import inwaiders.redn.rpg.base.utils.ResourceLocGenerator;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.skillsengine.bank.SaveBankAndHot;
import inwaiders.redn.skillsengine.bank.SkillsRegistry;
import inwaiders.redn.skillsengine.hotbar.SkillRenderer;
import inwaiders.redn.skillsengine.hotbar.SkillStartetHandler;
import inwaiders.redn.skillsengine.hotbar.SkillsKeys;
import inwaiders.redn.teamengine.teams.SaveAndLoadTeam;
import inwaiders.redn.teamengine.teams.SaveAndLoadTeamMain;
import inwaiders.redn.teamengine.teams.TeamCommands;
import java.util.Random;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Core.MODID, version = Core.VERSION, name = Core.NAME)
public class Core
{

	public static final String MODID = "rpg";
	public static final String VERSION = "1.0";
	public static final String NAME = "RPG";
	public static final String path = "inwaiders.redn.rpg.base";
	public static final ResourceLocGenerator skillrlgen = new ResourceLocGenerator(MODID, "textures/sicons/", ".png");
	public static Random r = new Random();

	@Instance(MODID)
	public static Core instance;

	@SidedProxy(clientSide = path + ".ClientProxy", serverSide = path + ".CommonProxy", modId = "rpg")
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new TeamCommands());
	}
}
