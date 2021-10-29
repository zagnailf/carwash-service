FROM adoptopenjdk/openjdk16

ARG JAR_FILE=carwash-1.0-SNAPSHOT.jar
COPY build/libs/${JAR_FILE} carwash.jar

EXPOSE 8080

CMD ["java", "-XX:+UseContainerSupport", "-Dspring.config.location=classpath:/application.yml", "-Dspring.profiles.active=container", "-jar", "carwash.jar"]




