@echo off
echo [INFO] Install sdk to maven local repo.

cd %~dp0

rem set JAVA_HOME=D:\Java\jdk-21.0.2
rem set NODE_HOME=D:\sdk\nodejs\nvm\v22.17.0

rem set PATH=%NODE_HOME%;%JAVA_HOME%\bin;%PATH%

call java -version
call node -v
call npm config get registry

call mvn clean install -DskipTests -pl !assembly,!core/core-backend,!core/core-frontend

:: deploy to nexus
call mvn clean deploy -DskipTests -f sdk/pom.xml

pause