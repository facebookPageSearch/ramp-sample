/**
 * 
 */
package ramp.sample.worksheetgen.io;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ramp.sample.worksheetgen.model.Pairs;
import ramp.sample.worksheetgen.model.Pairs.PairType;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class PairReaderTest {
	private PairReader pairReader = new PairReader();

	@DataProvider
	public Object[][] dataProvider() {
		return new Object[][] { 
				{ "#Pairs - Plurals - One to Many -s", "boy", "boys", PairType.PLURAL },
				{ "#Pairs - Opposites", "hot", "cold", PairType.OPPOSITE }, 
			};
	}

	@Test(dataProvider = "dataProvider")
	public void test(String resourcePath, String key, String value, PairType pairType) throws IOException {
		Pairs<String, String> pairs = pairReader.readFromResource(resourcePath);
		System.out.println(pairs);
		Assert.assertEquals(pairs.get(key), value);
		Assert.assertEquals(pairs.getPairType(), pairType);
	}
	
	@DataProvider
	public Object[][] dpPrintQuestions() {
		return new Object[][] { 
				{ "#Pairs - Plurals - One to Many -s", "shirt - "},
				{ "#Pairs - Opposites", "big X " }, 
			};
	}
	
	@Test(dataProvider="dpPrintQuestions")
	public void printQuestions(String resource, String expected) throws IOException {
		Pairs<String, String> pairs = pairReader.readFromResource(resource);
		List<String> questions = pairs.getQuestions();
		Assert.assertTrue(questions.contains(expected));
	}
}
