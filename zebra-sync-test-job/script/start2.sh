#!/bin/bash

cd `dirname $0`
cd ..

CLASSPATH=`find lib -name *.jar|xargs|sed "s/ /:/g"`

java -server -cp ${CLASSPATH} -Xms2560m -Xmx2560m -Xss256k -XX:PermSize=128m -XX:MaxPermSize=384m -XX:NewSize=1024m -XX:MaxNewSize=1024m -XX:SurvivorRatio=22 -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:-ReduceInitialCardMarks -XX:+CMSPermGenSweepingEnabled -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent com.dianping.avatardao.test.ConnectionPoolPerformanceTest $1
