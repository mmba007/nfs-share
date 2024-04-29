# Use a Java base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

EXPOSE 8085

COPY target/nfs-share-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java","-jar","nfs-share-0.0.1-SNAPSHOT.jar"]