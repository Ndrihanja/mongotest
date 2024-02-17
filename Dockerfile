FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD target/mongotest-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]