FROM alpine:latest

RUN apk add --no-cache openjdk21-jdk maven

WORKDIR /app
COPY pom.xml .
COPY src ./src
CMD ["mvn", "clean", "test"]