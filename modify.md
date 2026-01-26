# 二次开发

## 调整点

1. 调整 sdk StaticResourceConstants 类 getHomeData 方法，在启动 spring 容器前支持从外部 application.yml 加载配置信息
2. 调整 EhCacheStartListener 支持从外部 application.yml 加载配置信息
3. 增加 arthas 依赖，便于分析
4. 前端打包支持 cross-env 跨环境编译

mvn clean package -Pstandalone -U -DskipTests -pl sdk,core/core-backend -am
mvn clean package -Pstandalone -U -DskipTests -pl sdk,core/core-backend,core/core-frontend -am
