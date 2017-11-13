FROM maven:3-jdk-8-alpine
ADD . /imagehosting-app/
WORKDIR /imagehosting-app
RUN mvn package
EXPOSE 8080
CMD ["java", "-jar", "/imagehosting-app/target/imagehosting.jar"]
