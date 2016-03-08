/**
 * 
 */
package ramp.sample.worksheetgen.io;

import java.io.IOException;

import ramp.sample.worksheetgen.model.BaseWorksheetEntity;

import com.opencsv.CSVReader;

/**
 * @author Rama Palaniappan
 * @since 27-Sep-2015
 */
public class ReaderHelper {
	private ReaderHelper() {
		
	}
	private static ReaderHelper readerHelper = new ReaderHelper();
	public static ReaderHelper getReaderHelper() {
		return readerHelper;
	}
	public BaseWorksheetEntity<String, String> read(CSVReader csvReader,
			String header, BaseWorksheetEntity<String, String> entityToPopulate) throws IOException {
		entityToPopulate.setHeader(header);
		String[] nextLine = null;
		while ((nextLine = csvReader.readNext()) != null) {
			if (nextLine.length != 2) {
				continue;// only interested in pairs
			}
			String key = nextLine[0];
			String value = nextLine[1];
			if (key != null && value != null) {
				entityToPopulate.put(key.trim(), value.trim());
			}
		}
		if (entityToPopulate.getPairs() == null || entityToPopulate.getPairs().size() == 0) {
			throw new IllegalArgumentException(
					"Invalid input file provided.  "
							+ "Valid input files are OPPOSITES, MATCH, MEANING, PLURALS, Questions&Answers(Q&A)");
		}
		return entityToPopulate;
	}

}
