**Data Transfer Object (DTO)** - A design pattern used to transfer data between different layers or components of an application, especially in distributed systems or web services. A DTO encapsulates data, typically without any business logic, and is used to efficiently transfer data between different parts of an application or across networks.
- `RequestDTO`: Used for incoming data, which will be serviced and mapped to an entity for storage
- `ResponseDTO`: Used for outgoing data, which is mapped from an entity for data transfer.

---

DTO Validation Annotations:

`@NotNull`: Ensures that a field or parameter is not null. It’s typically used to validate that a value must be provided.

`@NotBlank`: Validates that a string is not null, empty, or just whitespace. It’s used for text fields to ensure the input is meaningful and non-empty.

`message`: Customizes the error message that is displayed when the validation constraint is violated.

