package inwaiders.redn.rpg.entity;

import java.util.List;
import java.util.Random;

import inwaiders.redn.rpg.packet.ParticlePacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.skills.EasySkillCreator;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class VipeEntity extends Entity{
	
	float speed = 0;
	int damage = 0;
	int level = 0;
	double rotateY = 0;
	double rotateX = 0;
	EntityPlayer ep;
	
	public VipeEntity(World w, EntityPlayer ep, float speed, int damage, int level) {
		super(w);
		
		this.setSize(0.5F, 0.5F);
		
		this.ep = ep;
		this.rotateY = ep.rotationPitch;
		this.rotateX = ep.rotationYawHead;
		
		this.level = level;
		this.speed = speed;
		this.damage = damage;
        
		this.setPosition(ep.posX, ep.posY+0.8F, ep.posZ);
		this.renderDistanceWeight = 0;
	}

    public void onUpdate()
    {
    	if(onGround || ticksExisted > 1000)
    	{
    		this.setDead();
    	}
        this.onEntityUpdate();
        
        float cosY = (float)Math.cos(Math.toRadians(-rotateY));
        float sinY = (float)Math.sin(Math.toRadians(-rotateY));
         
        float cosX = (float)Math.cos(Math.toRadians(-rotateX));
        float sinX = (float)Math.sin(Math.toRadians(-rotateX));

        this.posX += sinX * cosY * speed;
        this.posZ += cosX * cosY * speed;
        this.posY += sinY * speed;
        
        this.setPosition(this.posX, this.posY, this.posZ);
        
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(this.posX-0.5F, this.posY-0.5F, this.posZ-0.5F, this.posX+0.5F, this.posY+0.5F, this.posZ+0.5F);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(ep, aabb);
		
		for(int i = 0;i<list.size();i++){
			
			if(list.get(i) instanceof EntityLivingBase){
				EntityLivingBase el = (EntityLivingBase)list.get(i);
				EasySkillCreator.attack(ep, el, damage);
				el.addPotionEffect(new PotionEffect(Potion.poison.id, 400, level));
				this.setDead();
			}

		}
		
		Random r = ep.worldObj.rand;
		for (int i = 0; i < 10; i++) {
			if(!this.worldObj.isRemote)
				PacketDispatcher.sendToAllAround(new ParticlePacket(EntitySpellParticleFX.class, 0F, 1F - r.nextFloat(), 0F, this.posX, this.posY+0.5F, this.posZ, 0D, 0D, 0D), ep, 40);
		}
		
    }
    
	@Override
	protected void entityInit() {
		
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
		
	}

}
