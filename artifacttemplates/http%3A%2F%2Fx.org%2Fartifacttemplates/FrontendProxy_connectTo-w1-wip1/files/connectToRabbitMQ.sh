#!/bin/bash
echo "Connect Proxy to address $VMIP:$Port"
echo "VMIP: $VMIP and SourceVMIP: $SOURCE_VMIP and TargetVMIP: $TARGET_VMIP"
echo "Port: $Port and SourcePort: $SOURCE_Port and TargetVMIP: $TARGET_Port"
echo "Port: $AppName and SourcePort: $SOURCE_AppName and TargetVMIP: $TARGET_AppName"

echo "server:
  port: $SOURCE_Port

spring:
  rabbitmq:
    host: $TARGET_VMIP
    username: $User
    password: $Password
    port: $TARGET_Port
" > /var/www/java/${SOURCE_AppName}/application.yml

echo "Updated Proxy Configuration for ${SOURCE_AppName}."

kill -9 $(cat ${AppName}.pid)

nohup java -jar ${AppName}.jar > /var/log/${AppName}.log 2>&1 &
echo $! > ${AppName}.pid

echo "Started Application again with process ID $(cat ${AppName}.pid)"