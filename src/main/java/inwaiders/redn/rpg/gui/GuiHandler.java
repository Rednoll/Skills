package inwaiders.redn.rpg.gui;

import inwaiders.redn.rpg.files.CFG;
import inwaiders.redn.rpg.gui.gui.LearnGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int LEARN_ID = 0;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case (0):
			{
				
				break;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case (0):
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
		}
		return null;
	}

}
