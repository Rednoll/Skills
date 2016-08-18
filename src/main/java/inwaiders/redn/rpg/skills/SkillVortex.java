package inwaiders.redn.rpg.skills;

import java.util.Random;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillVortex extends BaseSkill {

	public SkillVortex() {
		setDamageByLevel(1, 2);
		setMaxCoolDownByLevel(1, 100);
		setRadiusByLevel(1, 6);
	}

	@Override
	public void skillStart(EntityPlayer ep) {
		if(!ep.worldObj.isRemote)
		{
			Random r = ep.worldObj.rand;
			for(int i = 0; i < 200; i++)
			{
				PacketDispatcher.sendToAllAround(new ParticlePacket(EntitySpellParticleFX.class, 0.2F, 0.153F, 0.82F, ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, 0D, 10D, 0D), ep, 20);
			}
		}
		EasySkillCreator.applyAOEEffect(ep, getRadiusByLevel(getLevel()), Target.TARGET_ANOTHER, (caster, target) -> {
			double x = target.posX < caster.posX ? 0.75 * getLevel() : target.posX > caster.posX ? -0.75 * getLevel() : 0;
			double y = target.posY < caster.posY ? 0.75 * getLevel() : target.posZ > caster.posY ? -0.75 * getLevel() : 0;
			double z = target.posZ < caster.posZ ? 0.75 * getLevel() : target.posZ > caster.posZ ? -0.75 * getLevel() : 0;
			target.motionX += x;
			target.motionY += y;
			target.motionZ += z;
		});
	}

	@Override
	public int getId() {
		return CFG.VoretexID;
	}

	@Override
	public String getName() {
		return "vortex";
	}

	@Override
	public LearnPointsPrice getPrice() {
		return new LearnPointsPrice(this, 50);
	}

	@Override
	public ResourceLocation getTexture() {
		return Core.skillrlgen.generate("vortex");
	}

}
