FROM java:8

EXPOSE 8082

ADD target/zuul-service-0.0.1-SNAPSHOT.jar zuul-service.jar

ENTRYPOINT ["java", "-jar", "zuul-service.jar"]