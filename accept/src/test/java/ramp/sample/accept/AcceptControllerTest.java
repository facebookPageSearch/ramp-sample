/**
 * 
 */
package ramp.sample.accept;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;

/**
 * @author rpalaniappan
 *
 */
public class AcceptControllerTest {
	
	@BeforeClass
	public void setup() {
		System.out.println(System.getProperties());
		String bURI = System.getProperty("baseURI", "http://localhost:8080");
		String bPath = System.getProperty("basePath", "/accept");
		
		RestAssured.baseURI = bURI;
		RestAssured.basePath = bPath;
	}
	
	@Test
	public void testHelloWorld() {
		String endpoint = "/hello";
		expect().statusCode(200)
				.log()
				.ifError()
				.body("msg", equalTo("Hello World"))
				.and()
				.body("uri", endsWith(endpoint))
				.and()
				.body("headers.tid", notNullValue())
				.when()
				.get(endpoint);
	}
}
