package inwaiders.redn.rpg.managers.server;

import net.minecraft.entity.player.EntityPlayer;
import inwaiders.redn.rpg.managers.ManagerBase;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;

public class PlayerInfoManagerServer extends ManagerBase<PlayerInfoServer>{

	public static final PlayerInfoManagerServer instance = new PlayerInfoManagerServer();
	
	@Override
	protected PlayerInfoServer getDefaultValue(EntityPlayer key) {
		return new PlayerInfoServer(key);
	}

}
