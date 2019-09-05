package com.akm.springboot.core.config;

import com.google.common.collect.Lists;
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
                .build().useDefaultResponseMessages(false)
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("akm-springboot后台API文档")
                .description("本文档由 [springfox-swagger2](http://springfox.github.io/springfox/) 自动生成。\n\n"
                        + "如何在此页面的请求时带上token\n1. 使用用户管理中的`/user/open/login`登录，登陆成功返回token\n2. 使用页面右上角的`Authorize`按钮,"
                        + " 输入token值\n3. 此时即可登陆用户有权访问的接口")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}
