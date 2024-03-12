package io.dataease.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ApiProperties {

	public static String apiToken;
	
	@Value("${api.token}")
    public void setApiToken(String apiToken) {
		ApiProperties.apiToken = apiToken;
    }
	
}
