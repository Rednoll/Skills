package inwaiders.redn.skillsengine.hotbar;

import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.skillsengine.bank.BankManagerClient;
import inwaiders.redn.skillsengine.bank.PlayerSkillBankClient;
import inwaiders.redn.skillsengine.bank.SkillsStartPacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class SkillStartetHandler
{

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{

		KeyBinding[] keys = new KeyBinding[] { 
		SkillsKeys.skill1,
		SkillsKeys.skill2,
		SkillsKeys.skill3,
		SkillsKeys.skill4,
		SkillsKeys.skill5,
		SkillsKeys.skill6 
		};
		for (int i = 0; i < keys.length; i++)
		{

			if (keys[i].isPressed())
			{

				Minecraft mc = Minecraft.getMinecraft();

				SkillsHotBar h = SkillsHotBarManager.instance.get(mc.thePlayer);
				PlayerSkillBankClient b = BankManagerClient.instance.get(mc.thePlayer);

				if (b.hasSkill(h.getSkillIdByPos(i)))
				{
					PacketDispatcher.sendToServer(new SkillsStartPacket(h.getSkillIdByPos(i)));
					b.activateSkill(h.getSkillIdByPos(i), mc.thePlayer);
				}

			}
		}

	}
}
