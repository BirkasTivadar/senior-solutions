FROM adoptopenjdk:16-jre-hotspot
WORKDIR /opt/app
COPY target/authors-0.0.1-SNAPSHOT.jar authors.jar
CMD["java", "-jar", "authors,jar"]