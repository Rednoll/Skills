package inwaiders.redn.rpg.files.nbt;

import inwaiders.redn.rpg.files.json.PlayerJson.BankSkill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PlayerNbt {
	// Names
	public static final String TAGNAME = "SkillEngine";
	// Main
	public static final String HOTBAR = "hotbar";// Object of 6 strings, named
													// by their position
	public static final String BANK = "bank";// Array
	public static final String TEAM = "team";// String
	public static final String LEARNPOINTS = "learnpts";// Int
	public static final String XP = "XP";// Int
	// BankSkill|Main
	public static final String LEVEL = "lvl";
	// Bank skill
	public static final String NAME = "name";// Int
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

	public void resetNBT() {
		initNbt();
		ep.getEntityData().setTag(TAGNAME, nbt);
	}

	private void initNbt() {
		if (!nbt.hasKey(BANK)) {
			initBank();
		}
		if (!nbt.hasKey(HOTBAR)) {
			initHotbar();
		}
		initMain();
	}

	private void initHotbar() {
		NBTTagCompound hotbar = new NBTTagCompound();
		for (int i = 0; i < 6; i++) {
			hotbar.setString(i + "", "NONE");
		}
		nbt.setTag(HOTBAR, hotbar);
	}

	private void initBank() {
		nbt.setTag(BANK, new NBTTagList());
	}

	private void initMain() {
		if (!nbt.hasKey(TEAM)) {
			nbt.setString(TEAM, "ANY");
		}
		if (!nbt.hasKey(LEVEL)) {
			nbt.setInteger(LEVEL, 0);
		}
		if (!nbt.hasKey(XP)) {
			nbt.setInteger(XP, 0);
		}
		if (!nbt.hasKey(LEARNPOINTS)) {
			nbt.setInteger(LEARNPOINTS, 0);
		}
	}

	public void addBank(String name, int lvl, int cd) {
		if (checkBank(name)) {
			NBTTagCompound skill = new NBTTagCompound();
			skill.setString(NAME, name);
			skill.setInteger(LEVEL, lvl);
			skill.setInteger(COOLDOWN, cd);
			nbt.getTagList(BANK, 10).appendTag(skill);
		}
	}

	private boolean checkBank(String name) {
		NBTTagList bank = nbt.getTagList(BANK, 10);
		for (int i = 0; i < bank.tagCount(); i++) {
			if (bank.getCompoundTagAt(i).getString(NAME).equals(name)) {
				return false;
			}
		}
		return true;
	}

	public BankSkill getBank(int slot) {
		NBTTagCompound skill = nbt.getTagList(BANK, 10).getCompoundTagAt(slot);
		return skill.hasKey(NAME) ? new BankSkill(skill.getString(NAME), skill.getInteger(LEVEL), skill.getInteger(COOLDOWN)) : null;
	}

	public int getBankSize() {
		return nbt.getTagList(BANK, 10).tagCount();
	}

	public void setHotbar(int slot, String name) {
		nbt.getCompoundTag(HOTBAR).setString(slot + "", name);
	}

	public String getHotbar(int slot) {
		return nbt.getCompoundTag(HOTBAR).getString(slot + "");
	}

	public String[] getHotbarSkills() {
		NBTTagCompound hotbar = nbt.getCompoundTag(HOTBAR);
		String[] ret = new String[6];
		for (int i = 0; i < 6; i++) {
			ret[i] = hotbar.getString(i + "");
		}
		return ret;
	}

	public void setHotbarSkills(String[] skills) {
		NBTTagCompound hotbar = nbt.getCompoundTag(HOTBAR);
		for (int i = 0; i < 6; i++) {
			hotbar.setString(i + "", skills[i]);
		}
	}

	public void setTeam(String team) {
		nbt.setString(TEAM, team);
	}

	public String getTeam() {
		return nbt.getString(TEAM);
	}

	public void setLearnPoints(int pts) {
		nbt.setInteger(LEARNPOINTS, pts);
	}

	public int getLearnPoints() {
		return nbt.getInteger(LEARNPOINTS);
	}

	public void setXp(int xp) {
		nbt.setInteger(XP, xp);
	}

	public int getXp() {
		return nbt.getInteger(XP);
	}

	public void setLevel(int lvl) {
		nbt.setInteger(LEVEL, lvl);
	}

	public int getLevel() {
		return nbt.getInteger(LEVEL);
	}

}
