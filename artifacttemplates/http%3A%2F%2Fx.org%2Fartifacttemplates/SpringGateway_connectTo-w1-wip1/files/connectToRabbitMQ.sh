#!/bin/bash
echo "Create $AppName on Port $SOURCE_Port"
echo "Connect Proxy to RabbitMQ on $VMIP:$TARGET_Port with User $User and Password $Password"

cd /var/www/java/${AppName}

echo "server:
  port: $SOURCE_Port

spring:
  rabbitmq:
    host: $VMIP
    username: $User
    password: $Password
    port: $TARGET_Port
    url: http://localhost:9990
" > application.yml

echo "Updated Proxy Configuration for ${AppName}."

kill -9 $(cat ${AppName}.pid)

nohup java -jar ${AppName}.jar > /var/log/${AppName}.log 2>&1 &
echo $! > ${AppName}.pid

echo "Started Application again with process ID $(cat ${AppName}.pid)"