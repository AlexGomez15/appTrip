# Despliegue Profesional

La forma recomendada para produccion es correr la aplicacion con Docker Compose, una base de datos persistente y variables de entorno fuera del codigo.

## 1. Preparar servidor

Instala en el servidor:

```bash
docker --version
docker compose version
git --version
```

## 2. Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/apptrip-springboot.git
cd apptrip-springboot/appTrip
```

## 3. Crear variables de entorno

```bash
cp .env.production.example .env
```

Edita `.env` y cambia las contrasenas por valores fuertes.

## 4. Levantar produccion

```bash
docker compose --env-file .env -f docker-compose.prod.yml up -d --build
```

## 5. Verificar

```bash
docker compose --env-file .env -f docker-compose.prod.yml ps
docker compose --env-file .env -f docker-compose.prod.yml logs -f apptrip
```

Abre:

```text
http://IP_DEL_SERVIDOR:8080/
http://IP_DEL_SERVIDOR:8080/actuator/health
```

## 6. Actualizar version

```bash
git pull
docker compose --env-file .env -f docker-compose.prod.yml up -d --build
```

## 7. Apagar

```bash
docker compose --env-file .env -f docker-compose.prod.yml down
```

No uses `down -v` en produccion, porque elimina el volumen de MySQL.
