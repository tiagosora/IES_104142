# Lab 3 - 104142

IES - Practical Classes - 22/23 - **Lab 2** - **P5** - **Tiago Carvalho** - **104142**

#

# Server-side Programming with Servlets

# Review Questions

### **A) Explain the differences between the RestController and Controller components used in different parts of this lab.**

The `@Controller` annotation represents controllers, followed by `@RequestMapping` annotation for request handling. This way, we need a separate `@ResponseBody`annotation to enable automatic serialization of the returned object.

The `@RestController` is a simplified version of the controller, since it includes the `@Controller` and `@ResponseBody` annotations.

### **B) Create a visualization of the Spring Boot layers (UML diagram or similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers. Describe the role of the elements modeled in the diagram.**

![Ex3_3_UML_Diagram](https://github.com/tiagosora/IES_104142/tree/master/lab3/lab3_3/Ex3_3_UML_Diagram.png)

### **C) Explain the annotations @Table, @Colum, @Id found in the Employee entity.**

ddwdwdw

### **D) Explain the use of the annotation @AutoWired (in the Rest Controller class).**

