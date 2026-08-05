# Estágio 1: Compilação (Build)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copia os arquivos de configuração do Maven para baixar as dependências em cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e gera o arquivo .jar final (ignora os testes para agilizar)
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia apenas o .jar gerado no estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]