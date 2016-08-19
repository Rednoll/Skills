package inwaiders.redn.rpg.storage.client;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.ItemSkillType;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.PassiveEffect;
import inwaiders.redn.rpg.utils.skillitem.ISkillContainerItem;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerInfoClient {
	protected HashMap<Integer, BaseSkill> bankSkills = new HashMap<Integer, BaseSkill>();
	protected EntityPlayer ep;
	protected String playername;
	protected String team = "ANY";
	public int[] hotbarSkills;
	protected int lpoints = 0;
	protected int xp = 0;
	protected int nextXp = Constants.DEFAUL_NEXT_XP;

	public PlayerInfoClient(EntityPlayer ep) {
		this.ep = ep;
		playername = ep.getCommandSenderName();
		hotbarSkills = new int[6];
	}

	public HashMap<Integer, BaseSkill> getSkills() {
		return bankSkills;
	}

	public void setSkills(HashMap<Integer, BaseSkill> skills) {
		this.bankSkills = skills;
	}

	protected void updateCoolDown() {
		bankSkills.forEach((id, skill) -> {
			if (skill.getCoolDown() > 0)
				skill.setCoolDown(skill.getCoolDown() - 1);
		});
	}

	protected void updateWhilesSkills() {
		bankSkills.forEach((id, skill) -> {
			if (skill.isPassive() == 1)
				skill.preWhileUpdate(ep);
		});
	}

	protected void updateCastSkills() {
		bankSkills.forEach((id, skill) -> skill.preCast(ep));
	}

	public void activateSkill(int id, EntityPlayer ep) {
		if (getSkillById(id) != null) {
			getSkillById(id).startCasting();
		}
	}

	public BaseSkill getSkillById(int id) {
		return bankSkills.get(id);
	}

	public boolean hasSkill(int id) {
		return getSkillById(id) != null;
	}

	public void learnSkill(int id) {

		if (!bankSkills.containsKey(id)) {
			this.bankSkills.put(id, SkillsRegistry.getSkillById(id));
		} else {
			BaseSkill skill = this.bankSkills.get(id);
			skill.setLevel(skill.getLevel() + 1);
		}
	}

	protected void resetPlayer() {
		if (ep == null) {
			ep = Minecraft.getMinecraft().thePlayer;
		}
	}

	protected void updateXp() {
		if (xp >= nextXp) {
			xp -= nextXp;
			if (nextXp < Integer.MAX_VALUE / 2) {
				nextXp *= 2;
			} else {
				nextXp = Integer.MAX_VALUE;
			}
			lpoints++;
		}
	}

	protected void updateItemSkills(Entity e, ItemSkillType type) {
		for (int i = 0; i <= 4; i++) {
			ItemStack item = ep.getEquipmentInSlot(i);
			if (item != null && item.getItem() instanceof ISkillContainerItem) {
				ISkillContainerItem container = (ISkillContainerItem) item.getItem();
				ItemSkillBase skill = container.getSkill(item);
				if (skill != null && skill.getType() == type)
					updateItemSkill(container.getSkill(item), e);
			}
		}
		// TODO Integrate with baubles
	}

	private void updateItemSkill(ItemSkillBase skill, @Nullable Entity e) {
		if (skill != null) {
			if (skill.getCd() > 0) {
				skill.decrCd();
			}
			switch (skill.getType()) {
				case TICK: {
					skill.preWhileUpdate(ep);
					break;
				}
				case HITWEARER: {
					skill.preWearerHited(ep, e);
					break;
				}
				case HITTARGET: {
					skill.preTargetHited(ep, e);
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void updateOnWearerHurtSkills(Entity attacker) {
		updateItemSkills(attacker, ItemSkillType.HITWEARER);
	}

	public void updateOnTargetHurtSkills(Entity target) {
		updateItemSkills(target, ItemSkillType.HITTARGET);
	}
	
	public PassiveEffect getPassiveEffects()
	{
		PassiveEffect ret = new PassiveEffect();
		for (int i = 0; i <= 4; i++) {
			ItemStack item = ep.getEquipmentInSlot(i);
			if (item != null && item.getItem() instanceof ISkillContainerItem) {
				ISkillContainerItem container = (ISkillContainerItem) item.getItem();
				ItemSkillBase skill = container.getSkill(item);
				if (skill != null && skill.getType() == ItemSkillType.PASSIVE)
				{
					PassiveEffect e = skill.getPassiveEffect(ep);
					ret.casttimedecrease += e.casttimedecrease;
					ret.cddecrease += e.cddecrease;
					ret.intevaldecrease += e.intevaldecrease;
					ret.manadecrease += e.manadecrease;
					ret.radiusincrease += e.radiusincrease;
				}
			}
		}
		return ret;
	}
	

	public void sync() {

	}

	public void update(EntityPlayer ep) {
		this.ep = ep;
		updateCoolDown();
		updateWhilesSkills();
		updateCastSkills();
		updateItemSkills(null, ItemSkillType.TICK);
		resetPlayer();
		updateXp();
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setSkillIdByPos(int pos, int id) {
		// System.out.println("Setting " + pos + " to " + id);
		hotbarSkills[pos] = id;
	}

	public int getSkillIdByPos(int pos) {
		return hotbarSkills[pos];
	}

	public void setLearnPoints(int i) {
		this.lpoints = i;
	}

	public int getLearnPoints() {
		return this.lpoints;
	}

	/**
	 * @return 0 if can learn, 1 if max lvl reached, 2 id not enough points
	 */
	public int canLearn(BaseSkill skill) {
		return getLearnPoints() >= skill.getPrice().getPrice() ? skill.getLevel() + 1 < skill.getMaxLvl() ? 0 : 1 : 2;
	}

	public void learn(BaseSkill skill) {
		if (canLearn(skill) == 0)
			this.setLearnPoints(this.getLearnPoints() - skill.getPrice().getPrice());
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void addXp(int xp) {
		this.xp += xp;
	}

	public void setXpForNextLevel(int nextXp) {
		this.nextXp = nextXp;
	}

	public int getXpForNextLevel() {
		return nextXp;
	}

}
