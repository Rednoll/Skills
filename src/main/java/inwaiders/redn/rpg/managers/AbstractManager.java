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
		if (!values.containsKey(key)) {
			set(key, getDefaultValue(key));
		}
		return values.get(key);
	}

	protected abstract V getDefaultValue(K key);
}
