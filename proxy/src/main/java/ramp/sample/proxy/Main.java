package ramp.sample.proxy;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Rama Palaniappan
 * @since 18-Sep-2015
 */
public class Main {
	public static void main(String[] args) throws ServletException, IOException {
		CloseableHttpClient closeableHttpClient = HttpClients.custom()
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

		String url = "https/twitter.com/gmail?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor";
//		String url = "https/www.google.co.in/webhp?sourceid=chrome-instant&rlz=1C5CHFA_enIN572US573&ion=1&espv=2&ie=UTF-8#q=apache+traffic+server+allow+all+domain+cors";
//		String url = "localhost:8080/proxy/https/api.appbot.co/api/v1/apps/311455/topics?key=b397b5d0d905773b42e180f9eed65f45f332a6d1&start=2015-08-01&end=2015-09-14";
		HttpGet httpget = new HttpGet(getUrl(url));

		CloseableHttpResponse closeableHttpResponse = null;
		try {
			closeableHttpResponse = closeableHttpClient.execute(httpget);
			System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
			HttpEntity entity = closeableHttpResponse.getEntity();
			String payload = entity != null ? EntityUtils.toString(entity)
					: null;
			System.out.println(payload);
		} finally {
			if (closeableHttpResponse != null) {
				closeableHttpResponse.close();
			}
			closeableHttpClient.close();

		}
	}

	private static String getUrl(String rawUrl) {
		StringBuilder sb = new StringBuilder();
		int index = rawUrl.indexOf("https");
		if (index >= 0) {
			sb.append("https://");
			index = index + 6;
		} else {
			index = rawUrl.indexOf("http");
			if (index >= 0) {
				sb.append("http://");
				index = index + 5;
			} else {
				throw new IllegalArgumentException(
						"Could not proxy this request: " + rawUrl);
			}
		}
		sb.append(rawUrl.substring(index));
		return sb.toString();
	}
}
