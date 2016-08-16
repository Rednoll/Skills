package Inwaiders.redn.skillsengine.bank;

import java.io.FileNotFoundException;
import java.io.IOException;
import Inwaiders.redn.rpg.base.LAS;
import Inwaiders.redn.rpg.base.json.PlayerJson;
import Inwaiders.redn.rpg.base.json.PlayerJson.BankSkill;
import Inwaiders.redn.rpg.base.utils.MiscUtils;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.hotbar.GeterHtoP;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveBankAndHot
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{

		SkillsBankServerProvider b = GeterBStoP.getParam(e.entityPlayer);
		SkillsHotBar h = GeterHtoP.getParam(e.entityPlayer.getEntityId());
		PlayerJson pjson = new PlayerJson(e.entityPlayer);
		for (int i = 0; i < b.skills.size(); i++)
		{
			BaseSkill skill = b.skills.get(i);
			pjson.setBank(i, skill.getId(), skill.getLevel(), skill.getCoolDown());
		}

		for (int i = 0; i < h.skills.length; i++)
		{
			pjson.setHotbar(i, h.skills[i]);
		}
		pjson.write();
	}

	public static void load(EntityPlayer ep)
	{

		SkillsBankServerProvider b = GeterBStoP.getParam(ep);
		SkillsHotBar h = GeterHtoP.getParam(ep.getEntityId());
		PlayerJson pjson = new PlayerJson(ep);
		int iCount = 0;
		for (BankSkill skill = pjson.getBank(iCount); skill != null; skill = pjson.getBank(++iCount))
		{
			try
			{
				BaseSkill s = SkillsStaticTable.getSkillById(skill.id);
				s.setLevel(skill.lvl);
				s.setCoolDown(skill.cd);
				b.skills.add(s);
				System.out.println("Loaded bank skill - " + s.getId());
			}
			catch (Exception e)
			{
				MiscUtils.crashGame("Unbable to load skill", e);
			}
		}

		for (int i = 0; i < h.skills.length; i++)
		{
			h.skills[i] = pjson.getHotbar(i);
		}

	}

}
