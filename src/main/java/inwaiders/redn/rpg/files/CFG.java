package inwaiders.redn.rpg.files;

import net.minecraftforge.common.config.Configuration;

public class CFG
{
	private static Configuration cfg;
	
	//Misc
	public static int xpRadius;
	public static boolean forceJson;
	
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
}
