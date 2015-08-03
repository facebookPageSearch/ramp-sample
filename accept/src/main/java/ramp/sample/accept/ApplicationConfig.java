package ramp.sample.accept;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public Filter loggingFilter() {
		return new LoggingFilter();
	}
	
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean () {
//	    
////	    CompressingFilter compressingFilter = new CompressingFilter();
////	    
////	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
////	    
////	    registrationBean.setFilter(compressingFilter);
////	    
//	    return registrationBean;
//	}
}