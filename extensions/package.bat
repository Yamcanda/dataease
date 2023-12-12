@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0

set JAVA_HOME=D:\Java\jdk-11.0.2
set NODE_HOME=D:\sdk\nodejs\nvm\v16.20.2

set PATH=%NODE_HOME%;%JAVA_HOME%\bin;%PATH%

call java -version
call node -v
call npm config get registry

call mvn clean package

pause