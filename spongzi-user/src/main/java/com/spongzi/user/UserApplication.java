package com.spongzi.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户应用程序
 *
 * @author spong
 * @date 2023/11/06
 */
@SpringBootApplication
@MapperScan("com.spongzi.*.dao")
@ComponentScan("com.spongzi")
@EnableCaching
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
