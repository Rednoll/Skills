package inwaiders.redn.rpg.gui.gui;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.button.HotbarButton;
import inwaiders.redn.rpg.gui.button.SkillButton;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.packet.SetHotbarPacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class HotbarGui extends GuiScreen
{
	private HotbarButton lastSeleced;
	private int xsize = 176;
	private int ysize = 166;
	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks)
	{	
		int k = (this.width - xsize) / 2;
	    int l = (this.height - ysize) / 2 + 8;
	    super.drawScreen(mouseX, mouseY, ticks);
	    mc.renderEngine.bindTexture(Core.guirlgen.generate("SkillBank"));
	    //drawTexturedModalRect(10, 45, 0, 0, 10, ysize);
	    //drawTexturedModalRect(mc.displayWidth - 10, 45, 166, 0, 10, ysize);
	}
	
	@Override
	public void initGui()
	{
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int k = 2;
		int xPos = (sr.getScaledWidth() / 2) / k - 61;
		GL11.glPushMatrix();
		GL11.glScalef(0.25F, 0.25F, 0.25F);
		for(int i = 0; i < 6; i++)
		{
			buttonList.add(new HotbarButton(i, xPos * 2 + 4 + 40 * i, 4));
		}
		PlayerInfoClient in = PlayerInfoManagerClient.instance.get(mc.thePlayer);
		BaseSkill[] skills = (BaseSkill[]) in.getSkills().values().toArray(new BaseSkill[in.getSkills().size()]);
		int buttonPos = sr.getScaledWidth() / 2 - 61;
		for(int i = 0; i < skills.length; i++)
		{
			buttonList.add(new SkillButton(i + 6, buttonPos - 56 + 40 * i - (40 * 6 * (i / 6)), 45 + 40 * (i / 6), 32, 32, skills[i], 0.5F, 0.5F, 0.5F, buttonPos * 2 - 112 + 80 * i - (80 * 6 * (i / 6)), 90 + 80 * (i / 6), fontRendererObj));
		}
		GL11.glPopMatrix();
	}
	
	@Override
	protected void actionPerformed(GuiButton b) {
		if(b.id < 6 && b instanceof HotbarButton)
		{
			((HotbarButton) b).selected = true;
			if(lastSeleced != null)
			{
				lastSeleced.selected = false;
			}
			lastSeleced = (HotbarButton) b;
		}
		else if (b instanceof SkillButton && lastSeleced != null && !((SkillButton) b).getSkillName().equals(""))
		{
			PacketDispatcher.sendToServer(new SetHotbarPacket(((SkillButton) b).getSkillName(), lastSeleced.id));
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
}
