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
			```xml
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
	- For having dynamic URLs, we can use URL Tags in web pages. So, if any `Context Root Path` is changed, the URLs will be updated with the new path.
		- In `jsp` files, use:
		```jsp
		<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
		<spring:url value="/resource/save" />
		```
		- NEVER use hardcoded URLs
	- `resource_add.jsp` added
	- Menu link to `Resource > add` updated
	- `application.properties` updated
	- Go to `http://localhost:8080/app/home/`
- [3] Form Tag
	- Instead of `HTML form`, we can use `Spring MVC Form` which has more parameters to config & some other rules to obey
		- Define by:
		```jsp
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		```
		- NEVER use nested tags in form's parameters
		```jsp
		❌ it is wrong
		<form:form action="<spring:url value="/resource/save"/>" method="POST" modelAttribute="resource">

		✅ it is correct
		<spring:url value="/resource/save" var="formUrl"/>
		<form:form action="${formUrl}" method="POST" modelAttribute="resource">
		```
	- In the controller's method, the model object must be defined
	```java
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("resource",new  Resource());
		return "resource_add";
	}
	```
- [4] Input Tag
	- Instead of `HTML Input Tag`, we will use `Spring MVC Input Tag`
		```html
		<input type="text" id="resource-name" class="form-control" name="name" />

		<form:input path="name" cssClass="form-control" id="resource-name"/>
		```
- [5] Select Tag
	- Instead of `HTML Select Tag`, we will use `Spring MVC Select Tag`. Note that the `options` must be sent from the server.
		```html		
		<select id="resource-type" name="type" class="selectpicker">
			<option></option>
			<option value="material">Material</option>
			<option value="other">Other</option>
			<option value="staff">Staff</option>
			<option value="tech">Technical Equipment</option>
		</select>

		<form:select path="type" items="${typeOptions}" cssClass="selectpicker" id="resource-type"/>
		```

	- Options are sent from server:
		```java
		List<String> options = new LinkedList<>(
				Arrays.asList(new String[] {"Material", "Staff", "Other", "Equipment"})
				);
		model.addAttribute("typeOptions", options);
		```
- [6] Checkboxes and Radio Buttons
	- Instead of `HTML Checkbox & Radio Button Tags`, we will use `Spring MVC Tags`. Note that the `options` must be sent from the server.
		```html
		<form:radiobuttons path="unitOfMeasure"  items="${radioOptions}"/>

		<form:checkboxes path="indicators"  items="${checkOptions}"/>
		```
	- NOTE:
		- In `ResourceController > save`, change the `return` to
		```java
		return "redirect:/resource/add";
		```
		otherwise, there will be ['items' must not be null](https://stackoverflow.com/questions/24428930/spring-form-validation-error-java-lang-illegalargumentexception-items-must-n)
- [7] Textarea Tag
	- Instead of `HTML TextArea Tag`, we will use `Spring MVC TextArea Tag`.
	```html
	<form:textarea path="notes" cssClass="form-control" rows="3"/>
	```


## Advanced Controllers
- [2] Databinding Composite Objects
	- The object `Project` is changed and new object `Sponsor` is added
		- NOTE: the nested object, must have an empty constructor
	- `project_add.jsp` tags are changed
- [3] Databinding Lists
	- `List<String> pointsOfContact` added to `Project` object
	- The `path` of each field must be indexed like `path="pointsOfContact[0]"`
- [4] Working with ModelAttributes
	- In a controller, `@ModelAttribute` annotated methods will be handled first, before `@RequestMapping("path...")` annotated methods. Therefore, we can setup any needed data before the page fields are generated. Otherwise (as we did so far), if a form is POSTed, after page is refreshed, the `select` field, will be empty.
		- Before:
		```java
		@RequestMapping("/add")
		public String add(Model model) {
			model.addAttribute("resource",new  Resource());
			
			//list of options for selecting a Type
			List<String> options = new LinkedList<>(
					Arrays.asList(new String[] {"Material", "Staff", "Other", "Equipment"})
					);
			model.addAttribute("typeOptions", options);
			
			return "resource_add";
		}
		```
		- Now:
		```java
		@RequestMapping("/add")
		public String add(Model model) {
			return "resource_add";
		}

		@ModelAttribute(value="resource")
		public Resource getResource() {
			return new Resource();
		}
		
		@ModelAttribute(value="typeOptions")
		public List<String> getTypes(){
			return new LinkedList<>(Arrays.asList(new String[] {"Material", "Staff", "Other", Equipment"}));
		}
		```
- [5] Working with SessionAttributes
	- What if we need to send a model to another page, or even edit it? In this case, we need `Session`. As long as the session is up, it will hold the model for further use.
	- NOTE:
		- The model name used for holding data, must be used in session too.
		```java
		@SessionAttributes("resource")
		```
	- `resource_review.jsp` is added fro editing/saving the resource data
- [6] SessionStatus
	- The session must be completed and get clear of any saved attribute. The problem of previous part, it this, so method `save`, must be changed:
	```java
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status) {
		System.out.println("Saved: " + resource);
		
		//Mark the current handler's session processing as complete,
		// allowing for cleanup of session attributes.
		status.setComplete();
		return "redirect:/resource/add";
	}
	```
- [7] @ResponseBody
	- This annotation allows us to provide the return value of our controller handler method, as the body of the response to a request.
	- `resource_add.jsp` is changed:
	```html
	<script>var ctx = "${pageContext.request.contextPath}"</script>
	<script src="<spring:url value="/js/resource.js"/>"></script>
	```
	- NOTE: Any file in the folder `/src/main/resources/static` will be static content (client-side) and is totally managed by Spring