package inwaiders.redn.rpg.gui.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.packet.LearnSkillPackect;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
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

		if (event.type == ElementType.HOTBAR) {

			ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);


			int xPos = sr.getScaledWidth() / 2;

			int yPos =	sr.getScaledHeight() / 2;

			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			this.mc.getTextureManager().bindTexture(texturepath_ThirdEye);

			drawTexturedModalRect(xPos-128, yPos-128, 0, 0, 256, 256);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();

		}
	}


}
