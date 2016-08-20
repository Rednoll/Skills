package inwaiders.redn.rpg.storage.client;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.files.json.PlayerJson.BankSkill;
import inwaiders.redn.rpg.files.nbt.PlayerNbt;
import inwaiders.redn.rpg.registry.ItemRegistry;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;
import inwaiders.redn.rpg.skills.item.ItemSkillBase;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.ItemSkillType;
import inwaiders.redn.rpg.skills.item.ItemSkillBase.PassiveEffect;
import inwaiders.redn.rpg.utils.MiscUtils;
import inwaiders.redn.rpg.utils.skillitem.ISkillContainerItem;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerInfoClient extends PlayerNbt {
	protected HashMap<String, BaseSkill> bankSkills = new HashMap<String, BaseSkill>();
	protected String playername;

	public PlayerInfoClient(EntityPlayer ep) {
		super(ep);
		playername = ep.getCommandSenderName();
	}

	public HashMap<String, BaseSkill> getSkills() {
		return bankSkills;
	}

	public void setSkills(HashMap<String, BaseSkill> skills) {
		skills.forEach((n, skill) -> System.out.println(n + "/" + skill));
		this.bankSkills = skills;
	}

	protected void updateCoolDown() {
		bankSkills.forEach((name, skill) -> {
			if (skill.getCoolDown() > 0)
				skill.setCoolDown(skill.getCoolDown() - 1);
		});
	}

	protected void updateWhilesSkills() {
		bankSkills.forEach((name, skill) -> {
			if (skill.isPassive() == 1)
				skill.preWhileUpdate(ep);
		});
	}

	protected void updateCastSkills() {
		bankSkills.forEach((name, skill) -> skill.preCast(ep));
	}

	public void activateSkill(String name, EntityPlayer ep) {
		if (getSkillByName(name) != null) {
			getSkillByName(name).startCasting();
		}
	}

	public BaseSkill getSkillByName(String name) {
		return bankSkills.get(name);
	}

	public boolean hasSkill(String name) {
		return getSkillByName(name) != null;
	}

	public void learnSkill(String name) {
		
		if (!bankSkills.containsKey(name)) {
			this.bankSkills.put(name, SkillsRegistry.getSkillByName(name));
		} else {
			BaseSkill skill = this.bankSkills.get(name);
			skill.setLevel(skill.getLevel() + 1);
		}
	}

	protected void resetPlayer() {
		if (ep == null) {
			ep = Minecraft.getMinecraft().thePlayer;
		}
	}

	protected void updateXp() {
		int level = getLevel();
		int xp = getXp();
		int nextXp = getNextXp(level + 1);
		if (xp >= nextXp) {
			xp -= nextXp;
			setLearnPoints(getLearnPoints() + 1);
			
			if(++level % 5 == 0 || level++ == 1 || level++ == 3){
				ItemStack scroll = new ItemStack(ItemRegistry.skillScroll);
				scroll.getItem().onCreated(scroll, ep.worldObj, ep);
				MiscUtils.addItemToPlayer(ep, scroll);
			}
			setLevel(level);
			setXp(xp);
		}
	}
	
	public int getNextXp(int lvl)
	{
		if(Constants.DEFAUL_NEXT_XP.length > lvl)
		{
			return Constants.DEFAUL_NEXT_XP[lvl];
		}
		return (int) ((int) getNextXp(lvl - 1) * 1.2);
	}

	protected void updateItemSkills(LivingHurtEvent e, ItemSkillType type) {
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

	private void updateItemSkill(ItemSkillBase skill, LivingHurtEvent e) {
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
					skill.preWearerHited(e);
					break;
				}
				case HITTARGET: {
					skill.preTargetHited(e);
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void updateOnWearerHurtSkills(LivingHurtEvent e) {
		updateItemSkills(e, ItemSkillType.HITWEARER);
	}

	public void updateOnTargetHurtSkills(LivingHurtEvent e) {
		updateItemSkills(e, ItemSkillType.HITTARGET);
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
	
	public void addXp(int xp) {
		setXp(getXp() + xp);
	}
	
	public void saveBank()
	{
		bankSkills.forEach((name, skill) -> addBank(skill.getName(), skill.getLevel(), skill.getCoolDown()));
	}
	
	public void loadBank()
	{
		int i = 0;
		for(BankSkill skill = getBank(i); skill != null; skill = getBank(++i))
		{
			BaseSkill result = SkillsRegistry.getSkillByName(skill.name);
			result.setLevel(skill.lvl);
			result.setCoolDown(skill.cd);
			bankSkills.put(result.getName(), result);
		}
	}

}
