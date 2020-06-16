FROM openjdk:8
EXPOSE 8081
ADD target/edge-service.jar edge-service.jar
ENTRYPOINT ["java","-jar","/edge-service.jar"]