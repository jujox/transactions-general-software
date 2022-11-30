FROM openjdk:17

WORKDIR /app
COPY target/transactions-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java","-jar","/app/transactions-0.0.1-SNAPSHOT.jar"]

