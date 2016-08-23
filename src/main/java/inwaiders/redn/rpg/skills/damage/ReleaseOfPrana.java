package inwaiders.redn.rpg.skills.damage;

import java.util.Random;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.skills.EasySkillCreator;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ReleaseOfPrana extends BaseSkill {

	public ReleaseOfPrana() {
		super(3);
		setTarget(Target.TARGET_ANOTHER);

		init();
	}

	public void init() {

		setMaxCast(0, 50);
		setMaxCast(1, 70);
		setMaxCast(2, 110);
		setMaxCast(3, 150);

		setDamageByLevel(0, 6);
		setDamageByLevel(1, 8);
		setDamageByLevel(2, 10);
		setDamageByLevel(3, 14);

		setRadiusByLevel(0, 8);
		setRadiusByLevel(1, 12);
		setRadiusByLevel(2, 16);
		setRadiusByLevel(3, 18);

		setMaxCoolDownByLevel(0, 1800);
		setMaxCoolDownByLevel(1, 2700);
		setMaxCoolDownByLevel(2, 3000);
		setMaxCoolDownByLevel(3, 3300);
	}

	@Override
	public void skillStart(EntityPlayer ep) {
		if (!ep.worldObj.isRemote) {
			Random r = ep.worldObj.rand;
			for (int i = 0; i < 500; i++) {
				PacketDispatcher.sendToAllAround(new ParticlePacket("net.minecraft.client.particle.EntitySpellParticleFX", 0.93F, 0.15F, 0F, ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, (r.nextDouble() - 0.5) * 20, 3, (r.nextDouble() - 0.5) * 20), ep, 20);
			}
		}
		
		EasySkillCreator.attackAOE(ep, getRadius(ep), getDamage(ep), getTarget());
		
	}

	@Override
	public void castUpdate(EntityPlayer ep) {
		
		if (!ep.worldObj.isRemote) {
			Random r = ep.worldObj.rand;
			PacketDispatcher.sendToAllAround(new ParticlePacket("net.minecraft.client.particle.EntitySpellParticleFX", 0.93F, 0.15F, 0F, ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, (r.nextDouble() - 0.5) * 20, 3, (r.nextDouble() - 0.5) * 20), ep, 20);
		}
	}


	@Override
	public ResourceLocation getTexture() {
		return Core.skillrlgen.generate("releaseOfPrana");
	}

	@Override
	public LearnPointsPrice getPrice() {
		return new LearnPointsPrice(this, 3);
	}

	@Override
	public String getName() {
		return "ReleaseOfPrana";
	}
}
