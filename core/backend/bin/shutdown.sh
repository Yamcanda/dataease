#!/bin/bash

cd `dirname $0`
cd ..
BASE=`pwd`

PIDFILE=run.pid

if [ ! -f "$PIDFILE" ]; then
    echo stoped
    exit 0
fi

cat run.pid | xargs kill -9
rm -rf run.pid