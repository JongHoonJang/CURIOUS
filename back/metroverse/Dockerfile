FROM openjdk:11
RUN chmod +x gradlew
RUN ./gradlew build
WORKDIR /var/jenkins_home/workspace/deployProject/back/metroverse
COPY . .
EXPOSE 8080
ARG JAR_FILE=build/libs/metroverse-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java" , "-jar" , "/app.jar"]
ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata