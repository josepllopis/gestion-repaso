# 📘 Gestión Repaso – API REST con Spring Boot, Oracle y Docker

Proyecto backend desarrollado en **Spring Boot** para gestionar tres entidades relacionadas entre sí:  
**Profesores**, **Alumnos** y **Asignaturas**, con relaciones **Many-To-Many** entre ellas.

Incluye:
-  CRUD completo para las tres entidades
-  Vinculación y desvinculación entre profesores ↔ alumnos ↔ asignaturas
- ️ Base de datos **Oracle XE** (dockerizada)
-  Proyecto totalmente **dockerizado**
-  API Documentada con **Swagger/OpenAPI**

---



##  Requisitos

- Java 17+
- Maven
- Docker & Docker Compose

---

##  Ejecutar con Docker

```bash
docker-compose build

docker-compose up
```

---

## 🖥️ Ejecutar localmente (sin Docker)

1. Asegúrate de tener una base de datos **Oracle XE** instalada y en ejecución.
2. Crea o utiliza un usuario y esquema para el proyecto.
3. Configura tu `application.properties` o `application.yml`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XE
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
## Documentación

Swagger UI: http://localhost:8093/swagger-ui/index.html



---

## Autor

Josep Llopis – [GitHub](https://github.com/josepllopis)

---

## Licencia

MIT
