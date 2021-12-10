Create an application.properties file and insert these values into it.
Set the values based on your database information and custom JWT information

Also in this resources folder, you may find a script for quickly saving products to the PostgreSQL database:

https://github.com/Revature-Nova/NovaBackEnd/blob/main/product-service/src/main/resources/Product%20Database%20script.sql

```
# Spring Data Source Information
spring.datasource.url=
spring.datasource.driverClassName=
spring.datasource.username=
spring.datasource.password=

# Spring JPA & Hibernate
spring.jpa.database-platform=
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
spring.jackson.serialization.fail-on-empty-beans=false

# Configure Eureka Server url
eureka.client.service-url.defaultZone=http://localhost:8010/eureka
server.port=8090
spring.application.name=product-service

# Logging
logging.level.org.hibernate.stat=debug
logging.level.org.hibernate.type=trace

# Web Security
jwt.header=
jwt.prefix=

```
