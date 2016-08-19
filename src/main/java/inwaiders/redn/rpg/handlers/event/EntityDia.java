package inwaiders.redn.rpg.handlers.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;
import inwaiders.redn.rpg.storage.server.PlayerInfoServer;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EntityDia {

	@SubscribeEvent
	public void dia(LivingDeathEvent e){
		
		PlayerInfoServer ep = PlayerInfoManagerServer.instance.get(e.source.)
		
		for(int i = 0;i<)
		if(e.entityLiving instanceof EntityZombie)
	}
}
