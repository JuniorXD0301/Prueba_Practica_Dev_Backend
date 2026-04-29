# Prueba_Practica_Dev_Backend

## Run with Docker Compose

Start Docker Desktop, then run:

```bash
docker compose up --build
```

The API will be available at:

```text
http://localhost:8080
```

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui.html
```

The OpenAPI JSON spec is available at:

```text
http://localhost:8080/v3/api-docs
```

MySQL is exposed on, because in my local, port 3306 is already in use:

```text
localhost:3307
```

Default database credentials:

```text
Database: franquicias_db
User: franquicias_user
Password: franquicias_password
Root password: root_password
```

Data is persisted in the Docker named volume `mysql_data`.

To stop the containers without deleting the database data:

```bash
docker compose down
```

To stop everything and delete the saved MySQL data:

```bash
docker compose down -v
```
