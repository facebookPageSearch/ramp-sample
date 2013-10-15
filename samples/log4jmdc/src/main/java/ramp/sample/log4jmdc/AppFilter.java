/**
 * 
 */
package ramp.sample.log4jmdc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

/**
 * @author Rama Palaniappan
 * @since Oct 11, 2013
 */
public class AppFilter implements Filter {

	private static final Log LOG = LogFactory.getLog(AppFilter.class);

	/**
	 * 
	 */
	public AppFilter() {
	}

	public void destroy() {
	}

	private enum MdcKey {
		TID("tid"), TRAFFIC_TYPE("trafficType");

		String key = null;

		MdcKey(String key) {
			this.key = key;
		}

		public String key() {
			return key;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			addMdc("12345678901234567", "LIVE");
			long start = System.currentTimeMillis();
			chain.doFilter(request, response);
			LOG.info("processingTime=" + (System.currentTimeMillis() - start));
		} finally {
			clearMdc();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	//To log these values, add these in your appender's ConversionPattern
	// %X{tid} %X{trafficType}
	//Example logs
	//2013-10-15 15:03:59,217 +0530 level=ERROR class=ramp.sample.log4jmdc.HelloWorldServlet tid=12345678901234567 trafficType=LIVE              Error Message
	//2013-10-15 15:03:59,218 +0530 level=INFO  class=ramp.sample.log4jmdc.AppFilter tid=12345678901234567 trafficType=LIVE              processingTime=1004
	private void addMdc(String tidValue, String trafficTypeValue) {
		MDC.put(MdcKey.TID.key(), MdcKey.TID.key() + "=" + tidValue);
		MDC.put(MdcKey.TRAFFIC_TYPE.key(), MdcKey.TRAFFIC_TYPE.key() + "="
				+ trafficTypeValue);
	}

	private void clearMdc() {
		// MDC.clear();
		// Older log4j jar don't have clear method, so remove one my one
		for (MdcKey mdcKey : MdcKey.values()) {
			MDC.remove(mdcKey.key());
		}
	}

}
