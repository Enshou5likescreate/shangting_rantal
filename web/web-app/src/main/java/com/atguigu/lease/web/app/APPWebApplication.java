package com.atguigu.lease.web.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan("com.atguigu.lease.web.app.mapper")
@SpringBootApplication
public class APPWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(APPWebApplication.class, args);
    }
}
