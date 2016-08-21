package inwaiders.redn.rpg.gui.button;

import org.lwjgl.opengl.GL11;

import inwaiders.redn.rpg.skills.BaseSkill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class SkillButton extends GuiButton {
	private final BaseSkill skill;
	private float xscale, yscale, zscale;
	private int renderx, rendery, renderxsize, renderysize;
	public SkillButton(int id, int x, int y, int width, int heigth, BaseSkill skill, float xscale, float yscale, float zscale, int renderx, int rendery) {
		super(id, x, y, width, heigth, "");
		this.skill = skill;
		this.xscale = xscale;
		this.yscale = yscale;
		this.zscale = zscale;
		this.renderx = renderx;
		this.rendery = rendery;
	}

	public SkillButton(int id, int x, int y, BaseSkill skill) {
		this(id, x, y, 64, 64, skill, 1F, 1F, 1F, x, y);
	}

	public String getSkillName() {
		System.out.println(skill + "/" + skill.getName());
		return skill.getName();
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(skill.getTexture());
		GL11.glScalef(xscale, yscale, zscale);
		drawTexturedModalRect(renderx, rendery, 0, 0, 64, 64);
		GL11.glScalef(1F / xscale, 1F / yscale, 1F / zscale);
	}

}
