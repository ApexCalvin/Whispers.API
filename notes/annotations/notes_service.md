`@Service`: Defines service classes in the service layer that contain business logic and handle operations or computations in the application.

`Bean`: When a class is annotated with @Service, Spring automatically registers the class itself (not just the annotation) as a bean in the application context, making it available for dependency injection via @Autowired, constructor injection, etc.

`@Transactional`: Marks methods or classes as part of a transaction. Spring handles the creation, commit, and rollback of transactions, ensuring that database operations are executed within a single transaction. If an exception occurs, Spring automatically rolls back the transaction to maintain data integrity.