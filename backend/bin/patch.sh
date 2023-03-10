#!/bin/bash

CURRENT=`pwd`

cd `dirname $0`
HOME=`pwd`

ARG1="$1"
TARGET="$CURRENT/upgrade"

MAIN_JAR=$HOME/bootstrap.jar

if [ -n "$ARG1" ]; then
  TARGET="$CURRENT/$ARG1"
fi

echo jar -uvf $MAIN_JAR -C $TARGET/ .
jar -uvf $MAIN_JAR -C $TARGET/ .
