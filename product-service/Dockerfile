FROM java:8

EXPOSE 8090

ADD target/product-service.jar product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]