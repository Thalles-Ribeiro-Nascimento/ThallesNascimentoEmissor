# Etapa 1: Escolha a imagem base do Maven para compilar o código Java
FROM maven:latest AS build

# Defina o diretório de trabalho para /app
WORKDIR /app

# Copie o arquivo pom.xml e a pasta src para o contêiner
COPY pom.xml .
COPY src ./src

# Execute o comando para compilar e gerar o .jar
RUN mvn clean package -DskipTests

# Etapa 2: Escolha a imagem base do JDK para rodar a aplicação
FROM openjdk:17

# Defina o diretório de trabalho para /app
WORKDIR /app

# Copie o arquivo .jar gerado na etapa de build para a imagem final
COPY --from=build /app/target/*.jar ThallesNascimentoEmissor.jar

# Exponha a porta que a aplicação vai usar
EXPOSE 5454

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "ThallesNascimentoEmissor.jar"]