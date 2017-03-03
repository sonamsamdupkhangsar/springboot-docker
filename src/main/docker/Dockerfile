FROM frolvlad/alpine-oraclejdk8:slim
#VOLUME /tmp
ADD springboot-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]
