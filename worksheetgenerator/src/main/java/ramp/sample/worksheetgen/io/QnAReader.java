/**
 * 
 */
package ramp.sample.worksheetgen.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.opencsv.CSVReader;

import ramp.sample.worksheetgen.model.QnA;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class QnAReader implements Reader<QnA<String, String>, String, String> {

	private ReaderHelper readerHelper = ReaderHelper.getReaderHelper();

	public QnA<String, String> read(File file) throws IOException {
		CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(
				file)), ',', '\"', 1);
		return (QnA<String, String>) readerHelper.read(csvReader,
				file.getName(), new QnA<String, String>());
	}

	public QnA<String, String> read(String filePath) throws IOException {
		File file = new File(filePath);
		return read(file);
	}

	public QnA<String, String> readFromResource(String resource)
			throws IOException {
		CSVReader csvReader = new CSVReader(new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream(
						"/" + resource))), ',', '\"', 1);
		return (QnA<String, String>) readerHelper.read(csvReader, resource,
				new QnA<String, String>());
	}

}
