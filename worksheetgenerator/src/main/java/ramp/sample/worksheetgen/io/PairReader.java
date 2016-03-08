/**
 * 
 */
package ramp.sample.worksheetgen.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ramp.sample.worksheetgen.model.Pairs;

import com.opencsv.CSVReader;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class PairReader implements
		Reader<Pairs<String, String>, String, String> {

	private ReaderHelper readerHelper = ReaderHelper.getReaderHelper();

	public Pairs<String, String> read(File file) throws IOException {
		CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(
				file)) ,',', '\"', 1);
		return (Pairs<String, String>) readerHelper.read(csvReader,
				file.getName(), new Pairs<String, String>());
	}

	public Pairs<String, String> read(String filePath) throws IOException {
		File file = new File(filePath);
		return read(file);
	}

	public Pairs<String, String> readFromResource(String resource)
			throws IOException {
		CSVReader csvReader = new CSVReader(new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream(
						"/" + resource))),',', '\"', 1);
		return (Pairs<String, String>) readerHelper.read(csvReader, resource,
				new Pairs<String, String>());
	}
}
