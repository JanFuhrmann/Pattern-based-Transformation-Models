#!/bin/bash

echo "RabbitMQ: Create User $User with password $Password and grant privileges to allow remote connections"
rabbitmqctl add_user $User $Password
rabbitmqctl set_user_tags $User administrator
rabbitmqctl set_permissions -p / $User ".*" ".*" ".*"

#rabbitmq-plugins enable rabbitmq_web_stomp
#sudo service rabbitmq-server restart