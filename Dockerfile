FROM openjdk:8-jdk-alpine
ADD target/imagehosting.jar /imagehosting.jar
EXPOSE 8080
CMD ["java", "-jar", "/imagehosting.jar"]
