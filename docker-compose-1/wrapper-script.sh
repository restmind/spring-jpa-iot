cd
git clone https://github.com/restmind/spring-jpa-iot.git
cd spring-jpa-iot/
mvn install
mvn clean package
timeout 30s mvn spring-boot:run

mvn spring-boot:run