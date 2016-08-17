package inwaiders.redn.teamengine.teams;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerTeamClient {
	
	String team = "ANY";
	
	EntityPlayer ep;
	
	public PlayerTeamClient(String team){
		this.team = team;
	}
	
	public void update(EntityPlayer ep){
		this.ep = ep;

	}
	
	public String getTeam(){
		return team;
	}
	
	public void setTeam(String team){
		this.team = team;
	}
	
}
