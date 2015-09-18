/**
 * 
 */
package ramp.sample.proxy;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

/**
 * @author Rama Palaniappan
 * @since 18-Sep-2015
 */
public class ProxyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Log log = LogFactory.getLog(getClass());

	@Override
	/**
	 * Enable SSL as mentioned here
	 * https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html#Configuration
	 * 
	 * Use https endpoint https://localhost:8443/proxy/* for proxying https
	 * Use http endpoint http://localhost:8080/proxy/* for proxying http
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		//Accept/Trust all SSL certificates
		HttpClientBuilder builder = HttpClients.custom();
		builder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

		String url = getUrlAndSetProxy(request, builder);
		HttpGet httpget = new HttpGet(url);
		setHeaders(request, httpget);
		
		CloseableHttpClient closeableHttpClient = builder.build();
		
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		CloseableHttpResponse closeableHttpResponse = null;
		int status = -1;
		try {
			try {
				closeableHttpResponse = closeableHttpClient.execute(httpget);
			} catch (Exception e) {
				servletOutputStream.print(ExceptionUtils.getMessage(e));
			}
			status = closeableHttpResponse.getStatusLine().getStatusCode();
			httpServletResponse.setStatus(status);
			HttpEntity entity = closeableHttpResponse.getEntity();
			String payload = entity != null ? EntityUtils.toString(entity)
					: null;
			servletOutputStream.print(payload);
		} finally {
			if (closeableHttpResponse != null) {
				closeableHttpResponse.close();
			}
			if (servletOutputStream != null) {
				servletOutputStream.close();
			}
			closeableHttpClient.close();
			log.info("URL=" + url + ", http-status=" + status);
		}
	}

	private void setHeaders(HttpServletRequest incomingRequest,
			HttpRequestBase httpMethod) {
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = incomingRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			if ("host".equalsIgnoreCase(key)) {
				continue;//Don't set host header
			}
			String value = incomingRequest.getHeader(key);
			log.info(key+"="+value);
			httpMethod.setHeader(key, value);
		}
	}
	
	//As there are String operations, both getUrl and setProxy is merged
	private String getUrlAndSetProxy(HttpServletRequest request, HttpClientBuilder builder) {
		String rawUrl = request.getRequestURI();
		boolean enableProxy = false;
		if (rawUrl.contains("intuit.com") || rawUrl.contains("intuit.net")) {
			enableProxy = false;
//		} else {
//			enableProxy = true;
		}
		HttpHost proxy = null;
		StringBuilder sb = new StringBuilder();
		int index = rawUrl.indexOf("https");
		if (index >= 0) {
			sb.append("https://");
			index = index + 6;
			if (enableProxy) {
				proxy = new HttpHost("qypprdproxy01.ie.intuit.net", 80, "https");
			}
		} else {
			index = rawUrl.indexOf("http");
			if (index >= 0) {
				sb.append("http://");
				index = index + 5;
				if (enableProxy) {
					proxy = new HttpHost("qypprdproxy01.ie.intuit.net", 80,
							"http");
				}
			} else {
				throw new IllegalArgumentException(
						"Could not proxy this request: " + rawUrl);
			}
		}
		sb.append(rawUrl.substring(index));
		String query = request.getQueryString();
		if (query != null) {
			sb.append('?').append(query);
		}
		if (proxy != null) {
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
					proxy);
			builder.setRoutePlanner(routePlanner);
		}
		return sb.toString();
	}

}