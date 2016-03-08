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
import ramp.sample.worksheetgen.model.QnA;
import ramp.sample.worksheetgen.model.Pairs.PairType;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class QnAReaderTest {
	private QnAReader qnAReader = new QnAReader();

	@DataProvider
	public Object[][] dataProvider() {
		return new Object[][] { 
				{ "Q&A-Test.txt", "Write a list of three things in your bedroom", "bed, blanket, mirror"},
			};
	}

	@Test(dataProvider = "dataProvider")
	public void test(String resourcePath, String key, String value) throws IOException {
		QnA<String, String> qnas = qnAReader.readFromResource(resourcePath);
		System.out.println(qnas);
		Assert.assertEquals(qnas.get(key), value);
	}
	
	@DataProvider
	public Object[][] dpPrintQuestions() {
		return new Object[][] { 
				{ "Q&A-Test.txt", "Write a list of three things in your bedroom"},
			};
	}
	
	@Test(dataProvider="dpPrintQuestions")
	public void printQuestions(String resource, String expected) throws IOException {
		QnA<String, String> qnas = qnAReader.readFromResource(resource);
		List<String> questions = qnas.getQuestions();
		Assert.assertTrue(questions.contains(expected));
	}
}
