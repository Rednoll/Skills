package inwaiders.redn.rpg.managers;

import java.util.Collection;
import java.util.HashMap;

public abstract class AbstractManager<K, V>
{
	protected HashMap<K, V> values = new HashMap<K, V>();
	public void set(K key, V value)
	{
		values.put(key, value);
	}
	
	public Collection<V> getValues()
	{
		return values.values();
	}
	
	public V get(K p)
	{
		if(!values.containsKey(p))
		{
			set(p, getDefaultValue(p));
		}
		return values.get(p);
	}
	
	protected abstract V getDefaultValue(K key);
}
