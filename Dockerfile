FROM eclipse-temurin:25-jre-alpine

WORKDIR /run
COPY build/libs/*-server-*.jar server.jar

EXPOSE 8080
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "server.jar"]
