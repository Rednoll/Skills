package inwaiders.redn.skillsengine.hotbar;

import java.util.ArrayList;
import java.util.List;

public class SkillsHotBar {

	public final int[] skills;
	
	public SkillsHotBar(){	
		skills = new int[6];
	}
	
	public void setSkillByIdByPos(int pos, int id){
		skills[pos] = id;
	}
	
	public int getSkillIdByPos(int pos){
		return skills[pos];
	}
}
