FROM java:8

EXPOSE 8089

ADD target/user-service.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]
