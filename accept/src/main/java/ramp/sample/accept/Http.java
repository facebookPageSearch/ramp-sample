/**
 * 
 */
package ramp.sample.accept;

import java.io.Serializable;
import java.util.Map;

/**
 * @author rpalaniappan
 *
 */
public class Http implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg = null;
	private String httpMethod = null;
	private String uri = null;
	private Map<String, String> headers = null;
	private String payload = null;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
