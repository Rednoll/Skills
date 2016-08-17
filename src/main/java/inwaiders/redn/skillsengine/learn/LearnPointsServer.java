package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class LearnPointsServer extends LearnPointsClient{

	private boolean loadet = false;
	private int iCount = 0;
	
	public void update(EntityPlayer ep){
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
