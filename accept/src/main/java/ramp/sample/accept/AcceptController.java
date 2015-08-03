/**
 * 
 */
package ramp.sample.accept;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rama Palaniappan
 * @since 29-Jul-2015
 */
@RestController
public class AcceptController {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	HttpRequestUtil httpRequestUtil;
	
	@RequestMapping("/**")
	public String home(HttpServletRequest request, 
	        HttpServletResponse response) {
		log.info("----------------------------------------------------");
		log.info("HEADERS: " + httpRequestUtil.getHeaders(request));
		try {
			log.info("PAYLOAD: " + httpRequestUtil.payload(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("----------------------------------------------------");
		
		String msg = "Hello World!";
		return msg;
	}
}
