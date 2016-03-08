/**
 * 
 */
package ramp.sample.worksheetgen.gen;

import java.util.List;
import java.util.Locale;

import ramp.sample.worksheetgen.model.WorksheetEntity;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class WorksheetGenerator {

	protected static final String BREAK = "--------------------------------------------------------------------";
	
	public void validateArguments(String[] args) {
		if (args == null || args.length < 1) {
			System.err.println("Usage: ");
			System.err.println("java  " + this.getClass().getCanonicalName() + " <file-location1> <file-location2> ...");
			System.err.println("Hint: Use double quotes if file path contains spaces or other special characters");
			System.exit(1);
		}
	}
	
	public void printQuestions (WorksheetEntity entity) {
		List<String> questions = entity.getQuestions();
		if (questions == null) {
			throw new IllegalArgumentException("No questions to print");
		}
		int i=0;
		String header = entity.getHeader();
		for (String question: questions) {
			if (i%entity.PAGE_LIMIT == 0) {
				printHeader(header);
			}
			System.out.print(++i + ".  ");
			System.out.println(question);
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
	
	private void printHeader(String header) {
		if (header != null) {
			header = header.toUpperCase(Locale.ENGLISH);
			System.out.println(header);
			System.out.println(BREAK);
			System.out.println();
		}
	}
}
