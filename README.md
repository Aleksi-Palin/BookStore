# BookStore

BookStore is a Spring Boot application for managing a small collection of books and their categories. It provides a web interface where users can view, add, edit, and delete books.

The project uses Thymeleaf for server-rendered pages, PostgreSQL for persistent data, and Spring Data JPA for database access. It also includes REST functionality: custom endpoints provide book data, while Spring Data REST exposes repository resources for books and categories.

When the application starts with an empty book table, it creates a few sample categories and books so the application can be tested immediately.

## Technical overview

- Java 21 and Spring Boot
- Maven build using the included Maven wrapper
- Spring MVC and Thymeleaf for the web pages
- Spring Data JPA with PostgreSQL for persistence
- `Book` and `Category` JPA entities connected by a many-to-one relationship
- `BookRepository` and `CategoryRepository` based on `CrudRepository`
- Spring Data REST resources available under `/api`
- Custom JSON endpoints at `/jsonBooklist` and `/bookById/{id}`

The application runs on port `8080` by default. Start it with:

```powershell
./mvnw.cmd spring-boot:run
```
