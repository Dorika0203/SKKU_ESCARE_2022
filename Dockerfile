FROM docker:latest
ARG BUILDX_VER=0.4.2
RUN mkdir -p /root/.docker/cli-plugins && \
    wget -qO ~/.docker/cli-plugins/docker-buildx \
    https://github.com/docker/buildx/releases/download/v${BUILDX_VER}/buildx-v${BUILDX_VER}.linux-amd64 && \
    chmod +x /root/.docker/cli-plugins/docker-buildx
FROM openjdk:16
ARG JAR_FILE=build/libs/*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
EXPOSE 8080
