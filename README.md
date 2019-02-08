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