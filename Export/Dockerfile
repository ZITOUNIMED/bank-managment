FROM amazoncorretto:11-alpine-jdk
VOLUME /exportAdminTmp
COPY target/*.jar app.jar 
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]