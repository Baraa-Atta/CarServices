FROM openjdk:11
WORKDIR /tmp
ADD target/CarDetails-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]
