package io.dataease.listener;


import io.dataease.utils.ConfigUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

public class EhCacheStartListener implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String property = applicationContext.getEnvironment().getProperty("dataease.login_timeout", String.class, "480");
        System.setProperty("dataease.login_timeout", property);

        // 优先从Spring Environment读取（启动目录config/application.yml）
        String ehcachePath = null;
        String jarDir = System.getProperty("user.dir");
        File configFile = new File(jarDir, "config" + File.separator + "application.yml");
        if (configFile.exists()) {
            ehcachePath = applicationContext.getEnvironment().getProperty("dataease.path.ehcache");
        }

        // 如果配置文件不存在或配置项不存在，从ConfigUtils获取（用户主目录的config/application.yml）
        if (ehcachePath == null || ehcachePath.isEmpty()) {
            ehcachePath = ConfigUtils.getConfig("dataease.path.ehcache", "/opt/dataease2.0/cache");
        }

        System.setProperty("dataease.path.ehcache", ehcachePath);
    }
}
