/**
 * 
 */
package ramp.sample.worksheetgen.model;

import java.util.List;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public interface WorksheetEntity {
	public static final int PAGE_LIMIT = 9;
	public List<String> getQuestions();
	public String getHeader();
}
