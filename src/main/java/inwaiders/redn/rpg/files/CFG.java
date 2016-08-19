package inwaiders.redn.rpg.files;

import net.minecraftforge.common.config.Configuration;

public class CFG
{
	//Internal
	private static int skillid = 1;
	private static int itemskillid = 1;
	private static Configuration cfg;
	
	//Skills
	public static int BackJumpID;
	public static int ReleaseOfPranaID;
	public static int EarthquakeID;
	public static int LightBoltStrikeID;
	public static int VoretexID;
	public static int SwapID;
	public static int VipeID;
	
	//ItemSkills
	public static int cooldowdecID;
	public static int InstantShieldID;
	
	public static void init(Configuration cfg)
	{
		CFG.cfg = cfg;
		cfg.load();
		initSkills();
		initItemSkills();
		cfg.save();
		CFG.cfg = null;
	}
	
	private static void initSkills()
	{
		BackJumpID = getSkillId("BackJump");
		ReleaseOfPranaID = getSkillId("ReleaseOfPrana");
		EarthquakeID = getSkillId("Earthquake");
		LightBoltStrikeID = getSkillId("LightBoltStrike");
		VoretexID = getSkillId("Vortex");
		SwapID = getSkillId("Swap");
		VipeID = getSkillId("VipeStrike");
	}
	
	private static void initItemSkills()
	{
		cooldowdecID = getItemSkillId("CooldownDecreaser");
		InstantShieldID = getItemSkillId("InstantShield");
	}
	
	private static int getSkillId(String name)
	{
		return cfg.getInt(name, "SkillIDs", skillid++, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
	}
	
	private static int getItemSkillId(String name)
	{
		return cfg.getInt(name, "ItemSkillIds", itemskillid++, Integer.MIN_VALUE, Integer.MAX_VALUE, "");
	}
}
