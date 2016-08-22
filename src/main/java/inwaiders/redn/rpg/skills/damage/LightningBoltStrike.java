package inwaiders.redn.rpg.skills.damage;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.LightningPacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.MiscUtils;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class LightningBoltStrike extends BaseSkill{

	
	public LightningBoltStrike(){
		super(3);
		setTarget(Target.TARGET_ANOTHER);
		setMaxCoolDownByLevel(0, 2000);
		setMaxCoolDownByLevel(1, 3000);
		setMaxCoolDownByLevel(2, 4000);
		setMaxCoolDownByLevel(3, 7000);
		setDamageByLevel(0, 8);
		setDamageByLevel(1, 10);
		setDamageByLevel(2, 12);
		setDamageByLevel(3, 14);	
		setRadiusByLevel(0, 30);
		setRadiusByLevel(1, 60);
		setRadiusByLevel(2, 90);
		setRadiusByLevel(3, 120);
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
	}

	@Override
	public String getName() {
		
		return "LightBoltStrike";
	}

	@Override
	public LearnPointsPrice getPrice() {
		
		return new LearnPointsPrice(this, 4);
	}

	@Override
	public ResourceLocation getTexture() {
		
		return Core.skillrlgen.generate("lightBoltStrike");
	}

}
