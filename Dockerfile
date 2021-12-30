FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y openjdk-16-jdk
COPY build/libs/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]
EXPOSE 8080

#FROM openjdk:16
#COPY build/libs/*.war app.war
#ENTRYPOINT ["java","-jar","/app.war"]
#EXPOSE 8080