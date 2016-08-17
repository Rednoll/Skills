package inwaiders.redn.skillsengine.learn.gui.gui;

import inwaiders.redn.skillsengine.examples.BaseSkill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class SkillButton extends GuiButton
{
	private final BaseSkill skill;
	public SkillButton(int id, int x, int y, int width, int heigth, BaseSkill skill)
	{
		super(id, x, y, width, heigth, "");
		this.skill = skill;
	}

	public SkillButton(int id, int x, int y, BaseSkill skill)
	{
		this(id, x, y, 32, 32, skill);
	}
	
	public int getSkillID()
	{
		return skill.getId();
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		mc.renderEngine.bindTexture(skill.getTexture());
		drawTexturedModalRect(xPosition, yPosition, 0, 0, width, height);
	}

}
