package io.dataease.config;

import io.dataease.commons.utils.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${geo.custom.rootpath:file:/opt/dataease/data/custom/}")
    private String geoPath;

    @Value("${dataease.static.resource:/opt/dataease/data/static-resource/}")
	private String staticResource;

    // for dev
    @Value("${dataease.static.web.resource}")
    private String staticWebResource;

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LogUtil.info("activeProfiles: " + activeProfiles);
        LogUtil.info("geoPath: " + geoPath);
        LogUtil.info("staticResource: " + staticResource);
        LogUtil.info("staticWebResource: " + staticWebResource);

        // for dev knife4j doc
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/geo/**").addResourceLocations(geoPath);
        registry.addResourceHandler("/static-resource/**").addResourceLocations("file:///" + staticResource);

        if(!ObjectUtils.isEmpty(staticWebResource)) {
            registry.addResourceHandler("/**").addResourceLocations("file:///" + staticWebResource);
        }
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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // for dev
        if(!ObjectUtils.isEmpty(staticWebResource)) {
            registry.addViewController("/").setViewName("redirect:/index.html");
        }
    }

}
