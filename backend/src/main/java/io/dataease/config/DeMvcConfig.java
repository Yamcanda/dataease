package io.dataease.config;

import static io.dataease.commons.constants.StaticResourceConstants.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
    
}
