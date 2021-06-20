FROM maven:alpine AS build
COPY ./src /home/app/src
COPY ./pom.xml /home/app
RUN mkdir -p /home/app/repos
COPY ./repos /home/app/repos
WORKDIR /home/app/
RUN mvn clean install

FROM openjdk:alpine
COPY --from=build /home/app/target/*.jar /usr/local/lib/APIGateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/APIGateway.jar"]
