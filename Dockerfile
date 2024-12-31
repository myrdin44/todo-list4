# Sử dụng OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file JAR của ứng dụng vào container
COPY target/TodoList-0.0.1-SNAPSHOT.jar /app/TodoList-0.0.1-SNAPSHOT.jar

# Chạy ứng dụng Spring Boot
<<<<<<< HEAD
ENTRYPOINT ["java", "-jar", "TodoList-0.0.1-SNAPSHOT.jar"]
=======
ENTRYPOINT ["java", "-jar", "TodoList-0.0.1-SNAPSHOT.jar"]
>>>>>>> c69f1c617c9eb4f5f0ec678118cc2fb176f6efe4
