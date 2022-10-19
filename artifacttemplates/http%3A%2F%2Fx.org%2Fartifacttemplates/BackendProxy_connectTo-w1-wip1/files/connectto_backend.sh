#!/bin/bash

cd /var/www/java/${SOURCE_AppName}
echo "Connect $SOURCE_AppName to $TARGET_AppName on $TARGET_VMIP:$TARGET_Port"
sed -i "s/localhost:9990/$TARGET_VMIP:$TARGET_Port/" application.yml