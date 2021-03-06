package io.employee.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <h1>SwaggerConfig</h1>
 * <p>
 * This is the SwaggerConfig class, it consists of docket bean and api-info
 * which are used for rest api documentation
 * </p>
 *
 * @author Ravindra Pawar
 */
@Component
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	ApiInfo apiInfo;
	private String host;

	/**
	 * <p>
	 * This method returns the docket bean object by setting up the values of apis
	 * and path
	 * </p>
	 *
	 * @return the Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).host(host).select()
				.apis(RequestHandlerSelectors.basePackage("io.employee")).paths(PathSelectors.any()).build()
				.apiInfo(metaData());
	}

	/**
	 * <p>
	 * This method returns the ApiInfo
	 * </p>
	 *
	 * @return the ApiInfo
	 */

	private ApiInfo metaData() {
		apiInfo = new ApiInfo("Java 8 Assignment", "Java 8 Assignment By Ravindra", "1.0", "Terms of Services",
				new Contact("Ravindra Pawar", "ravibalajipawar@gmail.com", "ravibalajipawar@gmail.com"),
				"Apache License", "ravibalajipawar@gmail.com", Collections.emptyList());
		return apiInfo;
	}

}
