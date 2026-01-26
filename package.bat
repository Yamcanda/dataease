@echo off
echo [INFO] Install sdk to maven local repo.

cd %~dp0

:: set JAVA_HOME=D:\Java\jdk-21.0.2
:: set NODE_HOME=D:\sdk\nodejs\nvm\v22.17.0

:: set PATH=%NODE_HOME%;%JAVA_HOME%\bin;%PATH%

call java -version
call node -v
call npm config get registry

:: call mvn clean package -DskipTests -Pstandalone

:: 仅编译后端, 不编译前端及 SDK
:: call mvn clean package -DskipTests -Pstandalone -pl !sdk,!core/core-frontend

:: 仅仅编译 前端、后端、打包、不编译 SDK
:: call mvn clean package -DskipTests -pl !sdk -Pstandalone

:: 仅打包，不编译前后端及 SDK
call mvn clean package -DskipTests -pl assembly -Pstandalone

:: 仅打包，不编译前后端及 SDK
:: call mvn package -DskipTests -pl !sdk,!core/core-backend,!core/core-frontend -Pstandalone

pause