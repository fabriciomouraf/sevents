FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY wait-for.sh /usr/wait-for.sh
RUN chmod +x /usr/wait-for.sh

ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["/usr/wait-for.sh", "db:5432", "--", "java", "-jar", "/app.jar"]