@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0

set JAVA_HOME=D:\Java\jdk-21.0.2
set NODE_HOME=D:\sdk\nodejs\nvm\v22.17.0

set PATH=%NODE_HOME%;%JAVA_HOME%\bin;%PATH%

call java -version
call node -v
call npm config get registry

:: call mvn clean package -Pstandalone
:: call mvn clean package -pl assembly -Pstandalone

:: install 后, 后续只在 core 下进行编译打包即可
:: call mvn install -pl !core/frontend,!core/backend,!assembly

:: call mvn clean package -Pstandalone -U -Dmaven.test.skip=true

call mvn clean package -Pstandalone -U -Dmaven.test.skip=true -pl !core-frontend -am

pause