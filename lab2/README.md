# Lab 2 - 104142

IES - Practical Classes - 22/23 - **Lab 2** - **P5** - **Tiago Carvalho** - **104142**

#

# Server-side Programming with Servlets

A **Servlet** is a Java class that runs at the server, handles requests, processes them, and reply with a response. A servlet must be deployed into a (multi threaded) **Servlet Container** to be usable. *Servlet* is a generic interface and the *HttpServlet* is an extension of that interface (the most used type of Servlets).

## Servlet Containers vs Docker Containers

**Docker Containers** are used to deploy virtualized runtimes. **Servlet Containers** provide a runtime to execute server-sided web-related Java code.

# Apache Tomcat

## **What is Apache Tomcat??**

The Apache Tomcat software is an open source Java Servlet Container that allows the implementation of an array of enterprise *Java Web Applications*.

### How to install and run Apache Tomcat

1. [Download](https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.68/bin/apache-tomcat-9.0.68.tar.gz) the Tomcat v9 file.
2. Extract it and go `cd {path_to_folder}/bin`
3. Grant premission to startup file:  `chmod u+x startup.sh`
4. Execute the file `./startup.sh`
5. (Optional) Access the default page in the browser [`http://localhost:8080`](http://localhost:8080) to check if it's all done correctly.

The Tomcat installation includes a management environment (“Manager app”) to control the server, including deploying and undeploying applications (http://localhost:8080/manager). However, before using the manager app, it is necessary to register at least one role in `{path_to_folder}/conf/tomcat-users.xml`, adding the following code:**Username** - admin | **Password** - secret

# **Creating the web application and deploying into Tomcat**

Start by creating a maven-based web application project using the following template:

- archetypeGroupId - org.codehaus.mojo.archetypes
- archetypeArtifactId - webapp-javaee7
- archetypeVersion - 1.1

using an IDE of your choice **or** use the following line of code (be careful with the path):

`mvn archetype:generate -DgroupId=com.tiagosora -DartifactId=lab2_1 -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=webapp-javaee7 -DarchetypeVersion=1.1 -DinteractiveMode=false`

After that, we type `mvn install` to build the project. Confirm that you have a **.war** file in `{path_to_folder}/target`. This is your application packaged as a Web Archive (you may inspect its contents in a regular Zip tool, though not required).

### How to deploy the web app to Tomcat

Use the Tomcat management interface (Tomcat manager application) to upload and deploy a .war file (http://localhost:8080/manager).

![image-20221013172718331](https://i.postimg.cc/k5Nkmp6Z/Screenshot-from-2022-10-13-17-27-00.png)

To confirm that your application was successfully deployer in Tomcat, access http://localhost:8080/lab2_1-1.0-SNAPSHOT/.

# Creating a servlet

For this example, we added a basic servlet to the project that takes the name of the user, passed as a parameter in the HTTP request and prints a customized message, using these [guide lines](https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/#webservlet_annotation). The final result shall be a Java file with a similar instruction.

```java
@WebServlet(name = "MyFirstServlet", value = "/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        if (username == null) {
            throw new NullPointerException("Invalid username!");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Hello " + username + "</h3>");
```

After building the project using `mvn package`, it should be possible to access http://localhost:8080/lab2_1-1.0-SNAPSHOT/MyFirstServlet and add a parameter `username`.

For example, try http://localhost:8080/lab2_1-1.0-SNAPSHOT/MyFirstServlet?username=Banana

# **Server-side programming with embedded servers**

In this case, you will be using an “embedded server”, since its lifecycle (start, stop) and the deployment of the artifacts is controlled by the application code. 

To do so, its possible to follow this [tutorial](https://examples.javacodegeeks.com/enterprise-java/jetty/embedded-jetty-server-example/) of the guide. In this example, it will show how to use **Jetty** in embedded mode. Jetty can be used in standalone mode, but the main purpose behind building jetty was so that it could be used inside an application instead of deploying an application on jetty server. In Embedded Jetty, you write a web application and instantiate Jetty server in the same code base.

# **Introduction to web apps with a full-featured framework (Spring Boot)**

**Spring Boot** is a rapid application development platform built on top of the popular **Spring Framework**. By assuming opinionated choices by default (e.g.: assumes common configuration options without the need to specify everything), Spring Boot is a convention-over-configuration addition to the Spring platform,useful to get started with minimum effort and create stand-alone, production-grade applications.

In order to create a new (maven-supported, Spring Boot) project for your web app, we can use [Spring Initializr](https://start.spring.io/), add the **Spring Web** dependency and generate the *starter kit*.

### **Build and run the program** 

The generated seed project also includes a convenient [Maven wrapper script](https://www.baeldung.com/maven-wrapper) (mvnw).

1. `mvn install -DskipTests && java -jar target\webapp1-0.0.1-SNAPSHOT.jar`
2. `mvn package` and `./mvnwspring-boot:run`

### **Changing the Port**

To make sure it worked correctly, we just have to check the *default* 8080 port, http://localhost:8080 or change the port to a custom by inserting "server-port = 1234" in `/src/resources/application.properties`.

## Serving Web content with Spring MVC

For this example, it required to build a simple application to serve web content as detailed in [this article](https://spring.io/guides/gs/serving-web-content/).

You will build an application that has a static home page and that will also accept HTTP GET requests at `http://localhost:8080/greeting`. It will respond with a web page that displays HTML. The body of the HTML will contain a greeting: “Hello, World!”

You can customize the greeting with an optional `name` parameter in the query string. The URL might then be `http://localhost:8080/greeting?name=User`. The `name` parameter value overrides the default value of `World` and is reflected in the response by the content changing to “Hello, User!”

### **Create a Web Controller**

You can easily identify the controller by the **@Controller** annotation. In the following example, the class **GreetingController** handles GET requests by returning the name of a `View` (in this case, greeting). 

```java
package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
```

## Building a RESTful Web Service

You will build a service that will accept HTTP GET requests at `http://localhost:8080/greeting`. It will respond with a JSON representation of a greeting, as the following listing shows:

```json
{"id":1,"content":"Hello, World!"}
```

You can customize the greeting with an optional `name` parameter in the query string, as the following listing shows `http://localhost:8080/greeting?name=User`. The `name` parameter value overrides the default value of `World` and is reflected in the response, as the following listing shows:

```json
{"id":1,"content":"Hello, User!"}
```

### **Create a Resource Representation Class**

Create a class to help with the structure. Set up the project and build system, you can create your web service.

```java
package com.example.restservice;

public class Greeting {
	private final long id;
	private final String content;
	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
}
```

### Create a Resource Controller

In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. These components are identified by the **@RestController** annotation.

```java
package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
```
# **Wrapping-up & integrating concepts**

Now, it's time to develop a web server (REST API) that offers random quotes from movies/shows from an existing API. 

However, to simplify the things, it's possible to simply add the quotes/movies information in a static way. To do so, just search online for movie quotes and add them to the "*API*".

### Explaining the project

Like before, I just created an application on Spring Initializr, using the same template and created a controller to manage the web request. Taking `GreetingController.java` for reference, I created `MoviesController.java`´, implementing three `@GetMapping` that ensures that HTTP GET requests to `api/quote`, `api/quotes` and `api/shows` are processed separately.

Endpoints:

| Method | Path                      | Description                                                  |
| ------ | ------------------------- | ------------------------------------------------------------ |
| GET    | api/quote                 | Returns a random quote from a random show/film.              |
| GET    | api/shows                 | List of all available shows. For convenience, a show should have some identifier/code. |
| GET    | api/quotes?show=<show_id> | Returns a random quote for the specified show/film.          |

Processing the requests, I created `MoviesAPI.java`, that is able to return a random quote from a random or specified movie. I also changed the **server port to** **9001**.

### Expected outputs

`http://localhost:9001/api/quote`

```
{"id":"2","show":"Movie Not Defined","quote":"When you can't have what you want, it's time to start wanting what you have."}
```

`http://localhost:9001/api/quotes?show=1`

```
{"id":"1","show":"Harry Potter","quote":"No good sittin’ worryin’ abou’ it. What’s comin’ will come, an’ we’ll meet it when it does."}
```

`http://localhost:9001/api/shows`

```
[{"id":"1","show":"Harry Potter"},{"id":"2","show":"Movie Not Defined"},{"id":"3","show":"Lord of The Rings"}]
```

# Review Questions

## **A. What are the responsibilities/services of a “servlet container”?**

Web servers use HTTP protocol to send and receive information/data. Basically, there's always an exchange of HTTP Requests and HTTP Responses. However, when using a dynamic web page, the user might need to submit/send extra data.

To avoid static responses from the web server, we are using servlet containers. A **Servlet** is a class responsible of managing the requests, processing them and return a response, based on the input. Servlets are controlled by a *Java* application called **Servlet Container**.

![What is a Servlet Container? - DZone Cloud](https://dz2cdn1.dzone.com/storage/temp/13992301-servlet-container-life-cycle.jpg)

---

## **B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)**

**Spring Boot** simplifies the configuration and building of an web application. In order to get started, we just need to declare which modules we want to use in our web app and download the fully-configured file tree structure.

**Spring MVC (Model-View-Controller)** is a well known design pattern for designing UI based applications. It mainly decouples business logic from UIs by separating the roles of model, view, and controller in an application.

![Spring MVC Tutorial](https://static.javatpoint.com/sppages/images/flow-of-spring-web-mvc.png)

- As displayed in the figure, all the incoming request is intercepted by the Dispatcher Servlet that works as the front controller.
- The Dispatcher Servlet gets an entry of handler mapping from the XML file and forwards the request to the controller.
- The controller returns an object of ModelAndView.
- The Dispatcher Servlet checks the entry of view resolver in the XML file and invokes the specified view component.

---

For more information on Spring MVC, continue reading [here](javatpoint.com/spring-mvc-tutorial).

---

## **C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?**

The *starter* dependencies, we used for the previous Spring Boot projects, mainly *Spring Web*, *Thymeleaf* and *Spring Boot DevTools*, are very helpful in terms of web development. One of the advantages of using those dependencies that really helped a lot through out the guide was the possibility of having the .jar and .war files automatically added on the classpath.

- **Spring Web** - Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Thymeleaf** - A modern server-side Java template engine for both web and standalone environments. Allows HTML to be correctly displayed in browsers and as static prototypes.
- **Spring Boot DevTools** - Provides fast application restarts, LiveReload, and configurations for enhanced development experience.

---

## **D. Which annotations are transitively included in the @SpringBootApplication?**

The **@SpringBootApplication** transitively includes the following:

- **@EnableAutoConfiguration** - Enables Spring Boot to auto-configure the application context. Therefore, it automatically creates and registers beans based on both the included jar files in the classpath and the beans defined by us.
- **@ComponentScan** - Enables Spring to scan for things like configurations, controllers, services, and other components we define. In particular, the *@ComponentScan* annotation is used with *@Configuration* annotation to specify the package for Spring to scan for components.
- **@Configuration** - Allows to register extra beans in the context or import additional configuration classes for the application context.

---

## **E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words.**

1. ### Use JSON Format

JSON is probably the most common data format for web APIs. Interactions with the API requests used to be done using the XML and HTML format. However, JSON is way easier to encode and decode then XML, making it the best fit.

2. ### Use logical nesting on endpoints

Nesting endpoints like `/shows` and `/show/show1/quotes` show the relationship that helps keeping the API organized.

3. ### Avoid verbs on endpoints

HTTP methods such as `GET`, `POST`, `PUT`, etc are already in verb form. So we should keep the endpoint name only as a noun and let the HTTP verbs handle what the endpoints do. For example, using `/show/show1/quotes` instead of `/show/get/show1/get/quotes`

4. ### Control API version carefully

When updating the API, we should always save it in a different version, because some changes might break some functionalities of the clients using the API. A way to do that is by adding the version identifier at the start of the API path, such as `/version0`, `version0.1`...

This way, the clients can choose which version they want to use and prevent eventual problems.

5. ### Provide extensive API documentation

It's never right to say that we shouldn't document some change on that API. Every change we make must be extensively documented, because different clients have different knowledges, so we must be as clear and explanatory as possible.

The documentation should contain:

- endpoints of the API
- example requests of relevant endpoints
- implementations in several programming languages
- error handling and circumstances

---

For more information on "Practices for REST API design", consider reading more in [stackoverflow](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/#h-accept-and-respond-with-json), [freecodecamp](https://www.freecodecamp.org/news/rest-api-best-practices-rest-endpoint-design-examples/) and [learn.microsoft](https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design).





