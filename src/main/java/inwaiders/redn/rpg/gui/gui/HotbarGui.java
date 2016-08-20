package inwaiders.redn.rpg.gui.gui;

import org.lwjgl.opengl.GL11;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.button.HotbarButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class HotbarGui extends GuiScreen
{

	private int xsize = 176;
	private int ysize = 166;
	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks)
	{
		
		int k = (this.width - xsize) / 2;
	    int l = (this.height - ysize) / 2;
	    super.drawScreen(mouseX, mouseY, ticks);
	}
	
	@Override
	public void initGui()
	{
		int k = (width - 426) / 2;
		for(int i = 0; i < 6; i++)
		buttonList.add(new HotbarButton(0, k + 94 + 40 * i, 4));
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
}
