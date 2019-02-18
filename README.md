# Spring MVC for Java Developers by O'REILLY


## Course Contents
- Spring MVC Configuration
- Key framework components
- Request Processing Lifecycle

## Prerequisits
- Java (Servlet API)
- Spring Core
- HTML, JavaScript, XML

## [3] Spring MVC Java Configuration
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

## [4] Boot Configuration
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


## [5] Basic Request Processing
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


## [6] Spring MVC Tags
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


## [7] Advanced Controllers
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
- [8] @RequestBody
	- When the AJAX code is run, the dara in form is seialized & the data is passed back as part of response body. 
	```
	name=printer&type=Equipment&cost=100&unitOfMeasure=Piece&indicators=Requires+Approval&_indicators=on&notes=This+printer+needs+special+approval
	```
	This annotation allows us to save the data, recieved as JSON, into a Java Object.


## [8] Validation and Exception Handling
- [2,3] Validators
	- `ProjectValidator` contains the rules of validation
	- `ProjectController > initBinder` adds special beans like formating, validator
	- Dependency `javax validation-api` is needed to be used in method argument. `ProjectController > saveProject`:
	```java
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveProject(@Valid @ModelAttribute Project project, Errors errors) {		
		if(!errors.hasErrors()) 
			System.out.println("The project validated.");
		else 
			System.out.println("The project not validated");
		return "project_add";
	}
	```
- [4] [Bean Validation](https://beanvalidation.org/1.0/spec/)
	- It is also possible to set the conditions in a Java object (Bean) directly by using specific annotations like `@NotBlank`.
	- Dependency `org.hibernate hibernate-validator` is added, while the IDE says it is deprecated. So I used `javax.validation.constraints.NotBlank`
- [5] Form Errors
	- Up to now, we just detected if there is any error or not. Now we will show the error message, to the user, to inform him.
	- In `project_add.jsp`, where ever we need to show the error, we have to use `<form:errors path="x"/>`
	- Based on the generated element, a better look will be:
	```html
	<style type="text/css">
		span[id$='errors']{
			color:red;
		}
	</style>

	<div class="form-group">
		<label for="project-name">Name</label>
		<form:input id="project-name" cssClass="form-control" path="name"/>
		<form:errors path="name"/>
	</div>
	```
- [6] @ExceptionHandler
	- If any exception is raised during form insertion, or ..., this error will be shown to the user.
	```
	Whitelabel Error Page

	This application has no explicit mapping for /error, so you are seeing this as a fallback.
	Mon Feb 11 09:42:41 CET 2019
	There was an unexpected error (type=Internal Server Error, status=500).
	This is an exception.
	```
	- But it is possible to handle the exceptions and redirect to a proper page. The `value=Exception.class` will support a wide range of exceptions that can be handled by this method.
	```java
	@ExceptionHandler(value=Exception.class)
	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}
	```
- [7] HandlerExceptionResolver
	- The previous title is about handling exceptions inside controllers. With `HandlerExceptionResolver` interface provided by Spring, it is possible to have a global component, to handle exceptions globally. It is implemented in `GlobalHandlerExceptionResolver`.
	- If specific exception is needed to be handled, it can be put inside the controller, and it will override the global exception handler`s rules, because it is a local exception handler, which has the higher priority than the global one.
	```java
	@ExceptionHandler(value=NullPointerException.class)
	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}
	```

## [9] View Resolution
- [2] Chaining View Resolvers
	- `WebConfiguration` is needed for accessing the properties (mappings) set in `views.properties`
	- `other_home.jsp`  is the new page will be shown instead of `home.jsp`
- [3] Content Negotiation
	- What if we need to return/present data as XML/JSON/PDF? By using `com.fasterxml.jackson.dataformat  jackson-dataformat-xml` it is possible to return any object/page as needed type.
	- `ProjectController > findProjectObject` will return the data object in path `/api/{id}`
	- `WebMvcConfigurerAdapter` is [deprecated](https://stackoverflow.com/questions/47552835/the-type-webmvcconfigureradapter-is-deprecated), so I used `WebMvcConfigurer`
	- ❌ I couldn't present data as JSON, as mentioned in tutorial
	- Go to `http://localhost:8080/app/project/api/{projectId}`
- [4] Redirects
	- When a form is submitted, if the user refreshes the (redirected) page, it will ask for resending the submitted form data, to the server again. To prevent this, after form validation, it should be "redirected" to the desired page by using `redirect:` term before the page's path. It can be any path:
	```java
	//ProjectController > saveProject

	return "redirect:/home/";
	//or
	return "redirect:/project/find";
	```
	- [Post/Redirect/Get](https://en.wikipedia.org/wiki/Post/Redirect/Get)
- [5] RedirectAttributes
	- Most of the time, it is needed to send some data after filling a form to the redirected page. By `RedirectAttributes` it becomes possible
	- `ProjectController > saveProject` must have `RedirectAttributes` to hold the parameter.
	- Reciever path must also get the parameter `@RequestMapping(value="/", params="projectId")`
	- `ProjectService > save` is added (but not mentioned in the tutorial)
	```java
	public void save(Project project){
		this.projects.add(project);
	}
	```
	- The URL of the redirected page will be `http://localhost:8080/app/home/?projectId=55`
- [6] FlashAttributes
	- Instead of sending only one parameter, it is possible to send the whole object at once. Using `addFlashAttribute` is another solution, with some differences.
	- Also `MainController > goHomeAgain` needs to be changed. It won't recieve the `param` anymore, bbut accepts `@ModelAttribute("project") Project project` in the argument. 
	- Temporarily `MainController > greeting` is disabled. Because in this case there 2 methods with equal paths.

## [10] Advanced Components
- [2] Handler Interceptors
	- It allows us to intercept if any action is taken place (even in specific path). So then specific actions can be done.
	- `com.oreilly.mvc.interceptors.GlobalInterceptor` will handle when to intercept.
	- `WebConfiguration > addInterceptors` will manage the scope of the interception.
	- For demonstration, `header` is modified:
	```html
	<li><a>The time will appear only in 'project' ${currentDate}</a></li>
	```
- [3] Bean Scopes
	- By setting a bean's scope, Spring will be able to handle the threads needed for that bean. During whole life of application, how many times a beans needs to be created.
		- `SCOPE_APPLICATION`: since the application start, bean will be created.
		- `SCOPE_REQUEST`: each request will create only one intance of bean.
		- `SCOPE_SESSION`: there is a unique instance for each session.
	- `com.oreilly.mvc.HitCounter` is the scoped-bean
	```java
	...
	@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
	...
	```
	- Each time `ProjectController > addProject` is invoked, the bean will be run.
- [4] JSON Support
	- Now resources are shown in their own page `resources`
	- ❌ I couldn't present data as JSON, as mentioned in tutorial. When I use Firefox, it shows as XML, but when I use Postman, the response is in JSON! 🤷‍♂️
- [5] Controller Advice
	- We can define a master class `com.oreilly.mvc.controllers.GlobalControllerAdvice` ,annotated with `@ControllerAdvice(annotations=Controller.class)` which holds common methods, handlers, validator and .... Then if a any bean anotated with `@Controller`, the rules will be fetched from this class.
- [6] Databinding With Converters
	- With `Converter` it is possible to have global converters to be used when ever & where ever, we need to convert types (like String to Date) after form submission.
	- `com.oreilly.mvc.converters.JulianDateConverter` will handle how to convert String to Date for field `Project.startDate`
	- Also in `WebConfiguration`, the method must be added:
	```java
	@Override
	public void addFormatters(FormatterRegistry registry) {
		WebMvcConfigurer.super.addFormatters(registry);
		registry.addConverter(new JulianDateConverter());
	}
	```
- [7] Databinding Arguments with Converters
	- When a `Resource` object is accessed via `ResourceController > findResource`, it will return the object itself. In `com.oreilly.mvc.converters.ResourceConverter` it is configured to access the service manage the conversion process. It is just added to the `addFormatters`
	```java
	registry.addConverter(resourceConverter);
	```
- [8] File Upload Support
	- `ResourceController > handleUpload` handles the file uploaded.
	- Note that in the form, `enctype` must be defined:
	```html
	<spring:url value="/resource/upload" var="uploadURL"/>
	<form method="POST" action="${uploadURL}" enctype="multipart/form-data">
	```


## [11] Security Features
- [1] Security Features
	- How to prevent [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) & [CSRF](https://en.wikipedia.org/wiki/Cross-site_request_forgery) attacks with Spring MVC
- [3] Global CORS Configuration
	- In `WebConfiguration` rules of accessing to the server's data must be defined:
	```java
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//NO other domain is allowed
		registry.addMapping("/**").allowedMethods("");
		
		//any request, originated ONLY from this domain is allowed
		registry.addMapping("/**").allowedOrigins("http://somedomain.com").allowedMethods("*");
	}
	```
	- If user is not allowed to do HTTP Request, he will get `Invalid CORS request` response.
- [4] Granular CORS Configuration
	- Instead of defining global rules, it is also possible to define some specific rules for each controller and even for each method.
	- `ProjectController`:
	```java
	...
	@CrossOrigin(origins="http://otherdomain.com")	//gives access to all methods & request types
	public class ProjectController {
	.
	.
	.
	@CrossOrigin(origins="http://anotherdomain.com", methods=RequestMethod.POST)	//gives access only to this method & request type
	public Project findProjectObject(@PathVariable Long projectId) {
	...
	```
	- [x] Disable rules in `WebConfiguration`
- [5] CSRF Protection
	- Spring Boot provides a complete solution via `spring-boot-starter-security`
	- In `SpringMvcProjectManagement` add `@EnableWebSecurity` to enable it
	- When the security is activated, 2 things happen:
		1. For accessing the website, you need to login by
		```
		username: user
		password: generated at start and printed in console
		```
		2. At the end of any form generated by Spring tags `<form:form ...> ... </form:errors>`, a new hidden field is added, which holds the token, and prevents unauthorized form submissions
		```html
		<div>
		<input type="hidden" name="_csrf" value="f640e073-964a-493f-9be4-088ce54658d1" />
		</div>
		```
	- By defining my own `WebSecurityConfiguration`, it is possible to enable/disable some features. Like User Authentication



## Extra
- [How to define multiple validations](https://stackoverflow.com/questions/14533488/adding-multiple-validators-using-initbinder/44540447)