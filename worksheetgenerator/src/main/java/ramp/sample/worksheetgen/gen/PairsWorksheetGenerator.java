/**
 * 
 */
package ramp.sample.worksheetgen.gen;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ramp.sample.worksheetgen.io.PairReader;
import ramp.sample.worksheetgen.model.Pairs;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class PairsWorksheetGenerator extends WorksheetGenerator {
	public void generate(String filePath) throws IOException {
		PairReader pairReader = new PairReader();
		Pairs<String, String> pairs = pairReader.read(filePath);
		printQuestions(pairs);
	}

	public static void main(String[] args) throws IOException {
		PairsWorksheetGenerator generator = new PairsWorksheetGenerator();
		generator.validateArguments(args);
		for (String file : args) {
			generator.generate(file);
			System.out.println(BREAK);
		}
	}
}
