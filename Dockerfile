FROM openjdk:8

COPY build/libs/zs-java-assignments-1.0-SNAPSHOT.jar/ app.jar

CMD ["java", "-jar", "app.jar"]