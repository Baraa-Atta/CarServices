FROM openjdk:11
WORKDIR /tmp
ADD target/CarReservation-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
