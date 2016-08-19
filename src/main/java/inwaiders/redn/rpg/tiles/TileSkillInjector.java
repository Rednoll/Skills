package inwaiders.redn.rpg.tiles;

import net.minecraft.nbt.NBTTagCompound;
import inwaiders.redn.rpg.utils.skillitem.ISkillContainerItem;
import inwaiders.redn.rpg.utils.skillitem.ISkillItem;

public class TileSkillInjector extends TileWithInventory {
	public static final int MAX_WORK_TIME = 400;
	public static final int GUI_BAR_HEIGHT = 19;
	private int workTime = 0;
	
	public TileSkillInjector() {
		super(2, true);
	}
	
	public int getWorkTimeScaled()
	{
		return workTime * GUI_BAR_HEIGHT / MAX_WORK_TIME;
	}
	
	@Override
	public void updateEntity() {
		if(canWork())
		{
			if(workTime++ >= MAX_WORK_TIME)
			{
				processItem();
			}
		}
		else
		{
			workTime = 0;
		}
	}
	
	private void processItem()
	{
		ISkillContainerItem container = (ISkillContainerItem) getStackInSlot(1).getItem();
		container.setSkill(getStackInSlot(1), ((ISkillItem) getStackInSlot(0).getItem()).getSkill(getStackInSlot(0)));
		decrStackSize(0, 1);
		workTime = 0;
	}
	
	private boolean canWork()
	{
		return getStackInSlot(0) != null && getStackInSlot(1) != null && getStackInSlot(0).getItem() instanceof ISkillItem && getStackInSlot(1).getItem() instanceof ISkillContainerItem;
	}
	
	@Override
	protected NBTTagCompound getSyncNbt() {
		NBTTagCompound nbt = super.getSyncNbt();
		nbt.setInteger("WORK", workTime);
		return nbt;
	}
	
	@Override
	protected void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		workTime = nbt.getInteger("WORK");
	}
	
	
}
