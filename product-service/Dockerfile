FROM java:8

EXPOSE 8090

ADD target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]