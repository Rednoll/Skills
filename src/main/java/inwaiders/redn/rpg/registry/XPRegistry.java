package inwaiders.redn.rpg.registry;

import java.util.HashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;

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
		initOverworld();
		initNether();
		initEnd();
	}
	
	private static void initOverworld()
	{
		register(EntityCreeper.class, 50);
		register(EntityZombie.class, 50);
		register(EntitySkeleton.class, 50);
		register(EntitySpider.class, 50);
		register(EntityCaveSpider.class, 55);
		register(EntitySlime.class, 70);
		register(EntitySilverfish.class, 65);
		register(EntityWolf.class, 40);
		register(EntityWitch.class, 70);
		register(EntityPlayer.class, 100);
	}
	
	private static void initNether()
	{
		register(EntityBlaze.class, 65);
		register(EntityPigZombie.class, 65);
		register(EntityMagmaCube.class, 30);
		register(EntityGhast.class, 60);
		register(EntityWither.class, 5000);
	}
	
	private static void initEnd()
	{
		register(EntityEnderman.class, 70);
		register(EntityDragon.class, 10000);
	}
	
}
