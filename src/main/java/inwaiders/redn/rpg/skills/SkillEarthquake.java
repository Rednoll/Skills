package inwaiders.redn.rpg.skills;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.skills.EasySkillCreator.IEffect;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class SkillEarthquake extends BaseSkill
{

	public SkillEarthquake(){
		super(3);
		setTarget(Target.TARGET_ANOTHER);
		setMaxCoolDownByLevel(0, 2000);
		setMaxCoolDownByLevel(1, 3000);
		setMaxCoolDownByLevel(2, 4000);
		setMaxCoolDownByLevel(3, 7000);
		setDamageByLevel(0, 6);
		setDamageByLevel(1, 8);
		setDamageByLevel(2, 10);
		setDamageByLevel(3, 12);
		setRadiusByLevel(0, 5);
		setRadiusByLevel(1, 10);
		setRadiusByLevel(2, 12);
		setRadiusByLevel(3, 15);
	}
	
	@Override
	public void preCast(EntityPlayer ep) {
		if(ep.worldObj.getBlock((int) ep.posX, (int) ep.posY - 1, (int) ep.posZ) != Blocks.air)
		super.preCast(ep);
	}
	
	@Override
	public void skillStart(EntityPlayer ep)
	{
		if(!ep.worldObj.isRemote)
		{
			Random r = ep.worldObj.rand;
			for(int i = 0; i < 500; i++)
			{
				PacketDispatcher.sendToAllAround(new ParticlePacket("blockcrack_" + Block.getIdFromBlock(ep.worldObj.getBlock((int) ep.posX, (int) ep.posY - 1, (int) ep.posZ)) + "_0", ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, (r.nextDouble() - 0.5) * 10, 3, (r.nextDouble() - 0.5) * 10), ep, 20);
			}
		}
		EasySkillCreator.applyAOEEffect(ep, getRadius(ep), target, (caster, target) ->{
			EasySkillCreator.attack(caster, target, getDamage(ep));
			target.motionY += getDamage(ep) / 8F;
			
			switch(Core.r.nextInt(4)){
				
				case 0 :
					target.motionX += getDamage(ep) / 8F;
				break;
				
				case 1 :
					target.motionX -= getDamage(ep) / 8F;
				break;
				
				case 2 :
					target.motionZ += getDamage(ep) / 8F;
				break;
				
				case 3 :
					target.motionZ -= getDamage(ep) / 8F;
				break;
			}
			
		});
	}
	
	@Override
	public LearnPointsPrice getPrice()
	{
		return new LearnPointsPrice(this, 2);
	}

	@Override
	public ResourceLocation getTexture()
	{
		return Core.skillrlgen.generate("earthquake");
	}

	@Override
	public int getId()
	{
		return CFG.EarthquakeID;
	}
	
	@Override
	public String getName()
	{
		return "eq";
	}

}
