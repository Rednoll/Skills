package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.skills.LightningBoltStrike;
import inwaiders.redn.rpg.skills.ReleaseOfPrana;
import inwaiders.redn.rpg.skills.SkillBackJump;
import inwaiders.redn.rpg.skills.SkillEarthquake;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLLog;

public class SkillsRegistry {

	private static HashMap<Integer, Class<? extends BaseSkill>> allSkills = new HashMap<Integer, Class<? extends BaseSkill>>();
	
	public static void registerSkill(int id, Class<? extends BaseSkill> base){
		allSkills.put(id, base);
	}
	public static void registerSkill(BaseSkill skill)
	{
		if(allSkills.containsKey(skill.getId()))
		{
			FMLLog.warning("Skill with id " + skill.getId() + " alredy registered, skipping");
			return;
		}
		registerSkill(skill.getId(), skill.getClass());
	}
	
	/*private static Class<?> getSkill(int i){
		return allSkills.get(i);
	}*/
	
	public static int getSize(){
		return allSkills.size();
	}
	
	public static BaseSkill getSkillById(int id){
		
		try
		{
			return allSkills.get(id).newInstance();
		}
		catch (Exception e)
		{
			MiscUtils.crashGame("Unable to get skill form registry, contact mod author", e);
		}
		return null;
	}
	
	public static void init(){
		
		registerSkill(new SkillBackJump());
		registerSkill(new ReleaseOfPrana());
		registerSkill(new SkillEarthquake());
		registerSkill(new LightningBoltStrike());
	}
	
	public static boolean learnSkill(PlayerInfoServer p, int id)
	{
		LearnPointsPrice c = getSkillById(id).getPrice();
		if (p.canLearn(c.getPrice()))
		{
			p.learnSkill(id);
			p.learn(c.getPrice());
			return true;
		}
		return false;

	}
	
	public static boolean learnSkill(EntityPlayer p, int id)
	{
		return learnSkill(PlayerInfoManagerServer.instance.get(p), id);
	}
}
