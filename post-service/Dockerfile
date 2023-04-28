FROM eclipse-temurin:17

WORKDIR /opt/app
COPY build/libs/post-service-*-SNAPSHOT.jar/ ./post-service.jar

CMD ["java", "-jar", "./post-service.jar"]
