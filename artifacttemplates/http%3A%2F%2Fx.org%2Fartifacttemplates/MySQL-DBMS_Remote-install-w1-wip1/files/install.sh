#!/bin/sh
sudo sh -c "echo '127.0.0.1' $(hostname) >> /etc/hosts";
sudo apt-get update -qq;

# disables setting the root password with gui, root password etc. will be set in the configure.sh
export DEBIAN_FRONTEND=noninteractive;

sudo apt-get install -y mysql-server-8.0

sleep 5

sed -i "s/127.0.0.1/$SOURCE_VMIP/" /etc/mysql/mysql.conf.d/mysqld.cnf

# verifying the installation
sudo /etc/init.d/mysql start

sleep 5
