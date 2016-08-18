package inwaiders.redn.rpg.gui.gui;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.container.SkillInjectorContainer;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

public class SkillInjectorGui extends GuiContainer{

	public SkillInjectorGui(IInventory playerInv, TileSkillInjector te) {
		super(new SkillInjectorContainer(playerInv, te));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		int k = (this.width - this.xSize) / 2;
	    int l = (this.height - this.ySize) / 2;
	    mc.renderEngine.bindTexture(Core.guirlgen.generate("SkillInjector"));
	    drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
