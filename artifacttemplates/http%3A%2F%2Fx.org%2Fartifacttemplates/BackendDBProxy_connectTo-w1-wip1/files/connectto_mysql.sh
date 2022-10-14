#!/bin/bash

echo "Connect ProxySQL to Database"
echo "ProxySQL: Add Server $TARGET_VMIP:$TARGET_DBMSPort with user $DBUser and Password $DBPassword to known servers for database $DBName"

echo "#file proxysql.cnf
datadir=\"/var/lib/proxysql\"

admin_variables=
{
    admin_credentials=\"admin:admin\"
    mysql_ifaces=\"0.0.0.0:6032\"
    admin-hash_passwords=false
}

mysql_variables=
{
    threads=4
    max_connections=2048
    default_query_delay=0
    default_query_timeout=36000000
    have_compress=true
    poll_timeout=2000
    interfaces=\"0.0.0.0:6033;/tmp/proxysql.sock\" 
    default_schema=\"${DBName:-realWorld}\"
    stacksize=1048576
    server_version=\"8.0\"
    connect_timeout_server=3000
    monitor_username=\"user\"
    monitor_password=\"pass\"
    monitor_history=600000
    monitor_connect_interval=2000
    monitor_ping_interval=2000
    monitor_read_only_interval=2000
    monitor_read_only_timeout=500
    ping_interval_server_msec=120000
    ping_timeout_server=500
    commands_stats=true
    sessions_sort=true
    connect_retries_on_failure=10
    # Config for SSL
    have_ssl=true
}
mysql_servers =
(
  {
    address=\"${TARGET_VMIP:-localhost}\"
    port=\"${TARGET_DBMSPort:-3306}\"
    hostgroup=0
    max_connections=200
    use_ssl=1
  }
)
mysql_users = 
(
  {
    username = \"${DBUser:-user}\"
    password = \"${DBPassword:-pass}\"
    default_hostgroup = 0
    max_connections=1000
    default_schema=\"${DBName:-realWorld}\"
    active = 1
    use_ssl=1
  }
)" > /etc/proxysql.cnf

echo "Updated ProxySQL Config for ${AppName}."

echo "Start ProxySQL with config file"
sudo proxysql -c /etc/proxysql.cnf --initial
