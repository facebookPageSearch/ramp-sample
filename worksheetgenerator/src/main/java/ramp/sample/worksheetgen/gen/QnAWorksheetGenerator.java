/**
 * 
 */
package ramp.sample.worksheetgen.gen;

import java.io.IOException;

import ramp.sample.worksheetgen.io.QnAReader;
import ramp.sample.worksheetgen.model.QnA;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class QnAWorksheetGenerator extends WorksheetGenerator {
	public void generate(String filePath) throws IOException {
		QnAReader qnAReader = new QnAReader();
		QnA<String, String> qnas = qnAReader.read(filePath);
		printQuestions(qnas);
	}

	public static void main(String[] args) throws IOException {
		QnAWorksheetGenerator generator = new QnAWorksheetGenerator();
		generator.validateArguments(args);
		for (String file : args) {
			generator.generate(file);
			System.out.println(BREAK);
		}
	}
}
