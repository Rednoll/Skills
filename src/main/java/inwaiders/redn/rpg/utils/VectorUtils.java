package inwaiders.redn.rpg.utils;

import net.minecraft.util.Vec3;

public class VectorUtils {
	public static Vec3 negate(Vec3 vector)
	{
		return Vec3.createVectorHelper(-vector.xCoord, -vector.yCoord, -vector.zCoord);
	}
}
