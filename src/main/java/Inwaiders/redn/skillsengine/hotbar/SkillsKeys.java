package inwaiders.redn.skillsengine.hotbar;

import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.ClientRegistry;
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
}
