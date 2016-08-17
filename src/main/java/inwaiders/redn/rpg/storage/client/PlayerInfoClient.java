package inwaiders.redn.rpg.storage.client;

import inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import inwaiders.redn.rpg.registry.SkillsRegistry;
import inwaiders.redn.rpg.skills.BaseSkill;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class PlayerInfoClient
{
	protected List<BaseSkill> bankSkills = new ArrayList();
	protected EntityPlayer ep;
	protected String playername;
	protected String team = "ANY";
	public int[] hotbarSkills;
	protected int lpoints = 0;
	public PlayerInfoClient(EntityPlayer ep)
	{
		this.ep = ep;
		playername = ep.getCommandSenderName();
		hotbarSkills = new int[6];
	}

	public List<BaseSkill> getSkills() {
		return bankSkills;
	}
	
	public void setSkills(List<BaseSkill> skills) {
		this.bankSkills = skills;
	}

	public void updateCoolDown()
	{
		for (int i = 0; i < bankSkills.size(); i++)
		{

			if (bankSkills.get(i).getCoolDown() > 0)
				bankSkills.get(i).setCoolDown(bankSkills.get(i).getCoolDown() - 1);
		}
	}

	public void updateWhilesSkills()
	{
		for (int i = 0; i < bankSkills.size(); i++)
		{

			if (bankSkills.get(i).isPassive() == 1)
				bankSkills.get(i).preWhileUpdate(ep);
		}
	}

	public void updateCastSkills()
	{
		for (int i = 0; i < bankSkills.size(); i++)
		{

			bankSkills.get(i).preCast(ep);
		}
	}

	public void activateSkill(int id, EntityPlayer ep)
	{
		if (getSkillById(id) != null)
		{
			getSkillById(id).startCasting();
		}
	}

	public BaseSkill getSkillById(int id)
	{

		for (int i = 0; i < bankSkills.size(); i++)
		{

			if (bankSkills.get(i).getId() == id)
				return bankSkills.get(i);
		}

		return null;
	}

	public boolean hasSkill(int id)
	{
		return getSkillById(id) != null;
	}
	
	public void learnSkill(int id)
	{
		this.bankSkills.add(SkillsRegistry.getSkillById(id));
	}

	public void resetPlayer()
	{
		if(ep == null)
		{
			ep = Minecraft.getMinecraft().thePlayer;
		}
	}

	public void sync()
	{

	}
	
	public void update(EntityPlayer ep){
		this.ep = ep;
		updateCoolDown();
		updateWhilesSkills();
		updateCastSkills();
		resetPlayer();
	}
	
	public String getTeam(){
		return team;
	}
	
	public void setTeam(String team){
		this.team = team;
	}
	
	public void setSkillByIdByPos(int pos, int id){
		hotbarSkills[pos] = id;
	}
	
	public int getSkillIdByPos(int pos){
		return hotbarSkills[pos];
	}
	
	public void setLearnPoints(int i){
		this.lpoints = i;
	}
	
	public int getLearnPoints(){
		return this.lpoints;
	}
	
	public boolean canLearn(int price){
		return this.getLearnPoints() - price >= 0 ? true : false; 
	}
	
	public void learn(int price){
		if(canLearn(price)) 
			this.setLearnPoints(this.getLearnPoints() - price);
	}
	
}
