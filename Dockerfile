FROM openjdk:8-jdk-alpine
ADD target/imagehosting.war app.war
EXPOSE 8080
