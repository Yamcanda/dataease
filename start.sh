#!/bin/bash

# set jdk env
# JAVA_HOME=/opt/java/jdk-21.0.2
# PATH=$JAVA_HOME/bin:$PATH

cd `dirname $0`
HOME=`pwd`
# cd ..

DEPLOY_DIR=`pwd`

STDOUT_FILE=$DEPLOY_DIR/stdout.log
MAIN_JAR=CoreApplication.jar
MODEL="-Dapplication.name=$DEPLOY_DIR"

JAVA_OPTS="-Xms1g -Xmx2g"
#JAVA_OPTS="-Xms8g -Xmx8g -XX:+UseCompressedOops -XX:+AlwaysPreTouch -XX:AutoBoxCacheMax=20000"
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