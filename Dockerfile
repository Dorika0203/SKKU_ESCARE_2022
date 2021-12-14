#FROM openjdk:16
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJar
#
#FROM openjdk:16
#COPY --from=builder build/libs/*.jar app.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]



FROM openjdk:16
RUN mkdir /app
ADD build/libs/app.war /app
CMD [ "java", "-jar", "/app/app.war" ]
EXPOSE 8080