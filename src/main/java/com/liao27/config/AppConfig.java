package com.liao27.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration
public class AppConfig extends WebMvcConfigurationSupport {

    /**
     * html 静态化存储路径
     */
    @Value("${html.location}")
    private String htmlLocation;

    /**
     * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用
     * http://blog.csdn.net/catoop/article/details/50501706
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**").addResourceLocations("file:" + this.htmlLocation);
        super.addResourceHandlers(registry);
    }

    @Bean
    ServletRegistrationBean H2ServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }

}