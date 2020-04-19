package com.kira.emercmdplat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.terran4j.commons.api2doc.config.EnableApi2Doc;

@MapperScan("com.kira.emercmdplat.mapper")
@ComponentScan(basePackages= {"com.kira", "com.kira.emercmdplat"})
@EnableTransactionManagement//开启事务（默认是开启的）
@SpringBootApplication
@EnableApi2Doc
public class EmercmdplatApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmercmdplatApplication.class, args);
    }

}
