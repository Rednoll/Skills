package inwaiders.redn.rpg.registry;

import inwaiders.redn.rpg.files.CFG;

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
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.EntityMindSpider;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.EntityTaintChicken;
import thaumcraft.common.entities.monster.EntityTaintCow;
import thaumcraft.common.entities.monster.EntityTaintCreeper;
import thaumcraft.common.entities.monster.EntityTaintPig;
import thaumcraft.common.entities.monster.EntityTaintSheep;
import thaumcraft.common.entities.monster.EntityTaintSpider;
import thaumcraft.common.entities.monster.EntityTaintSpore;
import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
import thaumcraft.common.entities.monster.EntityTaintSwarm;
import thaumcraft.common.entities.monster.EntityTaintVillager;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.entities.monster.EntityTaintacleSmall;
import thaumcraft.common.entities.monster.EntityThaumicSlime;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;

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
		if(CFG.thaum)
		{
			initThaum();
		}
	}
	
	private static void initOverworld()
	{
		register(EntityBat.class, 10);
		register(EntityChicken.class, 10);
		register(EntityCow.class, 10);
		register(EntityHorse.class, 10);
		register(EntityMooshroom.class, 50);
		register(EntityOcelot.class, 10);
		register(EntityPig.class, 10);
		register(EntitySheep.class, 10);
		register(EntitySquid.class, 10);
		register(EntityVillager.class, 150);
		register(EntityWolf.class, 25);
		register(EntityCreeper.class, 50);
		register(EntityZombie.class, 50);
		register(EntitySkeleton.class, 50);
		register(EntitySpider.class, 50);
		register(EntityCaveSpider.class, 55);
		register(EntitySlime.class, 70);
		register(EntitySilverfish.class, 65);
		register(EntityWolf.class, 40);
		register(EntityWitch.class, 70);
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
	
	private static void initThaum()
	{
		register(EntityCultistLeader.class, 6000);
		register(EntityEldritchWarden.class, 6000);
		register(EntityBrainyZombie.class, 55);
		register(EntityCultistCleric.class, 100);
		register(EntityCultistKnight.class, 110);
		register(EntityEldritchCrab.class, 150);
		register(EntityEldritchGuardian.class, 300);
		register(EntityFireBat.class, 70);
		register(EntityGiantBrainyZombie.class, 70);
		register(EntityInhabitedZombie.class, 70);
		register(EntityMindSpider.class, 5);
		register(EntityPech.class, 200);
		register(EntityTaintacle.class, 50);
		register(EntityTaintacleSmall.class, 25);
		register(EntityTaintCow.class, 50);
		register(EntityTaintChicken.class, 50);
		register(EntityTaintCreeper.class, 100);
		register(EntityTaintPig.class, 50);
		register(EntityTaintSheep.class, 50);
		register(EntityTaintSpider.class, 100);
		register(EntityTaintSpore.class, 50);
		register(EntityTaintSporeSwarmer.class, 200);
		register(EntityTaintSwarm.class, 100);
		register(EntityTaintVillager.class, 200);
		register(EntityThaumicSlime.class, 10);
		register(EntityWisp.class, 100);
	}
	
}
