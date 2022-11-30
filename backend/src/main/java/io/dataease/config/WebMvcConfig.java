package io.dataease.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${geo.custom.rootpath:file:/opt/dataease/data/custom/}")
    private String geoPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/geo/**").addResourceLocations(geoPath);
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                  .allowedOriginPatterns("*") // 允许的源 Access-Control-Allow-Origin
                  .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") // Access-Control-Allow-Methods
                  .allowCredentials(true) // 是否允许发送Cookie Access-Control-Allow-Credentials
                  .allowedMethods("*") // 支持跨域请求的方法 Access-Control-Allow-Methods
                  .exposedHeaders("*") // Access-Control-Expose-Headers
                  .maxAge(3600); // 预检请求的有效时间 Access-Control-Max-Age
    }
    
}
