/**
 * 
 */
package ramp.sample.worksheetgen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rama Palaniappan
 * @param <K>
 * @param <V>
 * @since 27-Sep-2015
 */
public class BaseWorksheetEntity<K, V> implements WorksheetEntity {
	private String header = null;
	private Map<K, V> pairs = new HashMap<K, V>();

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the pairs
	 */
	public Map<K, V> getPairs() {
		return pairs;
	}

	/**
	 * @param pairs
	 *            the pairs to set
	 */
	public void setPairs(Map<K, V> pairs) {
		this.pairs = pairs;
	}

	public V put(K key, V value) {
		return pairs.put(key, value);
	}

	public V get(K key) {
		return pairs.get(key);
	}

	public List<String> getQuestions() {
		return getQuestions(null);
	}

	public List<String> getQuestions(String questionSuffix) {
		if (questionSuffix == null) {
			questionSuffix = "";
		}
		List<String> questions = new ArrayList<String>();
		for (Map.Entry<K, V> entry : getPairs().entrySet()) {
			// Map's get would be random, so not randomizing again
			K key = entry.getKey();
			if (key != null) {
				questions.add(key.toString() + questionSuffix);
			}
		}
		return questions;
	}
}
