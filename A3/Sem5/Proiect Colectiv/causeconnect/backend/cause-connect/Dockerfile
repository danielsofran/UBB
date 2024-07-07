FROM openjdk:17
WORKDIR /backend
CMD ["./gradlew", "clean", "bootJar"]

COPY create-db.sql /docker-entrypoint-initdb.d/
COPY assets/images /images
COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]