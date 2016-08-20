package inwaiders.redn.rpg.gui.gui;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.button.SkillButton;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.packet.LearnSkillPackect;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.storage.LearnPointsPrice;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class LearnGui extends GuiScreen {
	private String alert;
	private Color alertC;
	private String name;

	public LearnGui(String name) {
		this.name = name;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks) {
		PlayerInfoClient info = PlayerInfoManagerClient.instance.get(mc.thePlayer);
		BaseSkill skill = info.getSkillByName(name);
		if(skill == null)
		{
			skill = SkillsRegistry.getSkillByName(name);
		}
		LearnPointsPrice price = skill.getPrice();
		drawDefaultBackground();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int k = width / 2;
		int l = height / 2;
		int guiWidth = 760, guiHeight = 980;
		int guiX = (sr.getScaledWidth() - guiWidth / 4) / 2;
		int guiZ = (sr.getScaledHeight() - guiHeight / 4) / 2;
		mc.renderEngine.bindTexture(Core.guirlgen.generate("LearnBG"));
		drawTexturedModalRect(guiX, guiZ, 0, 0, guiWidth / 4, guiHeight / 4);
		super.drawScreen(mouseX, mouseY, ticks);
		drawCenteredStringNS("Learn points: " + info.getLearnPoints(), 0, -85, new Color(100, 100, 100));
		drawCenteredStringNS("Skill cost: " + price.getPrice(), 0, -75, info.canLearn(skill) == 0 ? new Color(50, 255, 50) : new Color(255, 100, 100));
		if (alert != null && !alert.equals(""))
			drawCenteredStringNS(alert, 0, -105, alertC);
		fontRendererObj.drawSplitString(price.getDescription(), k - 60, l + 10, 120, new Color(100, 100, 100).getRGB());
	}

	private void drawCenteredStringNS(String s, int x, int y, Color c) {
		fontRendererObj.drawString(s, width / 2 + x - fontRendererObj.getStringWidth(s) / 2 , height / 2 + y, c.getRGB());
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		if (b.id == 0) {
			PlayerInfoClient l = PlayerInfoManagerClient.instance.get(mc.thePlayer);
			BaseSkill skill = l.getSkillByName(name);
			if(skill == null)
			{
				skill = SkillsRegistry.getSkillByName(name);
			}
			switch(l.canLearn(skill))
			{
				case(0):
				{
					PacketDispatcher.sendToServer(new LearnSkillPackect(name, mc.thePlayer.inventory.currentItem));
					if(!mc.thePlayer.capabilities.isCreativeMode)
					{
						mc.displayGuiScreen(null);
				        if (mc.currentScreen == null)
				            mc.setIngameFocus();
					}
					return;
				}
				case(1):
				{
					alert("Max level reached", new Color(255, 50, 50));
					return;
				}
				case(2):
				{
					alert("Not enough learn points", new Color(255, 50, 50));
					return;
				}
			}
		}
	}

	private void alert(String s, Color c) {
		alert = s;
		alertC = c;
	}

	@Override
	public void initGui() {
		buttonList.add(new SkillButton(0, width / 2 - 32, height / 2 - 60, SkillsRegistry.getSkillByName(name)));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
