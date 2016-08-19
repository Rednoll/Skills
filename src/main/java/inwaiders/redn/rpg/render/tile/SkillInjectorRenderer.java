package inwaiders.redn.rpg.render.tile;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class SkillInjectorRenderer extends TileEntitySpecialRenderer {

	private RenderItem renderItem;

	public SkillInjectorRenderer() {
		renderItem = new RenderItem() {
			public boolean shouldBob() {
				return false;
			}
		};
		renderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float p_147500_8_) {
		TileSkillInjector te = (TileSkillInjector) t;
		bindTexture(Core.modeltexrlgen.generate("injectorIMG"));
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glPushMatrix();
		AdvancedModelLoader.loadModel(Core.modelrlgen.generate("injector")).renderAll();
		renderSkill(te);
		renderContainer(te);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private float getGhostItemScaleFactor(ItemStack itemStack) {
		float scaleFactor = 1.0F;

		if (itemStack != null) {
			if (itemStack.getItem() instanceof ItemBlock) {
				switch (renderItem.getMiniBlockCount(itemStack, (byte) 1)) {
					case 1:
						return 0.90F;

					case 2:
						return 0.90F;

					case 3:
						return 0.90F;

					case 4:
						return 0.90F;

					case 5:
						return 0.80F;

					default:
						return 0.90F;
				}
			} else {
				switch (renderItem.getMiniItemCount(itemStack, (byte) 1)) {
					case 1:
						return 0.65F;

					case 2:
						return 0.65F;

					case 3:
						return 0.65F;

					case 4:
						return 0.65F;

					default:
						return 0.65F;
				}
			}
		}

		return scaleFactor;
	}

	private void renderContainer(TileSkillInjector te)
	{
		ItemStack container = te.getStackInSlot(1);
		if (container != null) {
			GL11.glPushMatrix();
			float scaleFactor = getGhostItemScaleFactor(te.getStackInSlot(0));
			float rotationAngle = Minecraft.getMinecraft().gameSettings.fancyGraphics ? (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) : 0;
			EntityItem ghostEntityItem = new EntityItem(te.getWorldObj());
			ghostEntityItem.hoverStart = 0.0F;
			ghostEntityItem.setEntityItemStack(te.getStackInSlot(1));
			float displacement = 0.2F;
			GL11.glTranslatef(0.5F, 0.3F, 0.6F);
			GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
			GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
			renderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
	}
	
	private void renderSkill(TileSkillInjector te)
	{
		ItemStack skill = te.getStackInSlot(0);
		if (skill != null) {
			GL11.glPushMatrix();
			float scaleFactor = getGhostItemScaleFactor(te.getStackInSlot(0));
			float rotationAngle = Minecraft.getMinecraft().gameSettings.fancyGraphics ? (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) : 0;
			EntityItem ghostEntityItem = new EntityItem(te.getWorldObj());
			ghostEntityItem.hoverStart = 0.0F;
			ghostEntityItem.setEntityItemStack(te.getStackInSlot(0));
			float displacement = 0.2F;
			GL11.glTranslatef(0.5F, 1.1F, 0.6F);
			GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
			GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
			renderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
	}
	
}
