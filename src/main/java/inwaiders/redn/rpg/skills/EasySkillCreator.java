package inwaiders.redn.rpg.skills;

import inwaiders.redn.rpg.utils.MiscUtils;
import inwaiders.redn.rpg.utils.Targeting;
import inwaiders.redn.rpg.utils.Targeting.Target;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EasySkillCreator {

	//Для ленивых жоп (ну вообше это помошник)
	
	public static void appliedAuraEffect(EntityPlayer ep, int radius, int idEffect, int level, Target targets){
		List list = ep.worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(ep.posX-radius, ep.posY-radius, ep.posZ-radius, ep.posX+radius, ep.posY+radius, ep.posZ+radius), (entity) -> entity instanceof EntityLivingBase);
		
		for(int i = 0;i<list.size();i++){
			
			if(list.get(i) instanceof EntityLivingBase){
				
				EntityLivingBase ent = (EntityLivingBase)list.get(i);
				
				if(Targeting.canAttack(ep, ent, targets))
					ent.addPotionEffect(new PotionEffect(idEffect, 20, level, true));
			}
			
		}
		
	}
	
	public static void attackAOE(EntityPlayer ep, int radius, int damage, Target targets){
		List list = ep.worldObj.getEntitiesWithinAABBExcludingEntity(ep, MiscUtils.createAABBFormRadius(ep.posX, ep.posY, ep.posZ, radius), (entity) -> entity instanceof EntityLivingBase);
		
		for(int i = 0;i<list.size();i++){
			
			if(list.get(i) instanceof EntityLivingBase){
				
				EntityLivingBase ent = (EntityLivingBase)list.get(i);
				
				if(Targeting.canAttack(ep, ent, targets))
					EasySkillCreator.attack(ep, ent, damage);
			}
			
		}
	}
	
	@FunctionalInterface
	public static interface IEffect
	{
		public void apply(EntityPlayer caster, EntityLivingBase target);
	}
	
	public static void applyAOEEffect(EntityPlayer ep, int radius, Target target, IEffect effect)
	{
		List<EntityLivingBase> entities = ep.worldObj.getEntitiesWithinAABBExcludingEntity(ep, MiscUtils.createAABBFormRadius(ep.posX, ep.posY, ep.posZ, radius), (entity) -> entity instanceof EntityLivingBase);
		for(EntityLivingBase e : entities)
		{
			if(Targeting.canAttack(ep, e, target))
			{
				effect.apply(ep, e);
			}
		}
	}
	
	public static void attack(EntityPlayer owner, EntityLivingBase target, int i){
		
		target.attackEntityFrom(new EntityDamageSource("player", owner), i);
	}
	
	
}
