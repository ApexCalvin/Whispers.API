**Lombok** - A Java library designed to reduce boilerplate code by generating common methods at compile time.


- `@NoArgsConstructor`: Generates a default (nullary) constructor, which is required by JPA frameworks for entity instantiation.
- `@AllArgsConstructor`: Generates a constructor with parameters for all fields in a class.
- `@Data`: Combines the following Lombok annotations:
    - `@Getter`: Generates getter methods for all fields.
    - `@Setter`: Generates setter methods for all fields.
    - `@ToString`: Generates the `toString()` method.
    - `@EqualsAndHashCode`: Generates `equals()` and `hashCode()` methods.
    - `@RequiredArgsConstructor`: Generates a constructor for all **required** (final and annotated with `@NonNull`) fields. Does not automatically create constructor overloads.
---

__Java Persistence API (JPA)__ - A specification (not an implementation) for Object-Relational Mapping (ORM) in Java. JPA allows Java applications to interact with relational databases using Java objects (entities), abstracting away the database details and enabling focus on Java objects instead of raw SQL.

**Hibernate** - the most common JPA implementation. It implements the JPA standard and adds extra capabilities.

**Persist**: Refers to saving an object in a database table, storing its state (field values), and allowing it to be retrieved and manipulated later.

- `@Entity`: Marks a class as a JPA entity, meaning it will be persisted in the database.
- `@Table`: Defines details about the table that will represent the entity in the database.
    - `name`: Specifies the table name in the database.
    - `uniqueConstraints = @UniqueConstraint(columnNames = "handle")`: Ensures that values in the "handle" column are unique across all records in the "account" table, preventing duplicate entries.
- `@Id`: Marks a field as the primary key of the entity.
- `@GeneratedValue`: Defines how the primary key field should be generated.
    - `strategy`: Specifies the generation strategy.
      - `GenerationType.IDENTITY`: Uses an auto-incremented value for the primary key.
- `@Column`: Specifies details of how a field is mapped to a database column.
    - `nullable`: Defines whether the column can contain NULL values.
---

**Relationships and Serialization**

- `@OneToMany`: Defines a one-to-many relationship between entities.
  - `mappedBy`: Specifies the inverse side and points to the owning field.
  - `cascade`: Manages parent-child relationships and ensures child entities are managed along with the parent entity.
    - `CascadeType.ALL`: Applies all persistence operations (e.g., `persist()`, `merge()`, `remove()`) to child entities.
- `@ManyToOne`: Defines a many-to-one relationship between entities.
- `@ManyToMany`: Defines a many-to-many relationship between entities.
  - `fetch`: Specifies how and when associated entities are loaded from the database.
    - `FetchType.LAZY`: Entities are loaded **only when explicitly requested**.
    - `FetchType.EAGER`: Entities are **immediately loaded** along with the parent entity.
- `@JointTable`: Defines the join table that connects the two entities in a many-to-many relationship.
  - `name`: Specifies the name of the join (cross-reference) table that holds the relationships.
  - `joinColumns = @JoinColumn(name = "post_id")`: Defines the foreign key column (post_id) linking to the "Post" entity in the join table.
  - `inverseJoinColumns = @JoinColumn(name = "hashtag_id")`: Defines the foreign key column (hashtag_id) linking to the "Hashtag" entity in the join table.
- `@JsonManagedReference`: A Jackson annotation used on the **parent side** of a bidirectional relationship to prevent infinite recursion during JSON serialization by managing circular references.
- `@JsonBackReference`: Applied to the **back side** of a bidirectional relationship, this annotation prevents infinite recursion by excluding the field from JSON serialization.
- `@JsonIgnoreProperties`: A Jackson annotation used to **ignore specific properties** during JSON serialization and deserialization. It allows you to exclude one or more fields from being included in the serialized JSON output or from being deserialized into the Java object.
- `@Transient`: Marks a field as not persistent, meaning it won’t be stored in the database, but can still be used in the Java object for temporary or calculated values.

