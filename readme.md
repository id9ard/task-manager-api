# Task Manager API

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.13-brightgreen.svg)
![JWT](https://img.shields.io/badge/JWT-Authentication-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED.svg)

API REST construida con **Spring Boot** para gestionar tareas, con autenticación basada en **JWT** (JSON Web Token). Proyecto diseñado con arquitectura en capas (Controller, Service, Repository) y buenas prácticas de seguridad.

## ✨ Características principales

- Registro e inicio de sesión de usuarios (contraseñas encriptadas con BCrypt).
- Autenticación stateless mediante JWT.
- CRUD completo de tareas (crear, listar, actualizar, eliminar).
- Protección de rutas: solo usuarios autenticados pueden acceder a `/api/tasks/**`.
- Base de datos MySQL con Docker (portable y fácil de levantar).
- Manejo de excepciones y validaciones básicas.
- Preparado para ser desplegado en la nube (Render, Railway, etc.).

## 🧱 Tecnologías utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|------------|
| Java | 21 | Lenguaje base |
| Spring Boot | 3.5.13 | Framework principal |
| Spring Web | - | Creación de API REST |
| Spring Data JPA | - | Persistencia y ORM |
| Spring Security | - | Autenticación y autorización |
| JJWT | 0.12.6 | Generación y validación de JWT |
| MySQL | 8.0 | Base de datos relacional |
| Docker | - | Contenedor para MySQL |
| Maven | - | Gestión de dependencias y build |
| Jakarta Validation | - | Validación de datos de entrada |

📡 Endpoints de la API

- Base URL: http://localhost:8082

# Endpoints públicos

Método  Ruta                    Descripción
GET     /hola                   Endpoint de prueba (verifica que la API corre)
POST    /api/auth/register      Registra un nuevo usuario
POST    /api/auth/login         Inicia sesión y devuelve un JWT

# Endpoints protegidos (requieren JWT)

Método  Ruta                    Descripción
GET     /api/tasks              Obtener todas las tareas
GET     /api/tasks/{id}         Obtener una tarea por ID
POST    /api/tasks              Crear una nueva tarea
PUT     /api/tasks/{id}         Actualizar una tarea existente
DELETE  /api/tasks/{id}         Eliminar una tarea

📦 Ejemplos de peticiones

# Registro

- POST /api/auth/register
- Content-Type: application/json
    {
    "username": "juanperez",
    "email": "juan@mail.com",
    "password": "123456"
    }

# Login

- POST /api/auth/login
- Content-Type: application/json
    {
    "username": "juanperez",
    "password": "123456"
    }

# Crear tarea (autenticado)

- POST /api/tasks
- Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
- Content-Type: application/json
    {
    "title": "Estudiar Spring Security",
    "description": "Completar el módulo de JWT",
    "completed": false
    }

🚀 Instrucciones para ejecutar localmente

# Requisitos previos

- Java 21 (OpenJDK)
- Docker y Docker Compose (opcional, pero recomendado)
- Git
- Maven (opcional, se puede usar el wrapper ./mvnw)

# Clonar el repositorio

- git clone https://github.com/tu-usuario/task-manager-api.git
- cd task-manager-api

# Levantar MySQL con Docker

- El proyecto está configurado para usar MySQL en el puerto 3307. Puedes levantar el contenedor con el siguiente comando:
docker run --name task-manager-mysql \
  -e MYSQL_ROOT_PASSWORD=task123 \
  -e MYSQL_DATABASE=task_api_db \
  -p 3307:3306 \
  -d mysql:8.0
- Nota: La contraseña, puerto y nombre de la base de datos coinciden con la configuración por defecto en application.properties. Si los cambias, actualiza también las propiedades de conexión.

# Ejecutar la aplicación

- Con Maven wrapper (Linux/Mac):
    ./mvnw spring-boot:run
- O usando Maven global:
    mvn spring-boot:run
- La API estará disponible en http://localhost:8082

# Verificación rápida

- curl http://localhost:8082/hola
- Respuesta esperada: API funcionando con Spring Boot 3.5.13!

🧪 Pruebas con Postman

- Puedes importar la colección de Postman desde el archivo TaskManager.postman_collection.json (incluido en el repositorio). Si no está, aquí tienes los pasos manuales:
- Registrar usuario: POST /api/auth/register
- Login y copiar el token de la respuesta.
- En las peticiones a tareas, agregar el header:
    Key: Authorization
    Value: Bearer <token_copiado>

🐳 Uso con Docker Compose (opcional)

- Si deseas levantar tanto la base de datos como la aplicación Spring Boot con un solo comando, puedes crear un docker-compose.yml similar a:
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: task123
      MYSQL_DATABASE: task_api_db
    ports:
      - "3307:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  app:
    build: .
    ports:
      - "8082:8082"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/task_api_db?useSSL=false&serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: task123
      APP_JWT_SECRET: "cambia-esta-clave-por-una-muy-segura-y-larga"
      APP_JWT_EXPIRATIONMS: "86400000"

volumes:
  mysql_data:

- Y luego ejecutar:
    docker-compose up -d

🔒 Nota de seguridad

- Nunca subas el archivo application.properties a un repositorio público si contiene contraseñas o claves secretas.
- El repositorio incluye un archivo .gitignore que excluye application.properties, target/ y archivos de configuración del IDE.

📄 Licencia

Este proyecto es de uso educativo y libre. Puedes usarlo como base para tu portfolio.
Desarrollado por Louis Brossard
![Github](https://github.com/id9ard) | ![Linkedin](https://www.linkedin.com/in/brossui)