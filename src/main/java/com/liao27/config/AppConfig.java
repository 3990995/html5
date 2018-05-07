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


    public AppConfig() {
        super();
    }

    /**
     * html 静态化存储路径
     */
    @Value("${upload.location}")
    private String uploadLocation;

    /**
     * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用
     * http://blog.csdn.net/catoop/article/details/50501706
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/html/**").addResourceLocations("file:" + this.uploadLocation);
        registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/static/vendor/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }


    @Bean
    ServletRegistrationBean H2ServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }


}