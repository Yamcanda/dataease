title dataease
@echo off

cd /d %~dp0

rem set jdk env
rem set JAVA_HOME=D:\Java\jdk-21.0.2

set JAVA_OPTS=-Xms1g -Xmx2g
set MAINWAR=CoreApplication.jar

if exist "%JAVA_HOME%\bin\java.exe" goto okHome

echo The JAVA_HOME environment variable is not defined correctly
echo JAVA_HOME 未设置或设置错误
@pause
goto end

:okhome
echo Using JAVA_HOME	%JAVA_HOME%
echo "%JAVA_HOME%\bin\java" -jar %JAVA_OPTS% %MAINWAR%
"%JAVA_HOME%\bin\java" -jar %JAVA_OPTS% %MAINWAR%

:end
@pause