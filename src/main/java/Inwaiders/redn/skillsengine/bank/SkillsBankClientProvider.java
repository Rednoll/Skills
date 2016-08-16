package Inwaiders.redn.skillsengine.bank;

import java.util.ArrayList;
import java.util.List;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import Inwaiders.redn.skillsengine.examples.BaseSkill;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;


public class SkillsBankClientProvider {

	List<BaseSkill> skills = new ArrayList();
	
	EntityPlayer ep;
	
	public SkillsBankClientProvider(EntityPlayer ep){
		
		this.ep = ep;
		
	}
	
	public void updateEngine(){
		updateCoolDown();
		updateWhilesSkills();
		updateCastSkills();
		reflection();
	}
	
	public void updateCoolDown(){
		
		for(int i = 0;i<skills.size();i++){
			
			if(skills.get(i).getCoolDown() > 0)
				skills.get(i).setCoolDown(skills.get(i).getCoolDown()-1);
		}
		
	}
	
	public void updateWhilesSkills(){
		
		for(int i = 0;i<skills.size();i++){
			
			if(skills.get(i).isPassive() == 1)
				skills.get(i).preWhileUpdate(ep);
		}
		
	}
	
	public void updateCastSkills(){
		
		for(int i = 0;i<skills.size();i++){
			
			skills.get(i).preCast(ep);
		}
		
	}
	
	public int getCoolDownById(int id){
		return getSkillById(id) != null ? getSkillById(id).getCoolDown() : 0;
	}
	
	public int isAuraById(int id){
		return getSkillById(id) != null ? getSkillById(id).isAura() : 0;
	}
	
	public int getCastById(int id){
		return getSkillById(id) != null ? getSkillById(id).getCast() : 0;
	}
	
	public int getMaxCastById(int id, int i){
		return getSkillById(id) != null ? getSkillById(id).getMaxCastByLevel(i) : 0;
	}
	
	public int getRadiusByIdByLevle(int id, int i){
		return getSkillById(id) != null ? getSkillById(id).getRadiusByLevel(i) : 0;
	}
	
	public int getDamageByIdByLevel(int id, int i){
		return getSkillById(id) != null ? getSkillById(id).getDamageByLevel(i) : 0;
	}
	
	public int getMaxCoolDownByIdByLevel(int id, int i){
		return getSkillById(id) != null ? getSkillById(id).getMaxCoolDownByLevel(i) : 0;
	}
	
	public int getLevelById(int id){
		return getSkillById(id) != null ? getSkillById(id).getLevel() : 1;
	}
	
	public int isPassiveById(int id){
		return getSkillById(id) != null ? getSkillById(id).isPassive() : 0;
	}
	
	public ResourceLocation getTextureById(int id){
		return getSkillById(id) != null ? getSkillById(id).getTexture() : null;
	}
	
	public boolean hasSkill(int id){
		return getSkillById(id) != null ? true : false;
	}
	
	public void activeSkill(int id, EntityPlayer ep){
		if(getSkillById(id) != null){
			getSkillById(id).startCasting();
		}
	}
	
	public BaseSkill getSkillById(int id){
		
		for(int i = 0;i<skills.size();i++){
			
			if(skills.get(i).getId() == id)
				return skills.get(i);
		}
		
		return null;
	}
	
	public void learnSkill(int id){
		try {
			this.skills.add(SkillsStaticTable.getSkillById(id));
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
	}
	
	public void reflection(){
		ep = ep.worldObj.getPlayerEntityByName(ep.getCommandSenderName());
	}
	
	public void synhronaizer(){
		
	}
}
