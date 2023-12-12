@echo off
echo [INFO] Package the project.

cd %~dp0

set JAVA_HOME=D:\Java\jdk-11.0.2
set NODE_HOME=D:\sdk\nodejs\nvm\v16.20.2

set PATH=%NODE_HOME%;%JAVA_HOME%\bin;%PATH%

:: echo jdk:
:: java -version

echo Node version:
node -v

echo NPM version:
call npm -v

:: echo NPM registry:
:: npm config get registry

call npm i
call npm run buildPlugin
:: call npm run build

pause