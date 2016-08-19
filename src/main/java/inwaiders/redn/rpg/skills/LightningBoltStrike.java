package inwaiders.redn.rpg.skills;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.LightningPacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.MiscUtils;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class LightningBoltStrike extends BaseSkill{

	
	public LightningBoltStrike(){
		setLevel(1);
		setTarget(Target.TARGET_ANOTHER);
		setMaxCoolDownByLevel(1, 2000);
		setMaxCoolDownByLevel(2, 3000);
		setMaxCoolDownByLevel(3, 4000);
		setMaxCoolDownByLevel(4, 7000);
		setDamageByLevel(1, 8);
		setDamageByLevel(2, 10);
		setDamageByLevel(3, 12);
		setDamageByLevel(4, 14);	
		setRadiusByLevel(1, 30);
		setRadiusByLevel(2, 60);
		setRadiusByLevel(3, 90);
		setRadiusByLevel(4, 120);
	}
	
	
	@Override
	public void skillStart(EntityPlayer ep){
		
		MovingObjectPosition object = MiscUtils.getPlayerTarget(ep, getRadius(ep), 0);
		
		if(object == null) return;
		if(!ep.worldObj.isRemote)
		{
			ep.worldObj.spawnEntityInWorld(new EntityLightningBolt(ep.worldObj, object.blockX, object.blockY+1, object.blockZ));
			PacketDispatcher.sendToAllAround(new LightningPacket(object.blockX, object.blockY+1, object.blockZ), ep.worldObj.provider.dimensionId, object.blockX, object.blockY+1, object.blockZ, getRadius(ep) * 2);
		}
		
		//System.out.println("Coords : X = "+MiscUtils.getPlayerTarget(ep, 30, 0).blockX+", Y = "+MiscUtils.getPlayerTarget(ep, 30, 0).blockY+", Z ="+MiscUtils.getPlayerTarget(ep, 30, 0).blockZ);
	}
	
	@Override
	public int getId() {
		return CFG.LightBoltStrikeID;
	}

	@Override
	public String getName() {
		
		return "LightBoltStrike";
	}

	@Override
	public LearnPointsPrice getPrice() {
		
		return new LearnPointsPrice(this, 2);
	}

	@Override
	public ResourceLocation getTexture() {
		
		return Core.skillrlgen.generate("lightBoltStrike");
	}

}
