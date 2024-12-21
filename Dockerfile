FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/locatech-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/locatech-0.0.1-SNAPSHOT.jar"]
