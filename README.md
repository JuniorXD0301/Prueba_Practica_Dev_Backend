# Prueba_Practica_Dev_Backend

## Ejecutar con Docker Compose

Inicia Docker Desktop, luego ejecuta:

```bash
docker compose up --build
```

La API estará disponible en:
```text
http://localhost:8080
```

La interfaz de Swagger UI estará disponible en:
```text
http://localhost:8080/swagger-ui.html
```

La especificación OpenAPI en formato JSON estará disponible en:
```text
http://localhost:8080/v3/api-docs
```

MySQL está expuesto en el siguiente puerto (debido a que el puerto 3306 ya está en uso en mi entorno local):
```text
localhost:3307
```

Credenciales de la base de datos por defecto:
```text
Base de datos: franquicias_db
Usuario: franquicias_user
Contraseña: franquicias_password
Contraseña root: root_password
```

Los datos se persisten en el volumen nombrado de Docker `mysql_data`.

Para detener los contenedores sin eliminar los datos de la base de datos:
```bash
docker compose down
```

Para detener todo y eliminar los datos guardados de MySQL:
```bash
docker compose down -v
```
