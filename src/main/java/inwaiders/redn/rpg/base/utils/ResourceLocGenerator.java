package inwaiders.redn.rpg.base.utils;

import net.minecraft.util.ResourceLocation;

public class ResourceLocGenerator
{
	private String modid, prefix, postfix;
	public ResourceLocGenerator(String modid, String prefix, String postfix)
	{
		this.modid = modid;
		this.prefix = prefix;
		this.postfix = postfix;
	}
	
	public ResourceLocGenerator(String modid, String prefix)
	{
		this(modid, prefix, "");
	}
	
	public ResourceLocGenerator(String modid)
	{
		this(modid, "");
	}
	
	public ResourceLocation generate(String path)
	{
		return new ResourceLocation(modid, (prefix.endsWith("/") ? prefix : (prefix + "/")) + path + postfix);
	}
}
