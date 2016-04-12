/**
 * 
 */
package ramp.sample.accept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @author Rama Palaniappan
 * @since 29-Jul-2015
 */
@Component
public class HttpRequestUtil {
	
	private final Log log = LogFactory.getLog(getClass());
	
	public String getUrl(HttpServletRequest httpServletRequest) {
		if (httpServletRequest == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("method=").append(httpServletRequest.getMethod())
				.append(" url=").append(httpServletRequest.getRequestURI());
		String query = httpServletRequest.getQueryString();
		if (query != null) {
			sb.append('?').append(query);
		}
		return sb.toString();
	}

	/**
	 * Payload once read could be read again.
	 * Do not call this method if the payload is read some where else
	 * @param httpServletRequest
	 * @return
	 * @throws IOException
	 */
	public String payload(HttpServletRequest httpServletRequest)
			throws IOException {
		if (httpServletRequest == null) {
			return null;
		}
		BufferedReader bufferedReader = httpServletRequest.getReader();
		StringWriter sw = new StringWriter();
		char[] buffer = new char[1024 * 4];
		int n = 0;
		while (-1 != (n = bufferedReader.read(buffer))) {
			sw.write(buffer, 0, n);
		}
		return sw.toString();
	}

	public Map<String, String> getHeaders(HttpServletRequest httpServletRequest) {
		if (httpServletRequest == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = httpServletRequest.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	private static final String HEADER_NAME_TID = "tid";

	public synchronized String getOrGenerateTransactionID(
			MutableHttpServletRequest mutableHttpServletRequest) {
		if (mutableHttpServletRequest == null) {
			return null;
		}

		String transactionId = mutableHttpServletRequest
				.getHeader(HEADER_NAME_TID);
		if (transactionId == null) {
			transactionId = UUID.randomUUID().toString();
			mutableHttpServletRequest.putHeader(HEADER_NAME_TID, transactionId);
			log.debug("Header tid doesn't exists, so setting one");
		}
		return transactionId;
	}

}
