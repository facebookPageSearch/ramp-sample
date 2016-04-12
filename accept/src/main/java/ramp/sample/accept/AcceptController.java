/**
 * 
 */
package ramp.sample.accept;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rama Palaniappan
 * @since 29-Jul-2015
 */
@RestController
public class AcceptController {

	// private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private HttpRequestUtil httpRequestUtil;

	@RequestMapping("/**")
	public Http home(HttpServletRequest request, HttpServletResponse response) {

		Http http = new Http();
		http.setMsg("Hello World");
		http.setHeaders(httpRequestUtil.getHeaders(request));
		http.setHttpMethod(request.getMethod());
		http.setUri(httpRequestUtil.getUrl(request));
		try {
			http.setPayload(httpRequestUtil.payload(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return http;
	}
}
