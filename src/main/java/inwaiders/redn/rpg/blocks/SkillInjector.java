package inwaiders.redn.rpg.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.GuiHandler;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SkillInjector extends BlockBase implements ITileEntityProvider {

	public SkillInjector() {
		super(Material.iron, "SkillInjector", 5F, 5F, ToolClass.prickaxe, 2);
		isBlockContainer = true;
		GameRegistry.registerTileEntity(TileSkillInjector.class, "RPGSKILLINJECTOR");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		p.openGui(Core.instance, GuiHandler.INJECTOR_ID, world, x, y, z);
		return true;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSkillInjector();
	}
	
}
