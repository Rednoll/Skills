package inwaiders.redn.rpg.gui.container;

import inwaiders.redn.rpg.gui.helpers.SmartSlot;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import inwaiders.redn.rpg.utils.skillitem.ISkillContainerItem;
import inwaiders.redn.rpg.utils.skillitem.ISkillItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SkillInjectorContainer extends ContainerWithPlayerInv {
	private TileSkillInjector te;
	public SkillInjectorContainer(IInventory playerInv, TileSkillInjector te) {
		super(playerInv, new Slot[]{new SmartSlot(te, 0, 80, 12,(stack) -> stack.getItem() instanceof ISkillItem), new SmartSlot(te, 1, 80, 56, (stack) -> stack.getItem() instanceof ISkillContainerItem)});
		this.te = te;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p) {
		return te.isUseableByPlayer(p);
	}

	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
            
            
	            if (fromSlot < 2) {	      
	                if (!this.mergeItemStack(current, 2, 38, true))
	                    return null;
	            } else {	           
	            	if (current.getItem() instanceof ISkillContainerItem)
	            	{
        				if(!this.mergeItemStack(current, 1, 2, false))
        				{
        					if (!this.mergeItemStack(current, 0, 1, false))
        					{
	            					return null;
        					}
        				}
	            	}
	            	else if(current.getItem() instanceof ISkillItem)
	            	{
	            		if (!this.mergeItemStack(current, 0, 1, false))
    					{
            					return null;
    					}
	            	}
	            }
            if (current.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }
	
}
