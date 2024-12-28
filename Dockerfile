# Sử dụng OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Cài đặt curl để tải wait-for-it
RUN apt-get update && apt-get install -y curl \
    && curl -o /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x /wait-for-it.sh

# Copy file JAR của ứng dụng vào container
COPY target/TodoList-0.0.1-SNAPSHOT.jar /app/TodoList-0.0.1-SNAPSHOT.jar
# Chạy ứng dụng Spring Boot
ENTRYPOINT ["/wait-for-it.sh", "db:5432", "--", "java", "-jar", "TodoList-0.0.1-SNAPSHOT.jar"]