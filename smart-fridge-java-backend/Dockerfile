FROM eclipse-temurin:26-jdk AS builder
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:26-jdk
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
