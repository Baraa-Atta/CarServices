FROM openjdk:11
WORKDIR /tmp
ADD target/CarService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]