package inwaiders.redn.rpg.handlers;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.GuiHandler;
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
	public static KeyBinding hotbar;
	public static void init()
	{

		ClientRegistry.registerKeyBinding(skill1 = new KeyBinding("key.skill1", Keyboard.KEY_NUMPAD7, "Skills"));
		ClientRegistry.registerKeyBinding(skill2 = new KeyBinding("key.skill2", Keyboard.KEY_NUMPAD8, "Skills"));
		ClientRegistry.registerKeyBinding(skill3 = new KeyBinding("key.skill3", Keyboard.KEY_NUMPAD9, "Skills"));
		ClientRegistry.registerKeyBinding(skill4 = new KeyBinding("key.skill4", Keyboard.KEY_NUMPAD4, "Skills"));
		ClientRegistry.registerKeyBinding(skill5 = new KeyBinding("key.skill5", Keyboard.KEY_NUMPAD5, "Skills"));
		ClientRegistry.registerKeyBinding(skill6 = new KeyBinding("key.skill6", Keyboard.KEY_NUMPAD6, "Skills"));
		ClientRegistry.registerKeyBinding(hotbar = new KeyBinding("key.hotbar", Keyboard.KEY_NUMPAD2, "Skills"));
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
		Minecraft mc = Minecraft.getMinecraft();
		for (int i = 0; i < keys.length; i++)
		{

			if (keys[i].isPressed())
			{

				PlayerInfoClient b = PlayerInfoManagerClient.instance.get(mc.thePlayer);

				if (b.hasSkill(b.hotbar[i]))
				{
					PacketDispatcher.sendToServer(new SkillsStartPacket(b.hotbar[i]));
					b.activateSkill(b.hotbar[i], mc.thePlayer);
				}

			}
		}
		if(hotbar.isPressed())
		{
			mc.thePlayer.openGui(Core.instance, GuiHandler.HOTBAR_ID, mc.theWorld, 0, 0, 0);
		}
	}
}
