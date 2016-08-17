package inwaiders.redn.rpg.files;

import inwaiders.redn.rpg.files.json.PlayerJson;
import inwaiders.redn.rpg.files.json.PlayerJson.BankSkill;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SavePlayer
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{

		PlayerInfoServer b = PlayerInfoManagerServer.instance.get(e.entityPlayer);
		List<BaseSkill> skills = b.getSkills();
		PlayerJson pjson = new PlayerJson(e.entityPlayer);
		for (int i = 0; i < skills.size(); i++)
		{
			BaseSkill skill = skills.get(i);
			pjson.setBank(i, skill.getId(), skill.getLevel(), skill.getCoolDown());
		}

		for (int i = 0; i < b.hotbarSkills.length; i++)
		{
			pjson.setHotbar(i, b.hotbarSkills[i]);
		}
		pjson.setTeam(b.getTeam());
		pjson.write();
	}

	public static void load(EntityPlayer ep)
	{

		PlayerInfoServer b = PlayerInfoManagerServer.instance.get(ep);
		PlayerJson pjson = new PlayerJson(ep);
		int iCount = 0;
		for (BankSkill skill = pjson.getBank(iCount); skill != null; skill = pjson.getBank(++iCount))
		{
			try
			{
				BaseSkill s = SkillsRegistry.getSkillById(skill.id);
				s.setLevel(skill.lvl);
				s.setCoolDown(skill.cd);
				b.getSkills().add(s);
			}
			catch (Exception e)
			{
				MiscUtils.crashGame("Unbable to load skill", e);
			}
		}

		for (int i = 0; i < b.hotbarSkills.length; i++)
		{
			b.hotbarSkills[i] = pjson.getHotbar(i);
		}
		b.setTeam(pjson.getTeam());
	}

}
