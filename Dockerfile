FROM maven:3.8.3-openjdk-17

COPY src /home/app/src

COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=dockerembbed,oauth-security" ,"-jar","/home/app/target/spring_rest_docker.jar"]


