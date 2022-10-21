FROM amazoncorretto:11-alpine-jdk
VOLUME /discoveryServiceTmp
COPY target/*.jar app.jar 
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]