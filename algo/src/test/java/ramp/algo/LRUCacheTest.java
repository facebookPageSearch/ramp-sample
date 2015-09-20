/**
 * 
 */
package ramp.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Rama Palaniappan
 * @since 19-Sep-2015
 */
public class LRUCacheTest {
	@Test
	public void testCache() {
		LRUCache<String, String> cache = new LRUCache<String, String>(1);
		String key1 = "key1";
		String value1 = "value1";
		String actual = cache.get(key1);
		Assert.assertNull(actual);
		cache.put(key1, value1);
		actual = cache.get(key1);
		Assert.assertEquals(actual, value1);
		String key2 = "key2";
		String value2 = "value2";
		//This should evict key1 as the LRU cache size is 1
		cache.put(key2, value2);
		actual = cache.get(key1);
		Assert.assertNull(actual);
		double cacheHitRatio = cache.cacheHitRatio();
		Assert.assertEquals(cacheHitRatio, 1/(double)3);
	}
}
