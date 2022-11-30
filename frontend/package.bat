@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0

call npm i
call npm run build:stage

pause