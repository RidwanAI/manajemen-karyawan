FROM openjdk:25-ea-21-jdk-slim

VOLUME /tmp

ARG JAR_FILE=target/manajemen-karyawan-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]