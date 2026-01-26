package io.dataease.constant;

import io.dataease.utils.ConfigUtils;
import io.dataease.utils.ModelUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Objects;

import static io.dataease.utils.StaticResourceUtils.ensureSuffix;


public class StaticResourceConstants {

    public static final String FILE_PROTOCOL = "file:";

    public static final String FILE_SEPARATOR = File.separator;

    public static final String USER_HOME = getHomeData();

    public static String WORK_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "static-resource" + FILE_SEPARATOR;

    public static String MAP_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "map";
    public static String CUSTOM_MAP_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "geo";
    public static String APPEARANCE_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "appearance";
    public static String REPORT_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "report";
    public static String PLUGIN_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "plugin";
    public static String I18N_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + "i18n/front";

    public static String MAP_URL = "/map";
    public static String GEO_URL = "/geo";
    public static String I18N_URL = "/i18n";

    /**
     * Upload prefix.
     */
    public final static String UPLOAD_URL_PREFIX = "static-resource";

    /**
     * url separator.
     */
    public static final String URL_SEPARATOR = "/";

    public static String getHomeData() {
        if (ModelUtils.isDesktop()) {
            return ConfigUtils.getConfig("dataease.path.data", "/opt/dataease2.0/data");
        } else {
            String defaultValue = "/opt/dataease2.0/data";
            String jarDir = getJarDirectory();
            if (jarDir != null) {
                String configPath = jarDir + File.separator + "config" + File.separator + "application.yml";
                File configFile = new File(configPath);
                if (configFile.exists()) {
                    String configValue = getConfigFromFile(configPath, "dataease.path.data");
                    if (configValue != null && !configValue.isEmpty()) {
                        return configValue;
                    }
                }
            }
            return defaultValue;
        }
    }

    /**
     * 从 YAML 配置文件中读取指定 key 的值
     */
    private static String getConfigFromFile(String filePath, String key) {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(new FileSystemResource(filePath));
            return Objects.requireNonNull(factory.getObject()).getProperty(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取启动 JAR 所在目录
     */
    private static String getJarDirectory() {
        try {
            // Spring Boot 提供的 ApplicationHome 可以获取最外层 JAR 所在目录
            ApplicationHome home = new ApplicationHome();
            File source = home.getSource();
            if (source != null && source.exists()) {
                return source.getParentFile().getAbsolutePath();
            }
            // 备选方案：获取应用目录
            File dir = home.getDir();
            if (dir != null && dir.exists()) {
                return dir.getAbsolutePath();
            }
        } catch (Exception e) {
            // 忽略异常，使用备选方案
        }
        return null;
    }
}
