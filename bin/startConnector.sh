#!/usr/bin/env bash

export CLASSPATH=%CLASSPATH:$PWD/target/s3-kafka-connector-1.0-SNAPSHOT-jar-with-dependencies.jar
java -cp $CLASSPATH com.dilteam.kafka.connect.Driver $PWD/src/main/resources/s3-connect-standalone.properties $PWD/src/main/resources/connect-s3-sink.properties

