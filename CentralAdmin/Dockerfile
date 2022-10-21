FROM amazoncorretto:11-alpine-jdk
VOLUME /centralAdminTmp
COPY target/*.jar app.jar 
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]