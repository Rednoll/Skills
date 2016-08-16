package Inwaiders.redn.teamengine.teams;

import java.io.FileNotFoundException;
import java.io.IOException;
import Inwaiders.redn.rpg.base.LAS;
import Inwaiders.redn.rpg.base.json.PlayerJson;
import Inwaiders.redn.skillsengine.bank.GeterBStoP;
import Inwaiders.redn.skillsengine.bank.SkillsBankServerProvider;
import Inwaiders.redn.skillsengine.bank.SkillsStaticTable;
import Inwaiders.redn.skillsengine.bank.SuperPuperZipWinRar;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import Inwaiders.redn.skillsengine.hotbar.GeterHtoP;
import Inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveAndLoadTeam
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{
		TeamEngineServerProvider te = GeterTStoP.getParam(e.entityPlayer.getEntityId());
		PlayerJson pjson = new PlayerJson(e.entityPlayer);
		pjson.setTeam(te.getTeam());
		pjson.write();
	}

	public static void load(EntityPlayer ep)
	{

		TeamEngineServerProvider te = GeterTStoP.getParam(ep.getEntityId());
		PlayerJson pjson = new PlayerJson(ep);
		String team = pjson.getTeam();
		if (team != "")
		{
			te.setTeam(team);
		}
		GeterTStoP.setParamToEntity(ep.getEntityId(), te);
	}

}
