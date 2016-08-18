package inwaiders.redn.rpg.gui.gui;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.packet.LearnSkillPackect;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
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
	private int id;

	public LearnGui(int id) {
		this.id = id;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks) {
		BaseSkill skill = SkillsRegistry.getSkillById(id);
		PlayerInfoClient info = PlayerInfoManagerClient.instance.get(mc.thePlayer);
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
		drawCenteredStringNS("Skill cost: " + skill.getPrice().getPrice(), 0, -75, new Color(100, 100, 100));
		if (alert != null && !alert.equals(""))
			drawCenteredStringNS(alert, 0, -105, alertC);
		fontRendererObj.drawSplitString(skill.getPrice().getDescription(), k - 60, l + 10, 120, new Color(100, 100, 100).getRGB());
		/*List<String> desc = getSd(SkillsRegistry.getSkillById(id));
		for (int i = 0; i < desc.size(); i++)
			fontRendererObj.drawString(desc.get(i), width / 2 - 60, height / 2 + (10 * (i + 1)) - 20, new Color(100, 100, 100).getRGB());*/
		// GL11.glScaled(0.27, 0.27, 0.27);
		// TableHarrington.renderSuperFont(width / 2 + 310, height / 2,
		// "Learn point: " + l.getLearnPoints(), this);
	}

	private void drawCenteredStringNS(String s, int x, int y, Color c) {
		fontRendererObj.drawString(s, width / 2 + x - fontRendererObj.getStringWidth(s) / 2 , height / 2 + y, c.getRGB());
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		if (b.id == 0) {
			BaseSkill skill = SkillsRegistry.getSkillById(id);
			PlayerInfoClient l = PlayerInfoManagerClient.instance.get(mc.thePlayer);
			if (l.getSkillById(id).getLevel() < skill.getMaxLvl()) {
				if (l.canLearn(skill.getPrice().getPrice())) {
					alert("Succes", new Color(50, 255, 50));
					PacketDispatcher.sendToServer(new LearnSkillPackect(id));
				} else {
					alert("Not enough learn points", new Color(255, 50, 50));
				}
			}
			else
			{
				alert("Max level reached", new Color(255, 50, 50));
			}
		}
	}

	private void alert(String s, Color c) {
		alert = s;
		alertC = c;
	}

	@Override
	public void initGui() {
		buttonList.add(new SkillButton(0, width / 2 - 32, height / 2 - 60, SkillsRegistry.getSkillById(id)));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
