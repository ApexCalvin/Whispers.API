`@RestController`: This is a specialized version of `@Controller` in Spring. It marks the class as a controller where every method is treated as a RESTful web service endpoint. It's automatically configured to return data (typically JSON) directly, without needing to return a view (like `@Controller` would do in a traditional web application).

---
**Inversion of Control (IoC)** - a design principle in which the control of object creation and dependency management is given to a framework (like Spring) instead of being manually handled by the developer.

In traditional programming, an object creates its own dependencies. But with IoC, a container (like Spring) takes control and injects the dependencies where needed.

---

Without IoC (traditional approach):

```Java
public class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine();  // Car is responsible for creating Engine
    }
}
```

Here, the `Car` class is responsible for creating its own `Engine` object. This makes it **tightly coupled** and harder to change or test.

---

With IoC (using Spring Dependency Injection):

```Java
@Service
public class Car {
    private final Engine engine;

    public Car(Engine engine) {  // Engine is provided from outside (IoC)
        this.engine = engine;
    }
}
```

Now, the `Car` class doesn’t create its own Engine. Instead, Spring **injects** it automatically. This makes the code loosely coupled, flexible, and testable.

---

`Dependency Injection (DI)`:  A design pattern where an object's dependencies (other objects it needs to function) are provided by an external system (like Spring) rather than being created by the object itself. This promotes loose coupling and makes it easier to manage dependencies, test, and maintain the code.
- `"hey, Car, you don't need to create your own engine — I will provide it for you."`

---

`Constructor Injection`: A specific type of dependency injection, where dependencies are provided to a class through its constructor. he class declares its dependencies as constructor parameters, and then these dependencies are passed when an object of that class is created.
- `"When I create this Car, I need to give it an Engine right away, so I’ll pass the Engine to it in the constructor."`

```Java
@Service
public class Car {
    private final Engine engine;

    public Car(Engine engine) {  // Constructor Injection
        this.engine = engine;
    }
}
```
Constructor injection is a way of providing dependencies to a class via its constructor. Spring will automatically call the constructor and pass the required dependencies when it creates the object. It's the recommended method for dependency injection because it makes it clear which dependencies a class needs. It also allows the dependencies to be immutable and ensures that the class is properly initialized with all necessary dependencies.

---

`@Autowired`: This annotation is used by Spring to automatically inject a dependency into a class. It can be applied to a field, setter method, or constructor. Spring will automatically find and inject the correct bean (dependency) when it initializes the class. You use it when you want Spring to automatically inject a bean into a class, either via the constructor, setter, or field injection.
- no longer needed after Spring 4.3+
- Spring will auto inject dependencies if there is only one constructor

```Java
@Service
public class Car {
    private final Engine engine;

    @Autowired  // Explicitly telling Spring to inject the dependency
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Here, the `@Autowired` annotation is telling Spring, "Inject the `Engine` dependency into this constructor." You would use `@Autowired` if you have multiple constructors or are using Setter Injection or Field Injection.

---
**Mapping annotations** - shortcuts for handling differrent HTTP requests in a Spring controller.
- `@RequestMapping()`
- `@GetMapping`
- `@PostMapping`
- `@DeleteMapping`
- `@PatchMapping`
- `@PutMapping`

`@PathVariable`: Used to capture dynamic parts of the URL.

`@RequestBody`: Used to capture the data sent in the body of the request and convert it into a Java object for use in your methods.

---
`@ResponseStatus(HttpStatus.BAD_REQUEST)`:

`@ExceptionHandler(MethodArgumentNotValidException.class)`:
