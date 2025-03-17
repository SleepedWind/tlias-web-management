package com.itheima.config;

import com.itheima.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer { //实现WebMvcConfigurer接口
    @Autowired
    private TokenInterceptor tokenInterceptor;

    //重写addInterceptors方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //拦截所有
                .excludePathPatterns("/login"); //放行
    }
}
