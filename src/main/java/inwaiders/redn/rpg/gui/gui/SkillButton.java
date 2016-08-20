package inwaiders.redn.rpg.gui.gui;

import inwaiders.redn.rpg.skills.BaseSkill;
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
		this(id, x, y, 64, 64, skill);
	}
	
	public String getSkillName()
	{
		return skill.getName();
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		mc.renderEngine.bindTexture(skill.getTexture());
		drawTexturedModalRect(xPosition, yPosition, 0, 0, width, height);
	}

}
