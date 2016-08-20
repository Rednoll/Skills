package inwaiders.redn.rpg.storage.server;

import inwaiders.redn.rpg.files.SaveAndLoadPlayer;
import inwaiders.redn.rpg.files.json.PlayerJson.BankSkill;
import inwaiders.redn.rpg.packet.sync.PlayerInfoSync;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PlayerInfoServer extends PlayerInfoClient {

	public PlayerInfoServer(EntityPlayer ep) {
		super(ep);
	}
	
	int iCount = 0;
	boolean loaded = false;
	
	@Override
	public void update(EntityPlayer ep)
	{
		super.update(ep);
		sync();
		load(ep);
	}

	public void sync()
	{
		if (iCount >= 30)
		{
			PacketDispatcher.sendTo(new PlayerInfoSync(ep), (EntityPlayerMP) ep);
			iCount = 0;
		}
		iCount++;
	}

	public void load(EntityPlayer ep)
	{
		if (!loaded)
		{
			SaveAndLoadPlayer.load(ep);
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
