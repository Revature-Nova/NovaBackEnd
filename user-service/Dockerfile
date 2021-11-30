FROM java:8

EXPOSE 8010

ADD target/user-service.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]
