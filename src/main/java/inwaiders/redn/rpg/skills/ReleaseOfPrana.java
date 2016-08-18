package inwaiders.redn.rpg.skills;

import java.util.Random;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.utils.Targeting.Target;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ReleaseOfPrana extends BaseSkill {

	public ReleaseOfPrana() {

		setLevel(1);

		setTarget(Target.TARGET_ANOTHER);

		init();
	}

	public void init() {

		setMaxCast(1, 50);
		setMaxCast(2, 70);
		setMaxCast(3, 110);
		setMaxCast(4, 150);

		setDamageByLevel(1, 6);
		setDamageByLevel(2, 8);
		setDamageByLevel(3, 10);
		setDamageByLevel(4, 14);

		setRadiusByLevel(1, 8);
		setRadiusByLevel(2, 12);
		setRadiusByLevel(3, 16);
		setRadiusByLevel(4, 18);

		setMaxCoolDownByLevel(1, 1800);
		setMaxCoolDownByLevel(2, 2700);
	}

	@Override
	public void skillStart(EntityPlayer ep) {
		if (!ep.worldObj.isRemote) {
			Random r = ep.worldObj.rand;
			for (int i = 0; i < 500; i++) {
				PacketDispatcher.sendToAllAround(new ParticlePacket(EntitySpellParticleFX.class, 0.93F, 0.15F, 0F, ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, (r.nextDouble() - 0.5) * 20, 3, (r.nextDouble() - 0.5) * 20), ep, 20);
			}
		}
		EasySkillCreator.attackAOE(ep, getRadiusByLevel(getLevel()), getDamageByLevel(getLevel()), getTarget());

	}

	@Override
	public void castUpdate(EntityPlayer ep) {
		if (!ep.worldObj.isRemote) {
			Random r = ep.worldObj.rand;
				PacketDispatcher.sendToAllAround(new ParticlePacket(EntitySpellParticleFX.class, 0.93F, 0.15F, 0F, ep.posX + r.nextDouble() - 0.5, ep.posY, ep.posZ + r.nextDouble() - 0.5, 0, 5, 0), ep, 20);
		}
	}

	@Override
	public void whileUpdate(EntityPlayer ep) {

	}

	@Override
	public ResourceLocation getTexture() {
		return Core.skillrlgen.generate("releaseOfPrana");
	}

	@Override
	public LearnPointsPrice getPrice() {
		return new LearnPointsPrice(this, 500);
	}

	@Override
	public int getId() {
		return CFG.ReleaseOfPranaID;
	}

	@Override
	public String getName() {
		return "rop";
	}
}
