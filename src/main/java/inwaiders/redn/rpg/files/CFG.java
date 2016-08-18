package inwaiders.redn.rpg.files;

import net.minecraftforge.common.config.Configuration;

public class CFG
{
	private static Configuration cfg;
	public static int BackJumpID;
	public static int ReleaseOfPranaID;
	public static int EarthquakeID;
	public static int LightBoltStrikeID;
	public static int VoretexID;
	public static int SwapID;
	
	public static void init(Configuration cfg)
	{
		CFG.cfg = cfg;
		cfg.load();
		BackJumpID = getSkillId("BackJump", 1);
		ReleaseOfPranaID = getSkillId("ReleaseOfPrana", 2);
		EarthquakeID = getSkillId("Earthquake", 3);
		LightBoltStrikeID = getSkillId("LightBoltStrike", 4);
		VoretexID = getSkillId("Vortex", 5);
		SwapID = getSkillId("Swap", 6);
		cfg.save();
		CFG.cfg = null;
	}
	
	private static int getSkillId(String name, int defaultValue)
	{
		return cfg.getInt(name, "SkillIDs", defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
	}
}
