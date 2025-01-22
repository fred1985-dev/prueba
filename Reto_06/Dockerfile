# Etapa de construcción: Usar Maven para compilar y empaquetar la aplicación
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar solo los archivos necesarios para la construcción
COPY pom.xml .
COPY src /app/src

# Ejecutar Maven para construir el proyecto, sin ejecutar las pruebas
RUN mvn clean package -DskipTests

# Etapa de ejecución: Usar una imagen ligera de OpenJDK para ejecutar la aplicación
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo para la ejecución
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción al contenedor de ejecución
COPY --from=build /app/target/prueba-0.0.1-SNAPSHOT.jar /app/prueba.jar

# Exponer el puerto 8080 para que la aplicación sea accesible
EXPOSE 8080

# Establecer el comando de inicio para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/prueba.jar"]
