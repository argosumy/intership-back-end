FROM openjdk:11

ENV APP_NAME="spd-baraholka"
ENV APP_VERSION="0.0.1-SNAPSHOT"

EXPOSE 8080

WORKDIR /home/spring/${APP_NAME}

COPY ./src/ ./src
COPY ./gradle ./gradle
COPY ./build.gradle ./gradlew ./

RUN addgroup spring && adduser spring --quiet --disabled-password --gecos "" --ingroup spring && \
    chown -R spring:spring /home/spring/${APP_NAME} && \
    ./gradlew bootJar

USER spring:spring

CMD java -jar ./build/libs/${APP_NAME}-${APP_VERSION}.jar