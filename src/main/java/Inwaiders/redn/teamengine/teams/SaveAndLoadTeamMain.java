package Inwaiders.redn.teamengine.teams;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import Inwaiders.redn.rpg.base.LAS;
import Inwaiders.redn.rpg.base.json.TeamJson;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SaveAndLoadTeamMain
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{
		for (int i = 0; i < GeterTMStoP.par.size(); i++)
		{
			ComposerTeamMainClassServerProvider s1 = (ComposerTeamMainClassServerProvider) GeterTMStoP.par.get(i);
			TeamMainClassServerProvider s = s1.getParam();
			TeamJson tjson = new TeamJson(s.getOwnerName(), e.entityPlayer.worldObj.getSaveHandler().getWorldDirectory());
			tjson.setMembers(s.players);
			tjson.setOwner(s.getOwnerName());
			tjson.write();
		}

	}

	public static void load(EntityPlayer ep, String name, TeamMainClassServerProvider s)
	{
		TeamJson tjson = new TeamJson(name, ep.worldObj.getSaveHandler().getWorldDirectory());
		s.setNickOwner(tjson.getOwner());
		String[] members = tjson.getMembers();
		for (String mname : members)
		{
			s.joinToAcces(ep.worldObj.getPlayerEntityByName(mname));
		}
		GeterTMStoP.setParamToEntity(name, s);
	}

}
