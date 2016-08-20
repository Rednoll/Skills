package inwaiders.redn.rpg.files;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;

public class CFG
{
	private static Configuration cfg;
	
	//Misc
	public static int xpRadius;
	public static boolean forceJson;
	
	//Integration
	public static boolean baubles;
	
	public static void init(Configuration cfg)
	{
		CFG.cfg = cfg;
		cfg.load();
		initMisc();
		cfg.save();
		CFG.cfg = null;
	}
	
	private static void initMisc()
	{
		xpRadius = cfg.getInt("xpRadius", "Misc", 15, 1, Integer.MAX_VALUE, "When mob killed players inside this radius will get xp");
		forceJson = cfg.getBoolean("forceJson", "Misc", false, "Use json file instead of player NBT for storing skills, used for debug. WARNIG cahngin this will reset your skills");
	}
	
	private static void initInteg()
	{
		baubles = getEnableIntegration("Baubles", "Enable baubles integration (Allow using item skills in baubles)");
	}
	
	private static boolean getEnableIntegration(String modid, String comment)
	{
		return Loader.isModLoaded(modid) && cfg.getBoolean(modid, "Integration", true, comment);
	}
}
