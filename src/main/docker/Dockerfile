####
# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the docker image run:
#
# mvn package
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkusmsa-jvm .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 quarkus/quarkusmsa-jvm
#
###
FROM maven:3.6.1-jdk-8-slim AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM mcr.microsoft.com/java/jdk:8u212-zulu-alpine

# run with user 1001 and be prepared for be running in OpenShift too
RUN adduser -G root --no-create-home --disabled-password 1001 \
  && chown -R 1001 /deployments \
  && chmod -R "g+rwX" /deployments \
  && chown -R 1001:root /deployments
USER 1001


ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV AB_ENABLED=jmx_exporter
COPY --from=build target/lib/* /deployments/lib/
COPY --from=build target/*-runner.jar /deployments/app.jar

RUN "cat /deployments/run-java.sh"

ENTRYPOINT [ "/deployments/run-java.sh" ]
EXPOSE 8080