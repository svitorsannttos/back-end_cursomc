package com.vitor.cursomc.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vitor.cursomc.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    @SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Exemplo de Swagger",
                "Endpoints referente ao cursomc",
                "1.0",
                "Terms of Service",
                new Contact("Michelli Brito", "https://www.youtube.com/michellibrito",
                        "michellidibrito@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }

}
