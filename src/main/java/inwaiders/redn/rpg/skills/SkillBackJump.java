package inwaiders.redn.rpg.skills;

import java.util.Random;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillBackJump extends BaseSkill {

	public SkillBackJump() {
		super(5);
		init();
	}

	@Override
	public void skillStart(EntityPlayer ep) {
		
		float cosX = (float) Math.cos(Math.toRadians(ep.rotationYaw));
		float sinX = (float) Math.sin(Math.toRadians(ep.rotationYaw));

		float cosY = (float) Math.cos(Math.toRadians(ep.rotationPitch));
		float sinY = (float) Math.sin(Math.toRadians(ep.rotationPitch));

		float speed = getLevel() + 1;
		float wertSpeed = (getLevel() + 1) / 1.5F;

		ep.motionZ -= cosX * speed;
		ep.motionX += sinX * speed;
		ep.motionY += wertSpeed / 2F;
		if (!ep.worldObj.isRemote) {
			Random r = ep.worldObj.rand;
			for (int i = 0; i < 40; i++) {
				PacketDispatcher.sendToAllAround(new ParticlePacket(EntitySpellParticleFX.class, 0.56F, 0.87F, 0.78F, ep.posX + r.nextDouble() - 0.5, ep.posY - 1.9, ep.posZ + r.nextDouble() - 0.5, 0, 3, 0), ep, 20);
			}
		}
	}

	public void init() {

		setMaxCast(0, 0);
		setMaxCast(1, 0);
		setMaxCast(2, 0);
		setMaxCast(3, 0);

		setMaxCoolDownByLevel(0, 1800);
		setMaxCoolDownByLevel(1, 2700);
		setMaxCoolDownByLevel(2, 3000);
		setMaxCoolDownByLevel(3, 3300);
	}

	@Override
	public void whileUpdate(EntityPlayer ep) {

	}

	@Override
	public ResourceLocation getTexture() {
		return Core.skillrlgen.generate("backJump");
	}

	@Override
	public LearnPointsPrice getPrice() {
		return new LearnPointsPrice(this, 200);
	}

	@Override
	public int getId() {
		return CFG.BackJumpID;
	}

	@Override
	public String getName() {
		return "bj";
	}
}
