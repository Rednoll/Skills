package inwaiders.redn.rpg.items;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DebugItem extends Item
{
	public DebugItem()
	{
		setUnlocalizedName("DEBUG");
		setCreativeTab(Core.tab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p)
	{
		p.openGui(Core.instance, GuiHandler.LEARN_ID, w, (int) p.posX, (int) p.posY, (int) p.posZ);
		return stack;
	}
}
