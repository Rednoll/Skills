package inwaiders.redn.rpg.gui.button;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class HotbarButton extends GuiButton
{

	public boolean selected = false;
	public HotbarButton(int idx, int x, int y)
	{
		super(idx, x, y, 36, 36, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if(mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height)
		{
			drawRect(xPosition, yPosition, xPosition + width, yPosition + height, new Color(44, 44, 44, 44).getRGB());
		}
		else if (selected)
		{
			drawRect(xPosition, yPosition, xPosition + width, yPosition + height, new Color(100, 100, 100, 150).getRGB());
		}
	}

}
