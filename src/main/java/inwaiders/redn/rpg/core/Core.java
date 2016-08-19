package inwaiders.redn.rpg.core;

import java.util.Random;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import inwaiders.redn.rpg.handlers.command.CADCommands;
import inwaiders.redn.rpg.handlers.command.TeamCommands;
import inwaiders.redn.rpg.items.CAD.CadMemoryCard;
import inwaiders.redn.rpg.proxy.CommonProxy;
import inwaiders.redn.rpg.utils.LocaleKeyGenerator;
import inwaiders.redn.rpg.utils.ResourceLocGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

@Mod(modid = Core.MODID, version = Core.VERSION, name = Core.NAME)
public class Core
{

	public static final String MODID = "rpg";
	public static final String VERSION = "1.0";
	public static final String NAME = "RPG";
	public static final String path = "inwaiders.redn.rpg.proxy";
	public static final ResourceLocGenerator guirlgen = new ResourceLocGenerator(MODID, "textures/gui", ".png");
	public static final ResourceLocGenerator skillrlgen = new ResourceLocGenerator(MODID, "textures/sicons/", ".png");
	public static final ResourceLocGenerator modelrlgen = new ResourceLocGenerator(MODID, "model", ".obj");
	public static final ResourceLocGenerator modeltexrlgen = new ResourceLocGenerator(MODID, "model/texture", ".png");
	public static final LocaleKeyGenerator skilllkgen = new LocaleKeyGenerator("rpg.skill.", ".name");
	public static final LocaleKeyGenerator skilldescgen = new LocaleKeyGenerator("rpg.skill.", ".desc.name");
	public static Random r = new Random();
	
	public static CreativeTabs tab = new CreativeTabs("RPG")
	{
		
		@Override
		public Item getTabIconItem()
		{
			return Items.apple;
		}
	};
	@Instance(MODID)
	public static Core instance;

	@SidedProxy(clientSide = path + ".ClientProxy", serverSide = path + ".CommonProxy", modId = "rpg")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit(e);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new TeamCommands());
		event.registerServerCommand(new CADCommands());
		
	}
}
