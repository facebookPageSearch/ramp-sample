/**
 * 
 */
package ramp.sample.worksheetgen.io;

import java.io.File;
import java.io.IOException;

import ramp.sample.worksheetgen.model.BaseWorksheetEntity;

/**
 * @author Rama Palaniappan
 * @param <K>
 * @param <V>
 * @since 27-Sep-2015
 */
public interface Reader<T extends BaseWorksheetEntity<K, V>, K, V> {
	
	public T read(File file) throws IOException;

	public T read(String filePath) throws IOException;

	public T readFromResource(String resource) throws IOException;
}
