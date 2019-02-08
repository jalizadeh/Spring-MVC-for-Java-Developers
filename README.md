# Spring MVC for Java Developers by O'REILLY


## Course Contents
- Spring MVC Configuration
- Key framework components
- Request Processing Lifecycle

## Prerequisits
- Java (Servlet API)
- Spring Core
- HTML, JavaScript, XML

## Spring MVC Java Configuration
- [3] Project `Spring MVC Foundation` is configured
- [4] Logging dependencies added
- [5] `AppInitializer` & `WebConfig` is done.
	- Go to `http://localhost:8080/spring-mvc-foundation/main/` to see the message. The 1st webapp is done :)
- [6] `DispatcherInitializer` is used instead of `AppInitializer`
	- It is faster to configure & needs less to code
- [7] Context Heirarchies
	- `RootConfig` is added
	- Sub-packages `services` & `controllers` were added
- [8] View Resolution Configuration
	- `WebConfig` manages how to load `jsp` pages
	- `hello.jsp` holds the beautified `${message}`
	- `MainController` will send the message as `message`

## Boot Configuration
- [2] Boot Basics
	- Project `spring-mvc-boot` is configured. It is a `Spring Starter Project`
	- To see the message, go to `http://localhost:8080/main/`

- [3] Configuration Overrides
	- `/spring-mvc-boot/src/main/resources/application.properties` is used for overriding my own configurations
		```
		server.port=9090
		server.servlet.context-path=/simple
		logging.level.root=debug
		```
	- To see the message, go to `http://localhost:9090/simple/main/`


- [4] Thymeleaf Template Engine
	- It will load the `.html` files in `/spring-mvc-boot/src/main/resources/templates`
	- `application.properties` is reset to empty

- [5] WAR Packaging
	- Open `pom.xml`
		- Change to `<packaging>jar</packaging>` to `<packaging>war</packaging>`
		- Copy dependency `spring-boot-starter-web` and change it to:
			```
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-tomcat</artifactId>
				<scope>provided</scope>
			</dependency>
			```
	- `SpringMvcBootApplication`
		- change it to:
			```java
			public class SpringMvcBootApplication extends SpringBootServletInitializer{
			```
	- Go to `Windows > Preferences > Java > Installed JREs > Execution Environment`
		- Select `JavaSE-1.8`, then check `jre1.8.0_XXX [perfecr match]`
	- Stop any server that is running
	- Right click on `pom.xml`, Select `Run As > Maven Build...` (the second item)
		- In `Goals`, enter `package`, then click `Run`
	- `war` file is available in `project/target/`


## Basic Request Processing
- [2] Project `spring-mvc-project-management` setup is done.
	- Go to `http://localhost:8080/home/`
- [3] Handling Requests with Controllers
	- `ProjectController` implemented
	- Go to `http://localhost:8080/project/add`
- [4] Establishing Request Mappings
	- Different type of parameters in `POST` can be handled by mixing them
	- `project_add.jsp` changed
	- Action added to form in `project_add.jsp`
- [5] Building Models
	- Sending a `model` to the `view`
	- `home.jsp` changed
	- `com.oreilly.mvc.data.entities.Project` is added
- [6] Autowiring Controller Services
	- `projects.jsp` added
	- `com.oreilly.mvc.data.services.ProjectService` added to prepare data
	- `ProjectController` will give the needed data to view
		```java
		@Autowired
		public ProjectService projectService;
		
		@RequestMapping(value="/find")
		public String find(Model model) {
			model.addAttribute("projects", this.projectService.findAll());
			
			return "projects";
		}
		```
	- `Project > find` link in `header.jsp` is fixed
- [7] Variables in Paths
	- `project.jsp` is added
	- Each project is linked to it's page in `projects.jsp`
	- `ProjectController` will handle the page for selected project
		```java
		@RequestMapping(value="/{projectId}")
		public String findProject(Model model, @PathVariable Long projectId) {
			model.addAttribute("project", this.projectService.find(projectId));
			return "project";
		}
		```
	- I set a random ID generated for each project
- [8] Working with Flexible Handler Arguments
	- Instead of using `HttpServletRequest request` as [Method Argument](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-methods), `@RequestParam("name") String name` is provided to be easier to get the parameter & cast the type without any other code.
- [9] Binding Request Data
	- And now, instead of those mentioned above, by using `@ModelAttribute Project project` as a method's argument, the paramateres' names will match with the names of the parameters in the java object. ALL IN ONE 😍


## Spring MVC Tags
- [2] URL Tag
	- For having dynamic URLs, we can use URL Tags in web pages. So, if any `Context Root` is changed, the URLs will be updated with the new path.
		- In `jsp` files, use:
		```
		<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>

		<spring:url value="/resource/save" />
		```
		- NEVER use hardcoded URLs
	- `resource_add.jsp` added
	- Menu link to `Resource > add` updated
	- `application.properties` updated
	- Go to `http://localhost:8080/app/home/`