package inwaiders.redn.rpg.managers;

import java.util.Collection;
import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;

public abstract class AbstractManager<K, V> {
	protected HashMap<K, V> values;

	public AbstractManager() {
		values = new HashMap<K, V>();
	}
	
	public void set(K key, V value) {
		//System.out.println(key);
		StackTraceElement[] s = Thread.currentThread().getStackTrace();
		for(StackTraceElement e : s)
		{
			//System.out.println(e);
		}
		values.remove(key);
		if(value != null)
		values.put(key, value);
	}

	public Collection<V> getValues() {
		return values.values();
	}

	public V get(K key) {
		if (key == null) {
			return null;
		}
		//System.out.println(key + "/" + values.get(key));
		if (!values.containsKey(key)) {
			V defaultv = getDefaultValue(key);
			System.out.println("defaulting");
			System.out.println("Setetd " + key + " to " + defaultv);
			values.remove(key);
			values.put(key, defaultv);
			System.out.println("def");
		}
		return values.get(key);
	}

	protected abstract V getDefaultValue(K key);
}
