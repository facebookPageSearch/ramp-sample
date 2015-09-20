/**
 * 
 */
package ramp.algo;

import java.util.LinkedHashMap;

/**
 * @author Rama Palaniappan
 * @since 19-Sep-2015
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 1L;
	private int maxEntries = 0;

	public LRUCache(int size) {
		super(size, 1.0f, true);
		this.maxEntries = size;
	}

	private long totalHits = 0;
	private long cacheHits = 0;
	//Override for cache hit rate
	public synchronized V get(Object key) {
		totalHits++;
		V value = super.get(key);
		if (value != null) {
			cacheHits++;
		}
		return value;
	}
	
	public synchronized double cacheHitRatio() {
		if (totalHits == 0) {
			return 0;
		}
		return cacheHits/(double)totalHits;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		if (size() > maxEntries) {
			return true;
		}
		return false;
	}

}
