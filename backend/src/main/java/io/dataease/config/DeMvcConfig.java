package io.dataease.config;

import static io.dataease.commons.constants.StaticResourceConstants.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.dataease.commons.utils.StaticResourceUtils.ensureBoth;
import static io.dataease.commons.utils.StaticResourceUtils.ensureSuffix;

/**
 * Author: wangjiahao
 * Date: 2022/4/24
 * Description:
 */
@Configuration
public class DeMvcConfig implements WebMvcConfigurer {
	
	@Value("${dataease.static.user_home:/opt/dataease/data/}")
	private String staticUserHome;
	
    /**
     * Configuring static resource path
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	String WORK_DIR = ensureSuffix(staticUserHome, FILE_SEPARATOR) + "static-resource" + FILE_SEPARATOR;
    	
        String workDir = FILE_PROTOCOL + ensureSuffix(WORK_DIR, FILE_SEPARATOR);
        String uploadUrlPattern = ensureBoth(UPLOAD_URL_PREFIX, URL_SEPARATOR) + "**";
        registry.addResourceHandler(uploadUrlPattern)
                .addResourceLocations(workDir);
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
