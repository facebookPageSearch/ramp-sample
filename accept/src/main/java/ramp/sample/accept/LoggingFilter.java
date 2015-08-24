/**
 * 
 */
package ramp.sample.accept;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rama Palaniappan
 * @since 02-Aug-2015
 */
public class LoggingFilter implements Filter {

	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	private HttpRequestUtil httpRequestUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			doFilter((HttpServletRequest) request,
					(HttpServletResponse) response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long processingTime = -1; // Default Value assigned as -1
		try {
			long start = System.currentTimeMillis();
			doFilterAddMDC(request, response, chain);
			processingTime = System.currentTimeMillis() - start;
		} finally {
			if (log.isInfoEnabled()) {
				String url = httpRequestUtil.getUrl(request);
				log.info(url+"  processingTime=" + processingTime + "  status="+response.getStatus());
			}
			MDC.clear();
		}
	}

	private void doFilterAddMDC(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String transactionId = null;
		MutableHttpServletRequest mutableHttpServletRequest = null;
		if (log.isInfoEnabled() && request instanceof HttpServletRequest) {
			mutableHttpServletRequest = new MutableHttpServletRequest(
					(HttpServletRequest) request);
			transactionId = httpRequestUtil
					.getOrGenerateTransactionID(mutableHttpServletRequest);
			MDC.put("tid", "tid=" + transactionId);
			String url = httpRequestUtil.getUrl(mutableHttpServletRequest);
			log.info(url);
		}
		if (mutableHttpServletRequest != null) {
			chain.doFilter(mutableHttpServletRequest,
					(ServletResponse) response);
		} else {
			chain.doFilter(request, (ServletResponse) response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}
}
