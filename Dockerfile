FROM openjdk:8-jdk-alpine AS GRADLE_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

FROM openjdk:8-jdk-alpine
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=GRADLE_BUILD_IMAGE $APP_HOME/build/libs/*.jar $APP_HOME/app.jar
EXPOSE 8080
#ENTRYPOINT java -jar app.jar
CMD java -jar app.jar