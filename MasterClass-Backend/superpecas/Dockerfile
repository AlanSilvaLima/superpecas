# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:21-jdk
MAINTAINER Fred Werneck
EXPOSE 8080/tcp
# copy application WAR (with libraries inside)
COPY target/superpecas-0.0.1-SNAPSHOT.war /app.war
# specify default command
CMD ["java", "-jar", "-Dspring.profiles.active=prd","/app.war"]