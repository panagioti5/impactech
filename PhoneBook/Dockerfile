FROM maven:alpine AS build
COPY ./src /home/app/src
COPY ./pom.xml /home/app
WORKDIR /home/app/
RUN mvn clean install

FROM openjdk:alpine
COPY --from=build /home/app/target/*.jar /usr/local/lib/PhoneBook.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/usr/local/lib/PhoneBook.jar"]
