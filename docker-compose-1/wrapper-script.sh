/etc/init.d/mysql start

cd
git clone https://github.com/restmind/spring-jpa-iot.git
cd spring-jpa-iot/
mvn install
mvn clean package
timeout 30s mvn spring-boot:run

export MYSQL_PWD=12345678
/usr/bin/mysql -u root -e "CREATE DATABASE IF NOT EXISTS mydb;"
/usr/bin/mysql -u root -e "FLUSH PRIVILEGES;"
mvn spring-boot:run