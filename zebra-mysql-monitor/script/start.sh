#!/bin/bash

cd `dirname $0`
cd ..

CLASSPATH=`find lib -name *.jar|xargs|sed "s/ /:/g"`

java -server -cp ${CLASSPATH} com.dianping.zebra.mysql.StartServer $1 $2