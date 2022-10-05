#!/bin/bash

echo "Start ProxySQL with config file"
sudo proxysql -c /etc/proxysql.cnf --initial