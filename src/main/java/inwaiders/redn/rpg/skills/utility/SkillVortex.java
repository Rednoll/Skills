package inwaiders.redn.rpg.skills.utility;

import java.util.Random;




import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.skills.EasySkillCreator;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.VectorUtils;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;import net.minecraft.util.Vec3;


public class SkillVortex extends BaseSkill {

	public SkillVortex() {
		super(4);
		setDamageByLevel(0, 2);
		setMaxCoolDownByLevel(0, 100);
		setRadiusByLevel(0, 6);
	}

	@Override
	public void skillStart(EntityPlayer ep) {
		if(!ep.worldObj.isRemote)
		{
			Random r = ep.worldObj.rand;
			for(int i = 0; i < 200; i++)
			{
				PacketDispatcher.sendToAllAround(new ParticlePacket("net.minecraft.client.particle.EntitySpellParticleFX", 0.2F, 0.153F, 0.82F, ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, 0D, 10D, 0D), ep, 20);
			}
		}
		EasySkillCreator.applyAOEEffect(ep, getRadius(ep), Target.TARGET_ANOTHER, (caster, target) -> {
			Vec3 rev = VectorUtils.negate(Vec3.createVectorHelper(target.posX - caster.posX, target.posY - caster.posY, target.posZ - caster.posZ).normalize());
			target.motionX += rev.xCoord * (0.5 * (getLevel() + 1));
			target.motionY += rev.yCoord * (0.5 * (getLevel() + 1));
			target.motionZ += rev.zCoord * (0.5 * (getLevel() + 1));
		});
	}

	@Override
	public String getName() {
		return "Vortex";
	}

	@Override
	public LearnPointsPrice getPrice() {
		return new LearnPointsPrice(this, 2);
	}

	@Override
	public ResourceLocation getTexture() {
		return Core.skillrlgen.generate("vortex");
	}

}
