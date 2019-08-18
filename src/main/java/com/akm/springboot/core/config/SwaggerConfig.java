package com.akm.springboot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApiDoc() {
        // see https://springfox.github.io/springfox/docs/current/
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //将Date类型全部转为Long类型
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false);
//                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台REST服务API文档")
                .description("本文档由 [springfox-swagger2](http://springfox.github.io/springfox/) 自动生成。\n\n"
                        + "如何在此页面的请求时带上jwt token\n1. 使用AuthApi里的`/v1/login`登录获取access\\_token\n2. 使用页面右上角的`Authorize`按钮,"
                        + " 输入Bearer *access\\_token*进行登录\n3. 登录后,当前页面的\"Try it out!\"请求将自动带上JWT token")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}
