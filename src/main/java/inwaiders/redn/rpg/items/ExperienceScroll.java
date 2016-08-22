package inwaiders.redn.rpg.items;

import inwaiders.redn.rpg.managers.server.PlayerInfoManagerServer;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExperienceScroll extends ItemBase {
	public static final int maxmeta = 10;

	public ExperienceScroll() {
		super("XPScrollRPG");
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item it, CreativeTabs t, List l) {
		for (int i = 0; i < maxmeta; i++) {
			l.add(new ItemStack(it, 1, i));
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p) {
		if (!w.isRemote) {
			PlayerInfoManagerServer.instance.get(p).addXp(100 * (stack.getItemDamage() + 1));
		}
		if (!p.capabilities.isCreativeMode) {
			p.getHeldItem().splitStack(1);
		}
		return stack;
	}

}
