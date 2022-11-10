FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD /NotificationService/target/NotificationService-0.1.jar NotificationService.jar
ENTRYPOINT ["java","-jar","NotificationService.jar"]