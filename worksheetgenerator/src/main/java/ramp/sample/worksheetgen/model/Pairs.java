/**
 * 
 */
package ramp.sample.worksheetgen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * @author Rama Palaniappan
 * @param <K>
 * @param <V>
 * @since 27-Sep-2015
 */
public class Pairs<K, V> extends BaseWorksheetEntity<K, V> implements
		WorksheetEntity {

	private static final String NEW_LINE = System.getProperty("line.separator");
	private static final String MATCH_SPACER = "\t\t\t\t\t\t";
	

	public enum PairType {
		MATCH, OPPOSITE, MEANING, PLURAL
	};

	private PairType pairType = null;

	/**
	 * @param header
	 *            the header to set
	 */
	@Override
	public void setHeader(String header) {
		super.setHeader(header);
		if (header == null) {
			return;
		}
		for (PairType pairType : PairType.values()) {
			String pairTypeName = pairType.name().toLowerCase(Locale.ENGLISH);
			header = header.toLowerCase(Locale.ENGLISH);
			if (header.contains(pairTypeName)) {
				setPairType(pairType);
			}
		}
	}

	/**
	 * @return the pairType
	 */
	public PairType getPairType() {
		return pairType;
	}

	/**
	 * @param pairType
	 *            the pairType to set
	 */
	public void setPairType(PairType pairType) {
		this.pairType = pairType;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Header: ").append(getHeader()).append(NEW_LINE);
		sb.append("Pair Type: ").append(pairType).append(NEW_LINE);
		sb.append("Pairs: ").append(getPairs()).append(NEW_LINE)
				.append(NEW_LINE);
		return sb.toString();
	}

	@Override
	public List<String> getQuestions() {
		if (pairType == null) {
			throw new IllegalAccessError("PairType not set");
		}
		if (pairType == PairType.MATCH) {
			return getQuestionsMatch();
		}
		String questionSuffix = null;
		switch (pairType) {
		case OPPOSITE:
			questionSuffix = " X ";
			break;
		case MEANING:
			questionSuffix = " = ";
			break;
		case PLURAL:
			questionSuffix = " - ";
			break;
		default:
			break;
		}
		return getQuestions(questionSuffix);
	}

	private Random random = new Random(System.currentTimeMillis());// Seed it
																	// with
																	// current
																	// timestamp

	private List<String> getQuestionsMatch() {
		int size = getPairs().size();
		String[] questions = new String[size];
		String[] answers = new String[size];
		int counter = 0;
		for (Map.Entry<K, V> entry : getPairs().entrySet()) {
			int startOffset = (counter / PAGE_LIMIT) * PAGE_LIMIT; //starts from zero
			// Map's get would be random, so not randomizing again
			K key = entry.getKey();
			V value = entry.getValue();
			if (key != null && value != null) {
				setRandom(questions, key.toString(), startOffset);
				setRandom(answers, value.toString(), startOffset);
			}
			counter++;
		}
		List<String> list = new ArrayList<String>(size);
		for (int index = 0; index < size; index++) {
			String question = questions[index];
			String answer = answers[index];
			list.add(question + MATCH_SPACER + answer);
		}
		questions = null;
		answers = null;
		return list;
	}

	private void setRandom(String[] array, String value, int startOffset) {
		//Offset start inclusive; end exclusive
		int rand = Integer.MAX_VALUE;
		do {
			rand = random.nextInt(PAGE_LIMIT) + startOffset;
		} while (rand >= array.length || array[rand] != null);
		array[rand] = value;
	}
}
