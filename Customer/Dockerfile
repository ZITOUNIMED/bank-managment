FROM amazoncorretto:11-alpine-jdk
VOLUME /customerTmp
COPY target/*.jar app.jar 
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]