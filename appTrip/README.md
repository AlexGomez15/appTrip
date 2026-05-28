# AppTrip

Aplicacion Spring Boot MVC para gestionar trips, categorias, usuarios, roles y perfiles.

## Requisitos

- Java 17
- Maven 3.9+
- MySQL 8+

## Configuracion

La aplicacion usa perfiles de Spring:

- `dev`: perfil local por defecto, con `ddl-auto=update`, SQL visible y datos demo.
- `prod`: perfil de produccion, sin datos demo, sin SQL visible, Thymeleaf con cache y esquema validado.

Variables esperadas en produccion:

```properties
SPRING_PROFILES_ACTIVE=prod
PORT=8080
DB_URL=jdbc:mysql://host:3306/apptrip?useSSL=false&serverTimezone=America/El_Salvador&allowPublicKeyRetrieval=true
DB_USERNAME=apptrip_user
DB_PASSWORD=change-me
```

Hay una plantilla en `.env.example`.

## Base de datos

Crear la base de datos antes de iniciar la app:

```sql
CREATE DATABASE apptrip CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

El script inicial esta en:

```text
src/main/resources/database/apptrip.sql
```

En produccion `spring.jpa.hibernate.ddl-auto=validate`, asi que las tablas deben existir antes del arranque.

## Ejecutar local

```bash
mvn spring-boot:run
```

Abrir:

```text
http://localhost:8080/
```

Salud de la app:

```text
http://localhost:8080/actuator/health
```

## Build de produccion

```bash
mvn clean package
```

El jar queda en:

```text
target/apptrip.jar
```

Ejecutar el jar con perfil de produccion:

```bash
java -jar target/apptrip.jar --spring.profiles.active=prod
```

## Docker

Construir la imagen:

```bash
docker build -t apptrip:1.0.0 .
```

Ejecutar:

```bash
docker run --rm -p 8080:8080 --env-file .env apptrip:1.0.0
```

## Rutas principales

```text
/                  Inicio con cards de Trips
/tabla             Tabla de Trips
/trips/view/{id}   Detalle de un Trip
/trips/create      Crear Trip
/categorias/index  Listar categorias
/categorias/create Crear categoria
/usuarios/index    Listar usuarios
```
