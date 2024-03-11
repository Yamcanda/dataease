@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0

set JAVA_HOME=D:\Java\jdk-11.0.2
set NODE_HOME=D:\sdk\nodejs\nvm\v16.20.2

set PATH=%NODE_HOME%;%JAVA_HOME%\bin;%PATH%

call java -version
call node -v
call npm config get registry

:: call mvn clean package -Pstandalone
:: call mvn clean package -pl assembly -Pstandalone

:: install 后, 后续只在 core 下进行编译打包即可
call mvn install -pl !core/frontend,!core/mobile,!core/backend,!assembly

pause