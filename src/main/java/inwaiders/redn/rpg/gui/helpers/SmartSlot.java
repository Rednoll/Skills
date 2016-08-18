package inwaiders.redn.rpg.gui.helpers;

import java.util.function.Function;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SmartSlot extends Slot{

	private Function<ItemStack, Boolean> f;
	public SmartSlot(IInventory inv, int idx, int x, int y, Function<ItemStack, Boolean> isItemValid) {
		super(inv, idx, x, y);
		this.f = isItemValid;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return f.apply(stack);
	}

}
