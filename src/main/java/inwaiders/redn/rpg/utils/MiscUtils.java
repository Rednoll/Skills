package inwaiders.redn.rpg.utils;

import java.util.HashSet;
import java.util.List;

import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class MiscUtils
{
	public static void handleContainerClick(EntityPlayer player, IFluidHandler tile)
	{
		ItemStack container = player.getHeldItem();
		if (!FluidContainerRegistry.isContainer(container))
		{
			return;
		}
		MovingObjectPosition block = getTargetBlock(player.worldObj, player, false);
		ForgeDirection dir = ForgeDirection.getOrientation(block.sideHit);
		if (FluidContainerRegistry.isFilledContainer(container))
		{
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(container);
			if (tile.canFill(dir, fluid.getFluid()))
			{
				tile.fill(dir, fluid, true);
				FluidContainerRegistry.drainFluidContainer(container);
			}
		}
		else
		{
			FluidStack drainresult = tile.drain(dir, FluidContainerRegistry.getContainerCapacity(container), false);
			if (FluidContainerRegistry.fillFluidContainer(drainresult, container) != null)
			{
				tile.drain(dir, FluidContainerRegistry.getContainerCapacity(container), true);
			}
		}
	}
	
	public static void dropItemsOnBreak(World w, int x, int y, int z, IInventory inv)
	{
		int size = inv.getSizeInventory();
		for(int i = 0; i < size; i++)
		{
			ItemStack s = inv.getStackInSlot(i);
			if(s != null)
			w.spawnEntityInWorld(new EntityItem(w, x, y, z, s));
		}
	}

	public static void crashGame(String msg, Throwable e)
	{
		throw new ReportedException(CrashReport.makeCrashReport(e, msg));
	}
	
	public static AxisAlignedBB createAABBFormRadius(double x, double y, double z, int radius)
	{
		return AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
	}
	
	public static void addItemToPlayer(EntityPlayer e, ItemStack s)
	{
		IInventory inv = e.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack slot = inv.getStackInSlot(i);
			if(slot == null)
			{
				inv.setInventorySlotContents(i, s);
				return;
			}
			else
			{
				if(slot.getItem() == s.getItem() && slot.getItemDamage() == s.getItemDamage() && slot.stackSize + s.stackSize <= s.getMaxStackSize())
				{
					inv.setInventorySlotContents(i, new ItemStack(s.getItem(), slot.stackSize + s.stackSize, s.getItemDamage()));
					return;
				}
			}
		}
		e.worldObj.spawnEntityInWorld(new EntityItem(e.worldObj, e.posX, e.posY, e.posZ, s));
	}
	
	/**
	 * @author Azanor
	 */
	public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3)
	{
		float var4 = 1.0F;
		float var5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;

		float var6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;

		double var7 = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;

		double var9 = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.yOffset;

		double var11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;

		Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * 0.017453292F - 3.1415927F);
		float var15 = MathHelper.sin(-var6 * 0.017453292F - 3.1415927F);
		float var16 = -MathHelper.cos(-var5 * 0.017453292F);
		float var17 = MathHelper.sin(-var5 * 0.017453292F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 10.0D;
		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);

		return world.func_147447_a(var13, var23, par3, !par3, false);
	}
	
	public static MovingObjectPosition getPlayerTarget(EntityPlayer player, float range, float border)
	  {
	  HashSet<Entity> excluded = new HashSet<Entity>();
	  excluded.add(player);
	  if(player.ridingEntity!=null)
	    {
	    excluded.add(player.ridingEntity);
	    }
	  float yOffset = player.worldObj.isRemote? 0.f : 1.62f;
	  Vec3 look = player.getLookVec();
	  look.xCoord*=range;
	  look.yCoord*=range;
	  look.zCoord*=range;
	  look.xCoord+=player.posX;
	  look.yCoord+=player.posY+yOffset;
	  look.zCoord+=player.posZ;
	  return tracePath(player.worldObj, player.posX, player.posY+yOffset, player.posZ, look.xCoord, look.yCoord, look.zCoord, border, excluded);
	  }
	 
	public static MovingObjectPosition tracePath(World world, double x, double y, double z, double tx, double ty, double tz, float borderSize, HashSet<Entity> excluded)
	  {
	  Vec3 startVec = Vec3.createVectorHelper(x, y, z);
	  Vec3 endVec = Vec3.createVectorHelper(tx, ty, tz);
	  double minX = x < tx ? x : tx;
	  double minY = y < ty ? y : ty;
	  double minZ = z < tz ? z : tz;
	  double maxX = x > tx ? x : tx;
	  double maxY = y > ty ? y : ty;
	  double maxZ = z > tz ? z : tz;
	  AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ).expand(borderSize, borderSize, borderSize);
	  List<Entity> allEntities = world.getEntitiesWithinAABBExcludingEntity(null, bb);  
	  MovingObjectPosition blockHit = world.rayTraceBlocks(startVec, endVec);
	  startVec = Vec3.createVectorHelper(x, y, z);
	  endVec = Vec3.createVectorHelper(tx, ty, tz);
	  Entity closestHitEntity = null;
	  float closestHit = Float.POSITIVE_INFINITY;
	  float currentHit = 0.f;
	  AxisAlignedBB entityBb;// = ent.getBoundingBox();
	  MovingObjectPosition intercept;
	  for(Entity ent : allEntities)
	    {    
	    if(ent.canBeCollidedWith() && !excluded.contains(ent))
	      {
	      float entBorder =  ent.getCollisionBorderSize();
	      entityBb = ent.boundingBox;
	      if(entityBb!=null)
	        {
	        entityBb = entityBb.expand(entBorder, entBorder, entBorder);
	        intercept = entityBb.calculateIntercept(startVec, endVec);
	        if(intercept!=null)
	          {
	          currentHit = (float) intercept.hitVec.distanceTo(startVec);
	          if(currentHit < closestHit || currentHit==0)
	            {            
	            closestHit = currentHit;
	            closestHitEntity = ent;
	            }
	          }
	        }
	      }
	    }  
	  if(closestHitEntity!=null)
	    {
	    blockHit = new MovingObjectPosition(closestHitEntity);
	    }
	  return blockHit;
	  }
}
