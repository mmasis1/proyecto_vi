#Version con jdk17
FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests
 
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/proyecto_gym-1.jar proyecto_gym.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","proyecto_gym.jar"]