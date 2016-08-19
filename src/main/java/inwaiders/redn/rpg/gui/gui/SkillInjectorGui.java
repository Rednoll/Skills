package inwaiders.redn.rpg.gui.gui;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.container.SkillInjectorContainer;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

public class SkillInjectorGui extends GuiContainer{

	TileSkillInjector te;
	public SkillInjectorGui(IInventory playerInv, TileSkillInjector te) {
		super(new SkillInjectorContainer(playerInv, te));
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		int k = (this.width - this.xSize) / 2;
	    int l = (this.height - this.ySize) / 2;
	    mc.renderEngine.bindTexture(Core.guirlgen.generate("SkillInjector"));
	    drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	    int work = te.getWorkTimeScaled();
	    if(work != 0)
	    {
	    	drawTexturedModalRect(k + 81, l + 33, 176, 3, 19, work);
	    }
	}

}
