/**
 * 
 */
package com.scb.rest.bookstore.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Juthakiat Tipchai
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		HashSet<String> produces = new HashSet<String>(Arrays.asList("application/json"));
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).produces(produces).pathMapping("/");
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Bookstore API")
				.description("A RESTful API for a bookstore that allows a user to login, perform user related tasks, view a list of books and place book orders.")
				.version("1.0")
				.contact(new Contact("Juthakiat Tipchai", "https://www.linkedin.com/in/juthakiat-tipchai-37165734/",
						"juthakiat@gmail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
}