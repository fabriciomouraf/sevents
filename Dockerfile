FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY ./wait-for /usr/wait-for
RUN chmod +x /usr/wait-for

ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["/usr/wait-for", "db:5432", "--", "java", "-jar", "/app.jar"]