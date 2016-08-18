package inwaiders.redn.rpg.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import inwaiders.redn.rpg.core.Core;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block{

	public static enum ToolClass
	{
		prickaxe,
		axe,
		spade,
		none
	}
	
	public BlockBase(Material mat, String name, float hardness, float resistance, String tool, int harversLevel) {
		super(mat);
		setBlockName(name);
		setBlockTextureName(Core.MODID + ":" + name);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(Core.tab);
		if(tool != "none" && tool != "")
		{
			setHarvestLevel(tool, harversLevel);
		}
		GameRegistry.registerBlock(this, name);
	}
	
	public BlockBase(Material mat, String name, float hardness, float resistance, ToolClass tool, int harversLevel) {
		this(mat, name, hardness, resistance, tool.toString(), harversLevel);
	}

}
