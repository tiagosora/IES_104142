# Lab 3 - 104142

IES - Practical Classes - 22/23 - **Lab 2** - **P5** - **Tiago Carvalho** - **104142**

#

# **Accessing databases in SpringBoot**

Jakarta Persistence API (formerly Java Persistence API) is concerned with *persistence*, which loosely means any mechanism by which Java objects outlive the application process that created them. Not all Java objects need to be persisted, but most applications persist key business objects. The JPA specification lets you define *which* objects should be persisted, and *how* they are persisted in your Java applications.

In order to understand this topic, as a stater, it's recommended to complete this guided [tutorial](https://www.baeldung.com/spring-boot-crud-thymeleaf). The tutorial walks you through the main steps; some of the required code is not shown and you can get it from the git repository referred in the article, if needed. You should, however, create the required entities/files by hand and look only for the boilerplate code as needed.

**The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?**

This happens because of the `@AutoWired`annotation that is before the UserController constructor. Autowiring feature of spring framework enables you to inject the object dependency implicitly.

**List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?**

- findAll()
- save()
- findById()
- delete(). 

UserRepository extends these methods defined in the CrudRepository class.

**Where is the data being saved?**

The data is being saved in a (non-persistent) local database.

**Where is the rule for the “not empty” email address defined?**

The `"not empty"` rule is defined on the User entity class.

#

# **Using Postman**

Postman is an API platform for building and using APIs. Postman simplifies each step of the API lifecycle and streamlines collaboration so you can create better APIs. The platform is cloud-native and includes the comprehensive suite of features enterprises are looking for, including SSO, audit, platform security, and much more. Postman can store and manage API specifications, documentation, workflow recipes, test cases and results, metrics, and everything else related to APIs.

Also, consult the print screens taken for the exercise 3 from the pratical guide in [here](https://github.com/tiagosora/IES_104142/tree/master/lab3/PostmanScreenshots). 

For more information, consult [postman tutorials](https://www.postman.com/).

#

# **Review Questions**

### **A) Explain the differences between the RestController and Controller components used in different parts of this lab.**

The `@Controller` annotation represents controllers, followed by `@RequestMapping` annotation for request handling. This way, we need a separate `@ResponseBody`annotation to enable automatic serialization of the returned object.

The `@RestController` is a simplified version of the controller, since it includes the `@Controller` and `@ResponseBody` annotations.

### **B) Create a visualization of the Spring Boot layers (UML diagram or similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers. Describe the role of the elements modeled in the diagram.**

![Ex3_3_UML_Diagram](https://github.com/tiagosora/IES_104142/blob/master/lab3/lab3_3/Ex3_3_UML_Diagram.png)

**Entities:**

- Movie: Class representation of a Movie characterized by an id and a title.
- Quote: Class representation of a Quote characterized by an id, a movie and a text.
- ErrorDetails, GlobalExceptionHandler and ResourceNotFOundException: Exception handling.
- MoviesApplication: Configuration class that declares one or more `@Bean` methods and triggers auto-configuration.

**Controller:**

- MovieController: HTTP request handler, sending data to MoviesService class to be processed.

**Service:**

- MoviesService: Contains all the business logic and interacts with the Repository. It's invoked by the controlle@

- MovieRepository and QuoteRepository interact with the databases, saving application data.

### **C) Explain the annotations `@Table`, `@Colum`, `@Id `found in the Employee entity.**

`@Table` -> When we create a `@Entity` annotation we're representing a Table stored in the database. When we use `@Table` after `@Entity` we're specifying the name of the table, (not be the same as the name of the entity).

### **D) Explain the use of the annotation `@AutoWired` (in the Rest Controller class).**

Autowiring is a way of letting Spring resolve dependencies automatically instead of doing it manually. If we mark some class as a component, during initialization spring creates an instance of this class in its context. Later, when we want to use the object marked with `@Autowired`, Spring will inject this earlier created instance into our program.
