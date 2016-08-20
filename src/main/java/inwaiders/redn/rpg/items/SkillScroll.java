package inwaiders.redn.rpg.items;

import inwaiders.redn.rpg.core.Core;
import inwaiders.redn.rpg.gui.GuiHandler;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.utils.ItemNBT;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkillScroll extends ItemBase {
	
	public SkillScroll() {
		super("SkillScroll");
	}
	
	@Override
	public void addInformation(ItemStack s, EntityPlayer p, List info, boolean p_77624_4_) {
		BaseSkill skill = SkillsRegistry.getSkillByName(ItemNBT.getString(s, "Skill", "NONE"));
		if(skill != null)
		info.add(skill.getName());
		setMaxStackSize(1);
	}
	
	@Override
	public void onCreated(ItemStack s, World w, EntityPlayer p) {
		String name;	
		while(SkillsRegistry.getSkillByName(name = SkillsRegistry.getRandomSkillName()) == null)
		{
			
		} 
		ItemNBT.setString(s, "Skill", name);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack s, World w, EntityPlayer p) {
		p.openGui(Core.MODID, GuiHandler.LEARN_ID, w, (int) p.posX, (int) p.posY, (int) p.posZ);
		return s;
	}
	
}
