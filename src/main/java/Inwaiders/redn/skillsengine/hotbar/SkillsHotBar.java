package Inwaiders.redn.skillsengine.hotbar;

import java.util.ArrayList;
import java.util.List;

public class SkillsHotBar {

	public int[] skills = new int[6];
	
	public SkillsHotBar(){
		
		for(int i = 0;i<6;i++){
			skills[i] = 0;
		}
		
		skills[1] = 1;
	}
	
	public void setSkillByIdByPos(int p, int i){
		skills[p] = i;
	}
	
	public int getSkillIdByPos(int i){
		return skills[i];
	}
}
