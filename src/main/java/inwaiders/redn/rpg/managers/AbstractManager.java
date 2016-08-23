package inwaiders.redn.rpg.managers;

import java.util.Collection;
import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;

public abstract class AbstractManager<K, V> {
	protected final HashMap<K, V> values = new HashMap<K, V>();

	public void set(K key, V value) {
		System.out.println("Setetd " + key + " to " + value);
		values.remove(key);
		values.put(key, value);
	}

	public Collection<V> getValues() {
		return values.values();
	}

	public V get(K p) {
		if (p == null) {
			return null;
		}
		if (!values.containsKey(p)) {
			V defaultv = getDefaultValue(p);
			System.out.println("defaulting");
			set(p, defaultv);
			System.out.println("def");
		}
		return values.get(p);
	}

	protected abstract V getDefaultValue(K key);
}
