package com.zs.assignment11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class);
    }

}
