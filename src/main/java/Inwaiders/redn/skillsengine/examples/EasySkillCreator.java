package Inwaiders.redn.skillsengine.examples;

import java.util.List;

import Inwaiders.redn.teamengine.targeting.Targeting;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntityDamageSource;

public class EasySkillCreator {

	//Для ленивых жоп (ну вообше это помошник)
	
	public static void appliedAuraEffect(EntityPlayer ep, int radius, int idEffect, int level, String targets){
	
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(ep.posX-radius, ep.posY-radius, ep.posZ-radius, ep.posX+radius, ep.posY+radius, ep.posZ+radius);
		List list = ep.worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
		
		for(int i = 0;i<list.size();i++){
			
			if(list.get(i) instanceof EntityLivingBase){
				
				EntityLivingBase ent = (EntityLivingBase)list.get(i);
				
				if(Targeting.canAttack(ep, ent, targets))
					ent.addPotionEffect(new PotionEffect(idEffect, 20, level, true));
			}
			
		}
		
	}
	
	public static void attackAOE(EntityPlayer ep, int radius, int damage, String targets){
		
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(ep.posX-radius, ep.posY-radius, ep.posZ-radius, ep.posX+radius, ep.posY+radius, ep.posZ+radius);
		List list = ep.worldObj.getEntitiesWithinAABBExcludingEntity(ep, aabb);
		
		for(int i = 0;i<list.size();i++){
			
			if(list.get(i) instanceof EntityLivingBase){
				
				EntityLivingBase ent = (EntityLivingBase)list.get(i);
				
				if(Targeting.canAttack(ep, ent, targets))
					EasySkillCreator.attack(ep, ent, damage);
			}
			
		}
	}
	
	public static void attack(EntityPlayer owner, EntityLivingBase target, int i){
		
		target.attackEntityFrom(new EntityDamageSource("player", owner), i);
	}
	
	
}
