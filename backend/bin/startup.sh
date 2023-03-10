#!/bin/bash

# set java env
#JAVA_HOME=/opt/java/jdk-11.0_2
#PATH=$JAVA_HOME/bin:$PATH

cd `dirname $0`
HOME=`pwd`
cd ..

DEPLOY_DIR=`pwd`

STDOUT_FILE=$DEPLOY_DIR/stdout.log
MAIN_JAR=$HOME/bootstrap.jar
MODEL=-Dspring.profiles.active=prod
MODEL="$MODEL -Dloader.path=lib,config"
MODEL="$MODEL -Dapplication.name=$DEPLOY_DIR"

JAVA_OPTS="-Xms1024m -Xmx2048m"
#JAVA_OPTS="-Xms8000m -Xmx8000m -XX:+UseCompressedOops -XX:+AlwaysPreTouch -XX:AutoBoxCacheMax=20000"
JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError"

echo java -jar $JAVA_OPTS $MODEL $MAIN_JAR \> $STDOUT_FILE 2\>\&1 \&
java -jar $JAVA_OPTS $MODEL $MAIN_JAR > $STDOUT_FILE 2>&1 &

while [ -z "$PROCESS" ]; do
    sleep 2
    PROCESS=`ps axfww | grep "$MAIN_JAR" | grep "$DEPLOY_DIR" | grep -v grep`
done

echo OK!
PIDS=`ps --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo $PIDS > run.pid
echo "PID: $PIDS"