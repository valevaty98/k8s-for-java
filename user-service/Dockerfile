FROM eclipse-temurin:17

WORKDIR /opt/app
COPY build/libs/user-service-*-SNAPSHOT.jar ./user-service.jar

CMD ["java", "-jar", "./user-service.jar"]
