package inwaiders.redn.rpg.gui.gui;

import org.lwjgl.opengl.GL11;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.button.HotbarButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class HotbarGui extends GuiScreen
{

	private HotbarButton lastSeleced;
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
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int k = 2;
		int xPos = (sr.getScaledWidth() / 2) / k - 61;
		GL11.glScalef(0.25F, 0.25F, 0.25F);
		for(int i = 0; i < 6; i++)
		buttonList.add(new HotbarButton(i, xPos * 2 + 4 + 40 * i, 4));
	}
	
	@Override
	protected void actionPerformed(GuiButton b) {
		if(b.id < 6 && b instanceof HotbarButton)
		{
			((HotbarButton) b).selected = true;
			if(lastSeleced != null)
			{
				lastSeleced.selected = false;
			}
			lastSeleced = (HotbarButton) b;
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
}
