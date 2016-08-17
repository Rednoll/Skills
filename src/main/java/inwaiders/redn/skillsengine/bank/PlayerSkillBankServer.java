package inwaiders.redn.skillsengine.bank;

import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PlayerSkillBankServer extends PlayerSkillBankClient
{
	int iCount = 0;
	boolean loaded = false;

	public PlayerSkillBankServer(EntityPlayer ep)
	{
		super(ep);
	}

	@Override
	public void update()
	{
		super.update();
		sync();
		load();
	}

	public void sync()
	{
		if (iCount >= 30)
		{
			PacketDispatcher.sendTo(new SyncStoCProviders(ep), (EntityPlayerMP) ep);
			iCount = 0;
		}
		iCount++;
	}

	public void load()
	{
		if (!loaded)
		{
			SaveBankAndHot.load(ep);
			loaded = true;
		}
	}
	
	@Override
	public void resetPlayer()
	{
		if(ep == null)
		{
			ep = (EntityPlayer) MinecraftServer.getServer().getConfigurationManager().getPlayerList(playername).get(0);
		}
	}
}
