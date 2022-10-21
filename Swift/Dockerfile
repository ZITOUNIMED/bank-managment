FROM amazoncorretto:11-alpine-jdk
VOLUME /swiftAdminTmp
COPY target/*.jar app.jar 
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]