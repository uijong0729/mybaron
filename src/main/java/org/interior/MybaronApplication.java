package org.interior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan
@Configuration
@EnableAutoConfiguration
public class MybaronApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybaronApplication.class, args);
	}
	
	@Bean
    public InternalResourceViewResolver setupViewResolver() {
 
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".html");
        resolver.setPrefix("/templates/");
        return resolver;
    }
}
