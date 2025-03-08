`@Repository`:  indicates that the interface is a Spring Data repository and should be treated as a bean in the Spring context. It also enables exception translation, converting database-related exceptions into Spring’s DataAccessException.

`extends JpaRepository`: a Spring Data JPA interface that provides methods for performing CRUD operations on an entity without needing to write any SQL or JPQL.

---

**Java Persistence Query Language (JPQL)** - a query language defined by JPA for querying data from the database.
- **Object-oriented**: JPQL operates on entities (Java objects) rather than directly querying database tables.
- **SQL-like syntax**: Although similar to SQL, JPQL works with entity attributes instead of raw database columns.
- **Named Queries**: JPQL supports predefined queries that can be defined in entity classes using annotations like `@Query`.
---

**Repository-Side Annotations**

`@Query`: This annotation is used to define custom queries for repository methods.
- `name`: Refers to a predefined named query, typically defined in an entity class or a separate configuration class.
- `nativeQuery = true`: Indicates that the query is a **native SQL query** rather than JPQL. Native queries are specific to the database and are executed directly, bypassing the entity mapping.

`Java Persistence Query`: You can define simple methods in the repository interface, and Spring Data JPA automatically interprets them as queries based on the entity’s fields. Spring Data JPA generates the corresponding SQL queries when the methods are executed.

Examples:
- `Account findByHandle(String handle);`
- `Hashtag findByName(String name);`
- `Like_ findByAccountHandleAndPostId(String handle, Long id);`

---
**Entity-Side Annotations**

`@NamedNativeQuery`: Defines a native SQL query within a JPA entity
- `name`: The name of the query, which will be referenced in the repository or service layer.
- `query`: The actual **native SQL query** that will be executed.
- `resultSetMapping`: Specifies how to map the result set of the query into a specific class.

`SqlResultSetMapping`: Maps the result set of a native query to a Java object. Typically, this is used to map the result to a DTO class.
- `@ConstructorResult`: Defines how to map result set columns to constructor parameters in a DTO class.
- `@ColumnResult`: Maps each column from the result set to a specific field in the DTO.