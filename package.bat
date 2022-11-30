@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0

call mvn clean package

pause