package inwaiders.redn.rpg.gui;

import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.gui.container.SkillInjectorContainer;
import inwaiders.redn.rpg.gui.gui.LearnGui;
import inwaiders.redn.rpg.gui.gui.SkillInjectorGui;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int LEARN_ID = 0;
	public static final int INJECTOR_ID = 1;
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
				if(!player.isSneaking())
				{
					return new LearnGui(CFG.BackJumpID);
				}
				else
				{
					return new LearnGui(CFG.ReleaseOfPranaID);
				}
			}
			case(INJECTOR_ID):
			{
				return new SkillInjectorGui(player.inventory, (TileSkillInjector) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

}
