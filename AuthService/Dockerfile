FROM amazoncorretto:11-alpine-jdk
VOLUME /authServiceAdminTmp
COPY ./AuthService/target/*.jar app.jar 
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]