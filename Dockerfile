# Use a Java 17 base image from Amazon Corretto
FROM amazoncorretto:17-alpine

# Set the working directory in the container
WORKDIR /app

EXPOSE 8085

COPY target/nfs-share-0.0.1-SNAPSHOT.jar /app/nfs-share-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","nfs-share-0.0.1-SNAPSHOT.jar"]