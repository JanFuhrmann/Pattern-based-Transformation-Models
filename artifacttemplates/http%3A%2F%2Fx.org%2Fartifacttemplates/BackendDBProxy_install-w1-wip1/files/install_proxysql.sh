#!/bin/bash

echo "Install ProxySQL v1"
sudo apt-get update -qq
sudo apt-get install wget -y
sudo wget https://github.com/sysown/proxysql/releases/download/v2.4.2/proxysql_2.4.2-ubuntu20_amd64.deb

# Skip new config and use already installed
printf '\n' | sudo dpkg -i proxysql_2.4.2-ubuntu20_amd64.deb

# Install MySQL Client
sudo apt-get install mysql-client -y