FROM dalongrong/alpine-oraclejdk8:8.131.11-slim-arthas
VOLUME /tmp
ADD  target/graphqlize-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
