FROM openjdk:8-jdk
FROM mjalas/javafx
COPY . /app/
WORKDIR /app/final_project
RUN javac welcome.java
CMD ["java", "welcome"]