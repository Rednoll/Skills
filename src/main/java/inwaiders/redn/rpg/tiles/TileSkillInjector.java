package inwaiders.redn.rpg.tiles;

import inwaiders.redn.rpg.utils.skillitem.ISkillContainerItem;
import inwaiders.redn.rpg.utils.skillitem.ISkillItem;

public class TileSkillInjector extends TileWithInventory {
	
	public TileSkillInjector() {
		super(2);
	}
	
	@Override
	public void updateEntity() {
		if(getStackInSlot(0) != null && getStackInSlot(1) != null)
		{
			ISkillContainerItem container = (ISkillContainerItem) getStackInSlot(1).getItem();
			container.setSkill(getStackInSlot(1), ((ISkillItem) getStackInSlot(0).getItem()).getSkill(getStackInSlot(0)));
			setInventorySlotContents(0, null);
		}
	}
	
}
