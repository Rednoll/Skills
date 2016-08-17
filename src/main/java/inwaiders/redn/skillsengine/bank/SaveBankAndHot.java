package inwaiders.redn.skillsengine.bank;

import inwaiders.redn.rpg.base.LAS;
import inwaiders.redn.rpg.base.json.PlayerJson;
import inwaiders.redn.rpg.base.json.PlayerJson.BankSkill;
import inwaiders.redn.rpg.base.utils.MiscUtils;
import inwaiders.redn.skillsengine.examples.BaseSkill;
import inwaiders.redn.skillsengine.hotbar.SkillsHotBarManager;
import inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import java.io.FileNotFoundException;
import java.io.IOException;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveBankAndHot
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{

		PlayerSkillBankServer b = BankManagerServer.instance.get(e.entityPlayer);
		SkillsHotBar h = SkillsHotBarManager.instance.get(e.entityPlayer);
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

		PlayerSkillBankServer b = BankManagerServer.instance.get(ep);
		SkillsHotBar h = SkillsHotBarManager.instance.get(ep);
		PlayerJson pjson = new PlayerJson(ep);
		int iCount = 0;
		for (BankSkill skill = pjson.getBank(iCount); skill != null; skill = pjson.getBank(++iCount))
		{
			try
			{
				BaseSkill s = SkillsRegistry.getSkillById(skill.id);
				s.setLevel(skill.lvl);
				s.setCoolDown(skill.cd);
				b.skills.add(s);
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
