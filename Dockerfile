FROM openjdk:11-jdk-oracle
COPY build/libs/usdexchange-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]