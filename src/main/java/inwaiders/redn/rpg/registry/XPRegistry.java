package inwaiders.redn.rpg.registry;

import java.util.HashMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;

public class XPRegistry {
	private static HashMap<Class<? extends EntityLivingBase>, Integer> xpMap = new HashMap<Class<? extends EntityLivingBase>, Integer>();
	
	public static void register(Class<? extends EntityLivingBase> e, int xp)
	{
		xpMap.put(e, xp);
	}
	
	public static int getXpFor(Class<? extends EntityLivingBase> e)
	{
		return xpMap.containsKey(e) ? xpMap.get(e) : 0;
	}
	
	public static int getXpFor(EntityLivingBase e)
	{
		return getXpFor(e.getClass());
	}
	
	public static void init()
	{
		register(EntityCreeper.class, 50);
	}
	
}
