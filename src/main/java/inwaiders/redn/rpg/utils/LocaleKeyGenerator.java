package inwaiders.redn.rpg.utils;

public class LocaleKeyGenerator
{
	private final String prefix, postfix;
	public LocaleKeyGenerator(String prefix, String postfix)
	{
		this.prefix = prefix;
		this.postfix = postfix;
	}
	
	public LocaleKeyGenerator(String prefix)
	{
		this(prefix, "");
	}
	
	public String generate(String key)
	{
		return prefix + key + postfix;
	}
}
