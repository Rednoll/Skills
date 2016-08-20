package inwaiders.redn.rpg.gui.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.registry.ItemRegistry;
import inwaiders.redn.rpg.utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class ThirdEyeGui extends Gui {

	private Minecraft mc;
	private static final ResourceLocation texturepath_ThirdEye = new ResourceLocation(Core.MODID, "textures/base/thirdEye.png");

	public ThirdEyeGui(Minecraft mc) {
		super();

		this.mc = mc;
	}

	@SubscribeEvent()
	public void onHUDRender(RenderGameOverlayEvent event) {

		if(mc.thePlayer.getHeldItem() != null ? mc.thePlayer.getHeldItem().getItem() == ItemRegistry.thirdEye : false)
			if (event.type == ElementType.HOTBAR) {
	
				ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	
	
				int xPos = sr.getScaledWidth() / 2;
	
				int yPos =	sr.getScaledHeight() / 2;
				
				MovingObjectPosition o = MiscUtils.getPlayerTarget(mc.thePlayer, 1200, 0);
				
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				this.mc.getTextureManager().bindTexture(texturepath_ThirdEye);
				
					drawTexturedModalRect(xPos - 128, yPos - 128, 0, 0, 256, 256);
					
					if(o == null)
						mc.fontRenderer.drawString("Ty: " + "MISS", xPos - 47 - mc.fontRenderer.getStringWidth("Ty : " + "MISS")/2, yPos - 4, 0xFFFFFF);
					else
					switch(o.typeOfHit){
						
						case MISS :
							mc.fontRenderer.drawString("Ty: " + "MISS", xPos - 47 - mc.fontRenderer.getStringWidth("Ty : " + "MISS")/2, yPos - 4, 0xFFFFFF);
						break;
						
						case BLOCK :
							mc.fontRenderer.drawString("Ty: " + "Block", xPos - 49 - mc.fontRenderer.getStringWidth("Ty : " + "MISS")/2, yPos - 4, 0xFFFFFF);
							mc.fontRenderer.drawString("X:" + o.blockX, xPos - 44 - mc.fontRenderer.getStringWidth("X:" + o.blockX)/2, yPos + 12, 0xFFFFFF);
							mc.fontRenderer.drawString("Y:" + o.blockY, xPos - 41 - mc.fontRenderer.getStringWidth("Y:" + o.blockY)/2, yPos + 28, 0xFFFFFF);
							mc.fontRenderer.drawString("Z:" + o.blockZ, xPos - 20 - mc.fontRenderer.getStringWidth("Z:" + o.blockZ)/2, yPos + 44, 0xFFFFFF);
						break;
						
						case ENTITY :
							int distance = (int) this.mc.thePlayer.getDistance(o.entityHit.posX, o.entityHit.posY, o.entityHit.posZ);
							
							mc.fontRenderer.drawString("Ty: " + "Entity", xPos - 49 - mc.fontRenderer.getStringWidth("Ty : " + "MISS")/2, yPos - 4, 0xFFFFFF);
							mc.fontRenderer.drawString("X:" + (int)o.entityHit.posX, xPos - 44 - mc.fontRenderer.getStringWidth("X:" + (int)o.entityHit.posX)/2, yPos + 12, 0xFFFFFF);
							mc.fontRenderer.drawString("Y:" + (int)o.entityHit.posY, xPos - 41 - mc.fontRenderer.getStringWidth("Y:" + (int)o.entityHit.posY)/2, yPos + 28, 0xFFFFFF);
							mc.fontRenderer.drawString("Z:" + (int)o.entityHit.posZ, xPos - 20 - mc.fontRenderer.getStringWidth("Z:" + (int)o.entityHit.posZ)/2, yPos + 44, 0xFFFFFF);
							
							mc.fontRenderer.drawString("Dist:" + distance, xPos - mc.fontRenderer.getStringWidth("Dist:" + distance)/2, yPos - 50, 0xFFFFFF);
						break;
					}
					
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(true);
				GL11.glPopMatrix();
	
			}
	}


}
