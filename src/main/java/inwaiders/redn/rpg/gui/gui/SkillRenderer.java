package inwaiders.redn.rpg.gui.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class SkillRenderer extends Gui {

	private Minecraft mc;
	private static final ResourceLocation texturepath = new ResourceLocation(Core.MODID, "textures/base/skills_bar.png");

	public SkillRenderer(Minecraft mc) {
		super();

		this.mc = mc;
	}

	@SubscribeEvent()
	public void onHUDRender(RenderGameOverlayEvent event) {
		
		if (event.type == ElementType.HOTBAR) {

			ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

			int k = 2;

			int xPos = (sr.getScaledWidth()) / k - 21;

			int yPos = (int) (sr.getScaledHeight() / 2.08F) / k - 57;

			int size = 159;
			int sizefood = 134;

			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			this.mc.getTextureManager().bindTexture(texturepath);

			GL11.glScalef(2F, 2F, 1F);

			drawTexturedModalRect(xPos, yPos, 0, 0, 22, 122);

			for (int i = 0; i < 6; i++) {
				renderSkill(i, xPos+3, yPos+3+20*i);
			}

			GL11.glScalef(1F, 1F, 1F);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();

		}
	}

	public void renderSkill(int pos, int xPos, int yPos) {

		PlayerInfoClient se = PlayerInfoManagerClient.instance.get(mc.thePlayer);

		BaseSkill b = se.getSkillByName(se.hotbar[pos]);
		if (b != null) {
			this.mc.getTextureManager().bindTexture(b.getTexture());
			GL11.glPushMatrix();
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			mc.ingameGUI.drawTexturedModalRect(xPos*4, yPos*4, 0, 0, 64, 64);
			GL11.glScalef(2F, 2F, 2F);
			int cd = b.getCoolDown();
			if (cd != 0)
				mc.fontRenderer.drawString("" + (cd / 20), xPos * 2 + 14 - mc.fontRenderer.getStringWidth("" + (cd / 20)) / 2 / 2, yPos * 2 + 12, 0xFFFFFF);
			this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));
			GL11.glColor3f(1, 1, 1);
			GL11.glPopMatrix();
		}
	}

}