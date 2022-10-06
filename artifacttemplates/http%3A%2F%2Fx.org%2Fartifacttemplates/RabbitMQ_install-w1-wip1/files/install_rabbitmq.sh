#!/bin/bash

apt-get update -qq
apt-get install -y rabbitmq-server
service rabbitmq-server start
echo "Successfully installed RabbitMQ!"
