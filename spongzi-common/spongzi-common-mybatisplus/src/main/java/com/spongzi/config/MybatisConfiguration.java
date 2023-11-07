package com.spongzi.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.spongzi.inteceptor.SqlBeautyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfiguration {

    @Bean
    public SqlBeautyInterceptor sqlBeautyInterceptor() {
        return new SqlBeautyInterceptor();
    }
}
