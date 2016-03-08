/**
 * 
 */
package ramp.sample.worksheetgen.gen;

import java.io.IOException;

import ramp.sample.worksheetgen.io.PairReader;
import ramp.sample.worksheetgen.model.Pairs;
import ramp.sample.worksheetgen.model.Pairs.PairType;

/**
 * @author Rama Palaniappan
 * @since 30-Sep-2015
 */
public class MatchWorksheetGenerator extends PairsWorksheetGenerator {
	@Override
	public void generate(String filePath) throws IOException {
		PairReader pairReader = new PairReader();
		Pairs<String, String> pairs = pairReader.read(filePath);
		pairs.setPairType(PairType.MATCH);
		printQuestions(pairs);
	}

	public static void main(String[] args) throws IOException {
		MatchWorksheetGenerator generator = new MatchWorksheetGenerator();
		generator.validateArguments(args);
		for (String file : args) {
			generator.generate(file);
			System.out.println(BREAK);
		}
	}
}
