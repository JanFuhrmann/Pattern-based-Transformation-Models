#!/bin/bash

echo "VMIP: "$VMIP
echo "DBName: "$DBName
echo "DBMSPort: "$DBMSPort
echo "DBUser: "$DBUser
echo "DBPassword: "$DBPassword
echo "AppName: "$AppName

cd /var/www/java/${AppName}

# https://www.gnu.org/software/bash/manual/html_node/Shell-Parameter-Expansion.html
echo "spring.datasource.url=jdbc:mysql://${VMIP:-127.0.0.1}:${DBMSPort:-3306}/$DBName
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=$DBUser
spring.datasource.password=$DBPassword
spring.jackson.deserialization.UNWRAP_ROOT_VALUE=true

image.default=https://static.productionready.io/images/smiley-cyrus.jpg

jwt.secret=nRvyYC4soFxBdZ-F-5Nnzz5USXstR1YylsTd-mA0aKtI9HUlriGrtkf-TiuDapkLiUCogO3JOK7kwZisrHp6wA
jwt.sessionTime=86400

mybatis.configuration.cache-enabled=true
mybatis.configuration.default-statement-timeout=3000
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.use-generated-keys=true
mybatis.type-handlers-package=io.spring.infrastructure.mybatis
mybatis.mapper-locations=mapper/*.xml

logging.level.io.spring.infrastructure.mybatis.readservice.ArticleReadService=DEBUG
logging.level.io.spring.infrastructure.mybatis.mapper=DEBUG" >> application.properties

echo "Updated Application Properties for ${AppName}."

kill -9 $(cat ${AppName}.pid)

nohup java -jar ${AppName}.jar > /var/log/${AppName}.log 2>&1 &
echo $! > ${AppName}.pid

echo "Started Application again with process ID $(cat ${AppName}.pid)"
