title dataease
@echo off

cd /d %~dp0
cd ..

rem set JAVA_HOME=D:\Java\jdk-11.0.2

set JAVA_OPTS=-Xms1024m -Xmx1024m
rem set MAINWAR=backend-1.17.0.jar
set MAINWAR=.\bin\bootstrap.jar
rem set MODEL=-Dloader.path=drivers
set MODEL=-Dspring.profiles.active=prod -Dloader.path=lib,config

if exist "%JAVA_HOME%\bin\java.exe" goto okHome

echo The JAVA_HOME environment variable is not defined correctly
echo JAVA_HOME 未设置或设置错误
@pause
goto end

:okhome
echo Using JAVA_HOME	%JAVA_HOME%
echo "%JAVA_HOME%\bin\java" -jar %JAVA_OPTS% %MODEL% %MAINWAR%
"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -jar %JAVA_OPTS% %MODEL% %MAINWAR%

:end
@pause
