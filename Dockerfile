# Creacion de imagen base de OpenJdk para ejecutar la aplicacion Java
FROM openjdk:17-jdk-slim

# Definicion del directorio de trabajo dentro del contenedor
WORKDIR /app

# Declaracion del copiado del archivo JAR generado por SpringBoot al contenedor
COPY target/Examen-Desarollo-Web-0.0.1-SNAPSHOT.jar /app/microservicio.jar

# Asignacion del puerto que se usará
EXPOSE 8080

# Ejecucion de la aplicacion al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "/app/microservicio.jar"]