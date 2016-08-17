package inwaiders.redn.rpg.handlers;

import inwaiders.redn.rpg.managers.client.PlayerInfoManagerClient;
import inwaiders.redn.rpg.packet.SkillsStartPacket;
import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.storage.client.PlayerInfoClient;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class SkillsKeys
{

	public static KeyBinding skill1;
	public static KeyBinding skill2;
	public static KeyBinding skill3;
	public static KeyBinding skill4;
	public static KeyBinding skill5;
	public static KeyBinding skill6;

	public static void init()
	{

		ClientRegistry.registerKeyBinding(skill1 = new KeyBinding("key.skill1", Keyboard.KEY_NUMPAD7, "Skills"));
		ClientRegistry.registerKeyBinding(skill2 = new KeyBinding("key.skill2", Keyboard.KEY_NUMPAD8, "Skills"));
		ClientRegistry.registerKeyBinding(skill3 = new KeyBinding("key.skill3", Keyboard.KEY_NUMPAD9, "Skills"));
		ClientRegistry.registerKeyBinding(skill4 = new KeyBinding("key.skill4", Keyboard.KEY_NUMPAD4, "Skills"));
		ClientRegistry.registerKeyBinding(skill5 = new KeyBinding("key.skill5", Keyboard.KEY_NUMPAD5, "Skills"));
		ClientRegistry.registerKeyBinding(skill6 = new KeyBinding("key.skill6", Keyboard.KEY_NUMPAD6, "Skills"));
	}
	
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

				PlayerInfoClient b = PlayerInfoManagerClient.instance.get(mc.thePlayer);

				if (b.hasSkill(b.getSkillIdByPos(i)))
				{
					PacketDispatcher.sendToServer(new SkillsStartPacket(b.getSkillIdByPos(i)));
					b.activateSkill(b.getSkillIdByPos(i), mc.thePlayer);
				}

			}
		}

	}
}
