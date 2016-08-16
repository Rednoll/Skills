package Inwaiders.redn.skillsengine.learn;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class LearnPointsServerProvider extends LearnPointsClientProvider{

	private boolean loadet = false;
	private int iCount = 0;
	
	public void updateEngine(EntityPlayer ep){
		sync(ep);
	}
	
	public void sync(EntityPlayer ep){
		
		if(iCount >= 40){
			send(ep);
			iCount = 0;
		}
		
		iCount++;
	}
	
	public void send(EntityPlayer ep){
		PacketDispatcher.sendTo(new SyncLearnPoints(ep), (EntityPlayerMP) ep);
	}
}
