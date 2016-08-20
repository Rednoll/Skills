package inwaiders.redn.rpg.items;

import inwaiders.redn.rpg.skills.EasySkillCreator;
import inwaiders.redn.rpg.utils.MiscUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ThirdEye extends ItemBase{

	public ThirdEye() {
		super("ThirdEye");
		this.setMaxStackSize(1);
	}

	public void onUpdate(ItemStack is, World world, Entity e, int p_77663_4_, boolean bool) {
		
	}
	
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer ep){
        
    	MovingObjectPosition o = MiscUtils.getPlayerTarget(ep, 120, 0);
    	
    	if(o != null && o.entityHit != null && o.typeOfHit == MovingObjectType.ENTITY && o.entityHit instanceof EntityLivingBase){
    		
    		EntityLivingBase e = (EntityLivingBase)o.entityHit;
    		EasySkillCreator.attack(ep, e, 600);
    	}
    	
    	return is;
    }
    
}
