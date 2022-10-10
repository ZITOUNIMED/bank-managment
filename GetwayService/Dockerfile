FROM amazoncorretto:11-alpine-jdk
VOLUME /getwayServiceTmp
COPY ./GetwayService/target/*.jar app.jar 
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]