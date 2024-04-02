# Spring Boot 3 Bucket4j-RateLimiter with Hazelcast distributed cache
An example of a RESTful WebServer developed using Spring & SpringBoot to implement rate limiter with hazelcast cache.

This example explained about spring boot3 with hazelcast cache. 
## Why HTTP Caching Matters for APIs?
Caching is reduce the number of calls made to your endpoint and also improve the latency of requests to your API.
* Performance improvement
* Reduced server load
* Bandwidth optimization
* Scalability
* Handling traffic spikes

## When should using Cache for APIs?
Using cache when your data is not volatile real-time data.

## Why should using rate limiter on API?
Rate limiting, also known as API rate limiting, is a crucial practice for ensuring the stability, security, and efficient operation of APIs. Let’s explore why it’s essential:
  *  Performance Optimization:
    - Efficient Resource Utilization: Without rate limiting, an API could be bombarded with an excessive number of requests, leading to slow performance and resource exhaustion.
    - Predictable Behavior: By enforcing limits on the number of requests, you ensure that your API can handle traffic without becoming overwhelmed.
  * Security Enhancement:
    - Mitigating DoS Attacks: Denial-of-Service (DoS) attacks can flood an API with unlimited requests, causing server overload. Rate limiting helps prevent such attacks by throttling excessive requests.
    - Protecting Backend Systems: Limiting the number of requests shields your backend systems from unnecessary strain.
  * Scalability:
    - Unexpected Popularity: If your API suddenly gains popularity, there may be unexpected spikes in traffic. Rate limiting helps manage these surges, preventing severe lag times.
    - Application Rate Limiting: By controlling the quantity of data clients can consume, you ensure scalability even during high-demand periods.
  * Avoiding Misuse:
    - Accidental Overuse: Rate limiting prevents unintentional misuse of your API. Users might inadvertently send too many requests, affecting system stability.
    - Security Risks: Unrestricted access can lead to security vulnerabilities or data loss.

## Requirements
The fully fledged server uses the following:

* Spring Framework
* SpringBoot
* Spring cache
* Hazelcast
* Bucket4j

## Dependencies
There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.<br>

  	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>com.bucket4j</groupId>
			<artifactId>bucket4j-core</artifactId>
			<version>8.10.1</version>
		</dependency>
		<dependency>
			<groupId>com.bucket4j</groupId>
			<artifactId>bucket4j-hazelcast</artifactId>
			<version>8.10.1</version>
		</dependency>
		<!-- Hazelcast -->
		<dependency>
			<groupId>com.hazelcast</groupId>
			<artifactId>hazelcast</artifactId>
		</dependency>
		<dependency>
			<groupId>com.hazelcast</groupId>
			<artifactId>hazelcast-spring</artifactId>
			<version>5.3.6</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

## Building the project
You will need:

*	Java JDK 17 or higher
*	Maven 3.5.1 or higher
*	Tomcat 10.1

Clone the project and use Maven to build the server

	$ mvn clean install
	
