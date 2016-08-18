package inwaiders.redn.rpg.files;

import inwaiders.redn.rpg.files.json.PlayerJson;
import inwaiders.redn.rpg.files.json.PlayerJson.BankSkill;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SaveAndLoadPlayer
{
	@SubscribeEvent
	public void save(SaveToFile e)
	{

		PlayerInfoServer b = PlayerInfoManagerServer.instance.get(e.entityPlayer);
		
		HashMap<Integer, BaseSkill> skills = b.getSkills();
		PlayerJson pjson = new PlayerJson(e.entityPlayer, true);
		skills.forEach((id, skill) -> pjson.addBank(skill.getId(), skill.getLevel(), skill.getCoolDown()));
		for (int i = 0; i < b.hotbarSkills.length; i++)
		{
			//System.out.println(i + "/" + b.getSkillIdByPos(i));
			pjson.setHotbar(i, b.getSkillIdByPos(i));
		}
		pjson.setTeam(b.getTeam());
		pjson.setXP(b.getXp());
		pjson.setXPForNextLevel(b.getXpForNextLevel());
		pjson.setLearnPoints(b.getLearnPoints());
		pjson.write();
	}

	public static void load(EntityPlayer ep)
	{

		PlayerInfoServer b = PlayerInfoManagerServer.instance.get(ep);
		PlayerJson pjson = new PlayerJson(ep);
		b.setSkills(new HashMap<Integer, BaseSkill>());
		int iCount = 0;
		for (BankSkill skill = pjson.getBank(iCount); skill != null; skill = pjson.getBank(++iCount))
		{
			try
			{
				BaseSkill s = SkillsRegistry.getSkillById(skill.id);
				s.setLevel(skill.lvl);
				s.setCoolDown(skill.cd);
				b.getSkills().put(s.getId(), s);
			}
			catch (Exception e)
			{
				MiscUtils.crashGame("Unbable to load skill", e);
			}
		}
		for (int i = 0; i < b.hotbarSkills.length; i++)
		{
			b.setSkillIdByPos(i, pjson.getHotbar(i));
		}
		b.setTeam(pjson.getTeam());
		b.setXp(pjson.getXP());
		b.setXpForNextLevel(pjson.getXPForNextLevel());
		b.setLearnPoints(pjson.getLearnPoints());
	}

}
