FROM openjdk:11

ENV APP_NAME="spd-baraholka"
ENV APP_VERSION="0.0.1-SNAPSHOT"
ENV HOST="http://localhost:8080"

EXPOSE 8080

WORKDIR /home/spring/${APP_NAME}

COPY ./src/ ./src
COPY ./test-data ./test-data
COPY ./gradle ./gradle
COPY ./build.gradle ./gradlew ./

RUN addgroup spring && adduser spring --quiet --disabled-password --gecos "" --ingroup spring && \
    chown -R spring:spring /home/spring/${APP_NAME}

RUN ./gradlew bootJar

USER spring:spring

CMD java -jar ./build/libs/${APP_NAME}-${APP_VERSION}.jar