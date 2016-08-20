package inwaiders.redn.rpg.gui;

import inwaiders.redn.rpg.gui.container.SkillInjectorContainer;
import inwaiders.redn.rpg.gui.gui.HotbarGui;
import inwaiders.redn.rpg.gui.gui.LearnGui;
import inwaiders.redn.rpg.gui.gui.SkillInjectorGui;
import inwaiders.redn.rpg.items.SkillScroll;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import inwaiders.redn.rpg.utils.ItemNBT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int LEARN_ID = 0;
	public static final int INJECTOR_ID = 1;
	public static final int HOTBAR_ID = 2;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case (LEARN_ID):
			{
				
				break;
			}
			case(INJECTOR_ID):
			{
				return new SkillInjectorContainer(player.inventory, (TileSkillInjector) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case (LEARN_ID):
			{
				ItemStack scroll = player.getHeldItem();
				if(scroll != null && scroll.getItem() instanceof SkillScroll && ItemNBT.verifyExistance(scroll, "Skill")) { //Just in case
					return new LearnGui(ItemNBT.getString(scroll, "Skill", "NONE"));
				}
				return null;
			}
			case(INJECTOR_ID):
			{
				return new SkillInjectorGui(player.inventory, (TileSkillInjector) world.getTileEntity(x, y, z));
			}
			case(HOTBAR_ID):
			{
				return new HotbarGui();
			}
		}
		return null;
	}

}
