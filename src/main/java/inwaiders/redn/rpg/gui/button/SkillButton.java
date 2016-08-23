package inwaiders.redn.rpg.gui.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import inwaiders.redn.rpg.skills.BaseSkill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class SkillButton extends GuiButton {
	private final BaseSkill skill;
	private float xscale, yscale, zscale;
	private int renderx, rendery, renderxsize, renderysize;
	private FontRenderer font;
	public SkillButton(int id, int x, int y, int width, int heigth, BaseSkill skill, float xscale, float yscale, float zscale, int renderx, int rendery, FontRenderer font) {
		super(id, x, y, width, heigth, "");
		this.skill = skill;
		this.xscale = xscale;
		this.yscale = yscale;
		this.zscale = zscale;
		this.renderx = renderx;
		this.rendery = rendery;
		this.font = font;
	}

	public SkillButton(int id, int x, int y, BaseSkill skill) {
		this(id, x, y, 64, 64, skill, 1F, 1F, 1F, x, y, null);
	}

	public String getSkillName() {
		return skill.getName();
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		drawRect(xPosition, yPosition, xPosition + width, yPosition + height, 0xFFFFFFFF);                                
		mc.renderEngine.bindTexture(skill.getTexture());
		GL11.glScalef(xscale, yscale, zscale);
		drawTexturedModalRect(renderx, rendery, 0, 0, 64, 64);
		if(font != null)
		{
			drawCenteredStringNS(font, skill.getLevel() + 1 + "", renderx + 18, rendery + 38, new Color(255, 255, 255));
		}
		GL11.glScalef(1F / xscale, 1F / yscale, 1F / zscale);
	}

	private void drawCenteredStringNS(FontRenderer fontRendererObj, String s, int x, int y, Color c) {
		fontRendererObj.drawString(s, width / 2 + x - fontRendererObj.getStringWidth(s) / 2 , height / 2 + y, c.getRGB());
	}
	
	@Override
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
		return super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_);
	}
}
