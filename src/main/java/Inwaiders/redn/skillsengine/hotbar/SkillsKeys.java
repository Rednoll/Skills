package Inwaiders.redn.skillsengine.hotbar;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class SkillsKeys {


	   public static KeyBinding skill1;
	   public static KeyBinding skill2;
	   public static KeyBinding skill3;
	   public static KeyBinding skill4;
	   public static KeyBinding skill5;
	   public static KeyBinding skill6;
	   
	   public static void init() {

		   skill1 = new KeyBinding("key.skill1", Keyboard.KEY_NUMPAD7, "Skills");
		   skill2 = new KeyBinding("key.skill2", Keyboard.KEY_NUMPAD8, "Skills");
		   skill3 = new KeyBinding("key.skill3", Keyboard.KEY_NUMPAD9, "Skills");
		   skill4 = new KeyBinding("key.skill4", Keyboard.KEY_NUMPAD4, "Skills");
		   skill5 = new KeyBinding("key.skill5", Keyboard.KEY_NUMPAD5, "Skills");
		   skill6 = new KeyBinding("key.skill6", Keyboard.KEY_NUMPAD6, "Skills");

	       ClientRegistry.registerKeyBinding(skill1);
	       ClientRegistry.registerKeyBinding(skill2);
	       ClientRegistry.registerKeyBinding(skill3);
	       ClientRegistry.registerKeyBinding(skill4);
	       ClientRegistry.registerKeyBinding(skill5);
	       ClientRegistry.registerKeyBinding(skill6);
	       

	   }
}
