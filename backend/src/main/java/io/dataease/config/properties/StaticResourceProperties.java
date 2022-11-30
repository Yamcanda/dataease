package io.dataease.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Author: wangjiahao
 * Date: 2022/4/24
 * Description:
 */
@Data
@Configuration
public class StaticResourceProperties {

    /**
     * Upload prefix.
     */
    private String uploadUrlPrefix = "static-resource";

    @Value("${dataease.static.user_home:/opt/dataease/data/}")
	private String staticUserHome;
    
    @Value("${dataease.static.resource:/opt/dataease/data/static-resource/}")
	private String staticResource;
    
}
