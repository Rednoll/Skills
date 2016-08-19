package inwaiders.redn.rpg.items.armor;

import java.util.List;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.utils.skillitem.ISkillContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SkillArmor extends ItemArmor implements ISkillContainerItem {

	private final String armorTextureName;
	public SkillArmor(int type) {
		super(ArmorMaterial.DIAMOND, 0, type);
		setUnlocalizedName("SkillArmor-" + type);
		setTextureName(Core.MODID + ":" + "SkillArmor-" + type);
		setCreativeTab(Core.tab);
		armorTextureName = "SkillArmor-" + (type == 2 ? 1 : 0);
		GameRegistry.registerItem(this, "SkillArmor-" + type);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return Core.MODID + ":textures/armor/" + armorTextureName + ".png";
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean p_77624_4_) {
		if(getSkill(stack) != null)
		{
			info.add(getSkill(stack).getName());
		}
	}
	
}
