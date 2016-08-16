package Inwaiders.redn.teamengine.teams;

import net.minecraft.entity.player.EntityPlayer;

public class TeamEngineClientProvider {
	
	String team = "ANY";
	
	EntityPlayer ep;
	
	public TeamEngineClientProvider(String team){
		this.team = team;
	}
	
	public void updateEngine(EntityPlayer ep){
		this.ep = ep;

	}
	
	public String getTeam(){
		return team;
	}
	
	public void setTeam(String team){
		this.team = team;
	}
	
}
