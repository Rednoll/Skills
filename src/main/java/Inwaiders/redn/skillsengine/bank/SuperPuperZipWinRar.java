package Inwaiders.redn.skillsengine.bank;

public class SuperPuperZipWinRar {

	//крч этот класс запихивает скилл в одно число
	
	public static String packing(int[] args){
		
		String result = "";
		
		for(int i = 0;i<args.length;i++){
			if(i > 0)
				result += "_";
			result += args[i];
		}
		
		//result.substring(0);
		
		return result;
	}
	
	public static int getAr(String pack, int ar){
		
		String[] words =  pack.trim().split("_");
		
		return Integer.parseInt(words[ar]);
		
	}
	
}
