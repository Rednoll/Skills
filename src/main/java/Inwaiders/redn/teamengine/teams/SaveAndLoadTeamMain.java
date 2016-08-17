package inwaiders.redn.teamengine.teams;

import inwaiders.redn.rpg.base.LAS;
import inwaiders.redn.rpg.base.json.TeamJson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SaveAndLoadTeamMain
{

	@SubscribeEvent
	public void save(SaveToFile e)
	{
		Collection<TeamServer> tmp = TeamManagerServer.instance.getValues();
		TeamServer[] teams = tmp.toArray(new TeamServer[tmp.size()]);
		for (TeamServer team : teams)
		{
			TeamJson tjson = new TeamJson(team.getTeamName(), e.entityPlayer.worldObj.getSaveHandler().getWorldDirectory());
			tjson.setName(team.getTeamName());
			tjson.setMembers(team.players);
			tjson.setOwner(team.getOwnerName());
			tjson.write();
		}

	}

	public static void load(EntityPlayer ep, String name, TeamServer s)
	{
		TeamJson tjson = new TeamJson(name, ep.worldObj.getSaveHandler().getWorldDirectory());
		s.setNickOwner(tjson.getOwner());
		String[] members = tjson.getMembers();
		for (String mname : members)
		{
			s.joinToAcces(ep.worldObj.getPlayerEntityByName(mname));
		}
		TeamManagerServer.instance.set(name, s);
	}

}
