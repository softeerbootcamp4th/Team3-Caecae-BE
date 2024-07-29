FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} caecae.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/caecae.jar"]