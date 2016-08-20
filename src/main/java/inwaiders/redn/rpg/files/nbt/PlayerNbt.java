package inwaiders.redn.rpg.files.nbt;

import inwaiders.redn.rpg.files.json.PlayerJson.BankSkill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PlayerNbt {
	// Names
	public static final String TAGNAME = "SkillEngine";
	// Main
	public static final String HOTBAR = "hotbar";// int array of 6
	public static final String BANK = "bank";// Array
	public static final String TEAM = "team";// String
	public static final String LEARNPOINTS = "learnpts";// Int
	public static final String XP = "XP";// Int
	//BankSkill|Main
	public static final String LEVEL = "lvl";
	// Bank skill
	public static final String ID = "id";// Int
	public static final String COOLDOWN = "cd";// Int

	public NBTTagCompound nbt;
	protected EntityPlayer ep;
	public PlayerNbt(EntityPlayer e) {
		ep = e;
		if (!e.getEntityData().hasKey(TAGNAME)) {
			nbt = new NBTTagCompound();
			e.getEntityData().setTag(TAGNAME, nbt);
		} else {
			nbt = e.getEntityData().getCompoundTag(TAGNAME);
		}
		initNbt();
	}

	public void resetNBT()
	{
		initNbt();
		ep.getEntityData().setTag(TAGNAME, nbt);
	}
	
	private void initNbt() {
		if(!nbt.hasKey(BANK))
		{
			initBank();
		}
		if(!nbt.hasKey(HOTBAR))
		{
			initHotbar();
		}
		initMain();
	}

	private void initHotbar() {
		nbt.setIntArray(HOTBAR, new int[] {-1, -1, -1, -1, -1, -1});
	}
	
	private void initBank() {
		nbt.setTag(BANK, new NBTTagList());
	}
	
	private void initMain() {
		if(!nbt.hasKey(TEAM))
		{
			nbt.setString(TEAM, "ANY");
		}
		if(!nbt.hasKey(LEVEL))
		{
			nbt.setInteger(LEVEL, 0);
		}
		if(!nbt.hasKey(XP))
		{
			nbt.setInteger(XP, 0);
		}
		if(!nbt.hasKey(LEARNPOINTS))
		{
			nbt.setInteger(LEARNPOINTS, 0);
		}
	}
	
	public void addBank(int id, int lvl, int cd)
	{
		NBTTagCompound skill = new NBTTagCompound();
		skill.setInteger(ID, id);
		skill.setInteger(LEVEL, lvl);
		skill.setInteger(COOLDOWN, cd);
		nbt.getTagList(BANK, 10).appendTag(skill);
	}
	
	public BankSkill getBank(int slot)
	{
		NBTTagCompound skill = nbt.getTagList(BANK, 10).getCompoundTagAt(slot);
		return new BankSkill(skill.getInteger(ID), skill.getInteger(LEVEL), skill.getInteger(COOLDOWN));
	}
	
	public int getBankSize()
	{
		return nbt.getTagList(BANK, 10).tagCount();
	}
	
	public void setHotbar(int slot, int id)
	{
		nbt.getIntArray(HOTBAR)[slot] = id;
	}
	
	public int getHotbar(int slot)
	{
		return nbt.getIntArray(HOTBAR)[slot];
	}
	
	public int[] getHotbarSkills()
	{
		return nbt.getIntArray(HOTBAR);
	}
	
	public void setHotbarSkills(int[] skills)
	{
		nbt.setIntArray(HOTBAR, skills);
	}
	
	public void setTeam(String team)
	{
		nbt.setString(TEAM, team);
	}
	
	public String getTeam()
	{
		return nbt.getString(TEAM);
	}
	
	public void setLearnPoints(int pts)
	{
		nbt.setInteger(LEARNPOINTS, pts);
	}
	
	public int getLearnPoints()
	{
		return nbt.getInteger(LEARNPOINTS);
	}
	
	public void setXp(int xp)
	{
		nbt.setInteger(XP, xp);
	}
	
	public int getXp()
	{
		return nbt.getInteger(XP);
	}
	
	public void setLevel(int lvl)
	{
		nbt.setInteger(LEVEL, lvl);
	}
	
	public int getLevel()
	{
		return nbt.getInteger(LEVEL);
	}
	
}
