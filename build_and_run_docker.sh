#!/bin/bash
mvn clean package spring-boot:repackage
docker build -t crms-poc .
docker run -it --rm -p 8090:8090 crms-poc
