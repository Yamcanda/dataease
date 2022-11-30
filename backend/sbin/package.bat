@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0
cd ..

call mvn clean package -Pstandalone

pause