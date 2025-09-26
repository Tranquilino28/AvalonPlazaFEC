# Etapa 1: build con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

# Copiar pom primero y descargar dependencias (mejor cache)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar el resto del proyecto
COPY . ./

# Construir el jar
RUN mvn clean package -DskipTests

# Etapa 2: imagen final
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 3066

CMD ["java", "-jar", "app.jar"]
