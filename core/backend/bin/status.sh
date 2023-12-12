#!/bin/bash

cd `dirname $0`
cd ..
BASE=`pwd`

PIDFILE=run.pid

if [ ! -f "$PIDFILE" ]; then
    echo stoped
    exit 0
fi

PIDS=`cat run.pid`
PROCESS=`ps --no-headers $PIDS`

if [ ! -z "$PROCESS" ]; then
    echo started
else
    echo stoped
fi