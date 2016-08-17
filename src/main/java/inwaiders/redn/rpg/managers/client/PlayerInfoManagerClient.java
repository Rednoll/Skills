package inwaiders.redn.rpg.managers.client;

import net.minecraft.entity.player.EntityPlayer;
import inwaiders.redn.rpg.managers.ManagerBase;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;

public class PlayerInfoManagerClient extends ManagerBase<PlayerInfoClient> {

	public static final PlayerInfoManagerClient instance = new PlayerInfoManagerClient();
	
	@Override
	protected PlayerInfoClient getDefaultValue(EntityPlayer key) {
		return new PlayerInfoClient(key);
	}

}
