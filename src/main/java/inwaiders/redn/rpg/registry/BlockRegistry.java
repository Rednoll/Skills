package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.blocks.SkillInjector;
import net.minecraft.block.Block;

public class BlockRegistry {
	
	public static Block skillInjector;

	public static void init() {
		skillInjector = new SkillInjector();
	}
	
}
