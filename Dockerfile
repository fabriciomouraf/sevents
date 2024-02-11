FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh

ENV SPRING_PROFILES_ACTIVE=dev

CMD ["java", "-jar", "app.jar"]