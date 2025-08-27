# Etapa 1: build do projeto
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagem final
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR
COPY --from=build /app/target/apolice-0.0.1-SNAPSHOT.jar app.jar

# Expõe porta
EXPOSE 8080

# Entrypoint usando profile default (opcional, pode ser sobrescrito pelo Compose)
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=developer"]
