package com.akm.springboot;

import com.akm.springboot.core.config.KitPropsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(KitPropsConfig.class)
public class SpringbootApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(SpringbootApplication.class, args);
        System.out.println("http://localhost:8989/swagger-ui.html");
    }

}
