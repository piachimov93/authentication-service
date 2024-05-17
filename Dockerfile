FROM openjdk:17
EXPOSE 8090
COPY target/authentication-service.jar authentication-service.jar
ENTRYPOINT ["java", "-jar", "authentication-service.jar"]