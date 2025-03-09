# Prueva Técnica Desarrollo Web VentureSoft

Este proyecto es un microservicio desarrollado con Spring Boot, que utiliza Docker para su despliegue y Swagger para la documentación de la API. 
Está diseñado para facilitar la creación y gestión de las entidades `HuCatMoneda` y `HuEmps` con una base de datos embebida en H2.

## Descripción

La aplicación proporciona una API RESTful para la gestión de monedas y empleados, con endpoints para crear y consultar datos. 
La aplicación está completamente configurada para ser ejecutada dentro de un contenedor Docker.

## Requisitos

Para ejecutar este proyecto, necesitas tener instalados los siguientes programas en tu máquina:
- **Docker**: [Descargar Docker](https://www.docker.com/products/docker-desktop)
- **Git** (si deseas clonar el repositorio)

## Instalación y Ejecución

1. **Clonar el Repositorio**
   Clona el repositorio en tu máquina local:
   ```bash
   git clone <https://github.com/NestorG4/venturesoft.com.git>
2. **Construir la imagen y levantar el contenedor**
   En la raiz del proyecto donde se encuentra el archivo docker-compose.yml ejecuta lo siguiente:
   docker compose up --build
3. **Acceso al proyecto**
   API principal:
   ```
   http://localhost:8080
   ```
   Documentación de Swagger UI (para ver y probar la API):
   ```
   http://localhost:8080/swagger-ui.html
   ```
   Consola de base de datos H2:
   ```
   http://localhost:8080/h2-console/
   ```
4. **Realizar peticiones a la API**
   Puedes realizar peticiones a los diferentes endpoints usando Swagger UI o herramientas como Postman.
   Swagger UI te permitirá ver los endpoints de la API de forma visual y hacer peticiones directamente desde tu navegador.
   Algunos de los endpoints disponibles son:
   ```
   POST /api/huCatMoneda/crearMoneda – Crear una nueva moneda.
   ```
   ```
   GET /api/huCatMoneda/consultarMonedas – Obtener todas las monedas.
   ```
   ```
   POST /api/huEmps/crearEmpleado – Crear un nuevo empleado.
   ```
   ```
   GET /api/huEmps/consultarEmpleados – Obtener todos los empleados.
   ```
```
