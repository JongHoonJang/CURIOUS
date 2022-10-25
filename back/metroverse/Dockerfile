FROM openjdk:11
WORKDIR /var/jenkins_home/workspace/deploytest/testproject
EXPOSE 8080
ARG JAR_FILE=build/libs/metroverse-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY . .
ENTRYPOINT ["java" , "-jar" , "/app.jar"]
ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata