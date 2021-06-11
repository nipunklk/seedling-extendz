FROM amd64/openjdk:11.0.8-slim AS BUILD_IMAGE

ENV APP_HOME=/root/dev/myapp/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME

COPY build.gradle.kts gradlew gradle.properties $APP_HOME 
COPY gradle $APP_HOME/gradle

# download dependencies
RUN ./gradlew dependencies
ADD src.tar.xz .
RUN ./gradlew build -x test
RUN pwd
RUN ls build/libs

FROM amd64/openjdk:11.0.8-jre-slim
WORKDIR /root/

COPY --from=BUILD_IMAGE /root/dev/myapp/build/libs/myapp-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=prod","myapp-0.0.1-SNAPSHOT.jar"]
