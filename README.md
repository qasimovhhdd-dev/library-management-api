# Library Management API

Sadə Kitabxana İdarəetməsi REST API-si. Spring Boot, Spring Data JPA və PostgreSQL istifadə edilərək qurulub.

## Texnologiyalar
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- Springdoc OpenAPI (Swagger)

## Quraşdırma

1. Repo-nu clone et:
```bash
git clone https://github.com/USERNAME/library-management-api.git
```

2. PostgreSQL-də verilənlər bazası yarat:
```sql
CREATE DATABASE library_db;
```

3. `src/main/resources/application.properties` faylında öz DB ayarlarını yaz:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=postgres
spring.datasource.password=your_password_here
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Layihəni run et (IntelliJ-də `LibraryApplication.java`-nı run et, və ya terminal-da):
```bash
./gradlew bootRun
```

## API Sənədləşdirməsi (Swagger)

Layihə run olandan sonra, brauzerdə aç:
http://localhost:8080/swagger-ui/index.html

## Endpoint-lər

### Books
- `POST /api/books` — yeni kitab yarat
- `GET /api/books?page=0&size=10&sort=title,asc` — kitabları səhifə-səhifə göstər
- `GET /api/books/{id}` — konkret kitabı göstər
- `PUT /api/books/{id}` — kitabı yenilə
- `DELETE /api/books/{id}` — kitabı sil

### Authors
- `POST /api/authors` — yeni müəllif yarat
- `GET /api/authors` — bütün müəllifləri göstər
- `GET /api/authors/{id}` — konkret müəllifi göstər
- `PUT /api/authors/{id}` — müəllifi yenilə
- `DELETE /api/authors/{id}` — müəllifi sil