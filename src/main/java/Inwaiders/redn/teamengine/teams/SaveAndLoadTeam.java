package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.base.LAS;
import inwaiders.redn.rpg.base.json.PlayerJson;
import inwaiders.redn.skillsengine.bank.BankManagerServer;
import inwaiders.redn.skillsengine.bank.PlayerSkillBankServer;
import inwaiders.redn.skillsengine.bank.SkillsRegistry;
import inwaiders.redn.skillsengine.bank.SuperPuperZipWinRar;
import inwaiders.redn.skillsengine.examples.BaseSkill;
import inwaiders.redn.skillsengine.hotbar.SkillsHotBarManager;
import inwaiders.redn.skillsengine.hotbar.SkillsHotBar;
import java.io.FileNotFoundException;
import java.io.IOException;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class SaveAndLoadTeam
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{
		PlayerTeamServer te = PlayerTeamManagerServer.instance.get(e.entityPlayer);
		PlayerJson pjson = new PlayerJson(e.entityPlayer);
		pjson.setTeam(te.getTeam());
		pjson.write();
	}

	public static void load(EntityPlayer ep)
	{

		PlayerTeamServer te = PlayerTeamManagerServer.instance.get(ep);
		PlayerJson pjson = new PlayerJson(ep);
		String team = pjson.getTeam();
		if (team != "")
		{
			te.setTeam(team);
		}
		PlayerTeamManagerServer.instance.set(ep, te);
	}

}
